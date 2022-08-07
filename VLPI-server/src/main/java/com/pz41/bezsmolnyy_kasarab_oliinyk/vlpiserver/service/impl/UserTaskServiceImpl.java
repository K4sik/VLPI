package com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.service.impl;

import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.entity.UserTask;
import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.repository.UserTaskRepository;
import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.service.UserTaskService;
import org.springframework.stereotype.Service;

@Service
public class UserTaskServiceImpl implements UserTaskService {

    private final UserTaskRepository userTaskRepository;

    public UserTaskServiceImpl(UserTaskRepository userTaskRepository) {
        this.userTaskRepository = userTaskRepository;
    }

    @Override
    public UserTask save(UserTask userTask) {
        return userTaskRepository.save(userTask);
    }

    @Override
    public boolean existsByUserEmailAndTaskId(String email, Long id) {
        return userTaskRepository.existsByUserEmailAndTaskId(email, id);
    }
}
