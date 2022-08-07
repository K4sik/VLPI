package com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.service;

import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.domain.request.UserTaskResultRequest;
import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.domain.response.TaskResponse;
import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.domain.response.TaskUserResponse;
import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.entity.Task;

import java.util.List;

public interface TaskService {

    TaskResponse findById(Long id);

    List<TaskUserResponse> findByTaskTypeIdAndUserEmail(Long id, String email);

    byte evaluateTask(String email, UserTaskResultRequest taskResult);
}
