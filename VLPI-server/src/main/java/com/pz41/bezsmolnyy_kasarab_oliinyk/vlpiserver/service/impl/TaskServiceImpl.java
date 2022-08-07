package com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.service.impl;

import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.domain.request.UserTaskResultDetailsRequest;
import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.domain.request.UserTaskResultDetailsRequirementRequest;
import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.domain.request.UserTaskResultRequest;
import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.domain.response.TaskResponse;
import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.entity.*;
import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.service.UserService;
import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.service.UserTaskSolutionService;
import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.util.converter.TaskConverter;
import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.util.converter.TaskUserConverter;
import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.domain.response.TaskUserResponse;
import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.repository.TaskRepository;
import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.service.TaskService;
import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.service.UserTaskService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserService userService;
    private final UserTaskService userTaskService;
    private final UserTaskSolutionService userTaskSolutionService;

    public TaskServiceImpl(TaskRepository taskRepository, UserService userService, UserTaskService userTaskService, UserTaskSolutionService userTaskSolutionService) {
        this.taskRepository = taskRepository;
        this.userService = userService;
        this.userTaskService = userTaskService;
        this.userTaskSolutionService = userTaskSolutionService;
    }

    @Override
    public TaskResponse findById(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        if(task.isPresent()) {
            TaskResponse taskResponse = new TaskResponse();
            TaskConverter.convert(task.get(), taskResponse);
            return taskResponse;
        }
        return null;
    }

    @Override
    public List<TaskUserResponse> findByTaskTypeIdAndUserEmail(Long id, String email) {
        List<TaskUserResponse> taskUserResponseList = new ArrayList<>();
        List<Task> taskList = taskRepository.findByTaskTypeId(id);
        taskList.forEach(task -> {
            TaskUserResponse taskUserResponse = new TaskUserResponse();
            TaskUserConverter.convert(task, taskUserResponse);
            taskUserResponse.setDoneBefore(userTaskService.existsByUserEmailAndTaskId(email, task.getId()));
            taskUserResponseList.add(taskUserResponse);
        });

        return taskUserResponseList;
    }

    @Transactional
    @Override
    public byte evaluateTask(String email, UserTaskResultRequest taskResult) {
        User user = userService.findByEmail(email);
        Task task = taskRepository.getById(taskResult.getId());

        TaskType taskType = task.getTaskType();

        UserTask userTask = new UserTask();
        userTask.setUser(user);
        userTask.setTask(task);
        userTask.setTime(taskResult.getTime());
        userTask.setDate(LocalDateTime.now());

        List<UserTaskSolution> userTaskSolutionList = new ArrayList<>();

        AtomicReference<Float> totalPoints = new AtomicReference<>(0f);
        AtomicReference<Float> earnedPoints = new AtomicReference<>(0f);
        AtomicReference<Integer> correctCount = new AtomicReference<>(0);
        AtomicReference<Integer> wrongCount = new AtomicReference<>(0);
        task.getTaskDetailses().forEach(taskDetails -> {
            Optional<UserTaskResultDetailsRequest> detailsRequest = taskResult.getDetails().stream()
                    .filter(x -> x.getId().equals(taskDetails.getId())).findFirst();

            detailsRequest.ifPresent(userTaskResultDetailsRequest -> taskDetails.getTaskDetailsRequirements().forEach(detailsRequirement -> {
                if (detailsRequirement.getWeight() > 0) {
                    totalPoints.updateAndGet(v -> (float) (v + detailsRequirement.getWeight()));
                }

                Optional<UserTaskResultDetailsRequirementRequest> requirementRequest = userTaskResultDetailsRequest
                        .getRequirements().stream().filter(x -> x.getId().equals(detailsRequirement.getRequirement().getId())).findFirst();

                if (requirementRequest.isPresent()) {
                    UserTaskSolution userTaskSolution = new UserTaskSolution();
                    userTaskSolution.setTaskDetails(taskDetails);
                    userTaskSolution.setRequirement(detailsRequirement.getRequirement());
                    userTaskSolutionList.add(userTaskSolution);

                    if(detailsRequirement.isCorrect()) {
                        if(taskType.getName().equals("Drag&Drop")) {
                            correctCount.updateAndGet(v -> v + 1);
                        }
                        earnedPoints.updateAndGet(v -> (float) (v + detailsRequirement.getWeight()));
                    } else {
                        if(taskType.getName().equals("Drag&Drop")) {
                            wrongCount.updateAndGet(v -> v + 1);
                        }
                    }
                }
            }));

        });

        float wrongPenalty = 0;
        if(wrongCount.get() > 0) {
            float wrongRatio = wrongCount.get()/(float)correctCount.get();
            double f = Math.random()/Math.nextDown(1.0f);
            double x = 0.90*(1.0-f)+1.1*f;
            wrongPenalty = (float) (earnedPoints.get()*wrongRatio*x);
        }
        float scoreRes = ((earnedPoints.get()-wrongPenalty)/totalPoints.get())
                *totalPoints.get();
        float timeRes = ((task.getTime()*1.2f-userTask.getTime())/task.getTime());
        float gradePreScale = Math.min(scoreRes*timeRes, totalPoints.get());
        byte grade = (byte) Math.max(((gradePreScale/totalPoints.get()) * 100), 0);

        userTask.setGrade(grade);
        userTask = userTaskService.save(userTask);
        for (UserTaskSolution userTaskSolution : userTaskSolutionList) {
            userTaskSolution.setUserTask(userTask);
        }

        userTaskSolutionService.saveAll(userTaskSolutionList);

        return grade;
    }
}
