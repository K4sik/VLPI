package com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.util.converter;

import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.domain.response.TaskDetailsRequirementResponse;
import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.domain.response.TaskDetailsResponse;
import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.domain.response.TaskResponse;
import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.domain.response.TaskUserResponse;
import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.entity.Task;
import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.entity.TaskDetails;
import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.entity.TaskDetailsRequirement;
import org.springframework.util.StringUtils;

public class TaskConverter {
    public static void convert(Task task, TaskResponse taskResponse) {
        taskResponse.setId(task.getId());
        taskResponse.setDifficulty(StringUtils.capitalize(task.getDifficulty().name().toLowerCase()));
        taskResponse.setName(task.getName());
        taskResponse.setDescription(task.getDescription());
        taskResponse.setAuthorName(task.getUser().getFullName());
        taskResponse.setAuthorEmail(task.getUser().getEmail());
        taskResponse.setTime(task.getTime());

        task.getTaskDetailses().forEach(details -> {
            TaskDetailsResponse detailsResponse = new TaskDetailsResponse();
            convert(details, detailsResponse);
            taskResponse.getDetails().add(detailsResponse);
        });
    }

    private static void convert(TaskDetails taskDetails, TaskDetailsResponse taskDetailsResponse) {
        taskDetailsResponse.setId(taskDetails.getId());
        taskDetailsResponse.setWording(taskDetails.getWording());

        taskDetails.getTaskDetailsRequirements().forEach(requirement -> {
            TaskDetailsRequirementResponse requirementResponse = new TaskDetailsRequirementResponse();
            convert(requirement, requirementResponse);
            taskDetailsResponse.getRequirements().add(requirementResponse);
        });
    }

    private static void convert(TaskDetailsRequirement taskDetailsRequirement, TaskDetailsRequirementResponse taskDetailsRequirementResponse) {
        taskDetailsRequirementResponse.setId(taskDetailsRequirement.getRequirement().getId());
        taskDetailsRequirementResponse.setText(taskDetailsRequirement.getRequirement().getText());
        taskDetailsRequirementResponse.setCorrect(taskDetailsRequirement.isCorrect());
    }
}
