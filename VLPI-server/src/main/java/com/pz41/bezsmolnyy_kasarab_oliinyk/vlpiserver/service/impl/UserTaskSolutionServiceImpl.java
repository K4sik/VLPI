package com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.service.impl;

import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.entity.UserTaskSolution;
import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.repository.UserTaskSolutionRepository;
import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.service.UserTaskSolutionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserTaskSolutionServiceImpl implements UserTaskSolutionService {

    private final UserTaskSolutionRepository userTaskSolutionRepository;

    public UserTaskSolutionServiceImpl(UserTaskSolutionRepository userTaskSolutionRepository) {
        this.userTaskSolutionRepository = userTaskSolutionRepository;
    }

    @Override
    public UserTaskSolution save(UserTaskSolution userTaskSolution) {
        return userTaskSolutionRepository.save(userTaskSolution);
    }

    @Override
    public void saveAll(List<UserTaskSolution> userTaskSolutionList) {
        userTaskSolutionRepository.saveAll(userTaskSolutionList);
    }
}
