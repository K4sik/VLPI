package com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.controller;

import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.config.jwt.JWTTokenProvider;
import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.domain.request.UserTaskResultRequest;
import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.domain.response.TaskResponse;
import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.domain.response.TaskUserResponse;
import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.domain.response.UserTaskResponse;
import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.constants.SecurityConstants.TOKEN_PREFIX;

@RestController
@RequestMapping("tasks")
public class TaskController {

    private final JWTTokenProvider jwtTokenProvider;
    private final TaskService taskService;

    public TaskController(JWTTokenProvider jwtTokenProvider, TaskService taskService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.taskService = taskService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTask(@PathVariable("id") Long id) {
        TaskResponse taskUserResponse = taskService.findById(id);
        if(taskUserResponse != null) {
            return ResponseEntity.ok(taskUserResponse);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/type/{id}")
    public ResponseEntity<List<TaskUserResponse>> getTasksByType(@RequestHeader("Authorization") String token, @PathVariable("id") Long id) {
        if(token.startsWith(TOKEN_PREFIX)) {
            String email = jwtTokenProvider.getEmail(token.substring(7));
            List<TaskUserResponse> taskUserResponseList = taskService.findByTaskTypeIdAndUserEmail(id, email);
            return ResponseEntity.ok(taskUserResponseList);
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/evaluation")
    public ResponseEntity<UserTaskResponse> evaluateTask(@RequestHeader("Authorization") String token, @RequestBody UserTaskResultRequest taskResult) {
        if(token.startsWith(TOKEN_PREFIX)) {
            String email = jwtTokenProvider.getEmail(token.substring(7));

            UserTaskResponse userTaskResponse = new UserTaskResponse();
            userTaskResponse.setGrade(taskService.evaluateTask(email, taskResult));
            return ResponseEntity.ok(userTaskResponse);
        }

        return ResponseEntity.badRequest().build();
    }
}
