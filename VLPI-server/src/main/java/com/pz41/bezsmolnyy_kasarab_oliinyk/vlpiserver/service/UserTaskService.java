package com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.service;

import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.entity.UserTask;

public interface UserTaskService {

    UserTask save(UserTask userTask);

    boolean existsByUserEmailAndTaskId(String email, Long id);
}
