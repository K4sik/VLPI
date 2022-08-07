package com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.service;

import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.entity.UserTaskSolution;

import java.util.List;

public interface UserTaskSolutionService {

    UserTaskSolution save(UserTaskSolution userTaskSolution);

    void saveAll(List<UserTaskSolution> userTaskSolutionList);
}
