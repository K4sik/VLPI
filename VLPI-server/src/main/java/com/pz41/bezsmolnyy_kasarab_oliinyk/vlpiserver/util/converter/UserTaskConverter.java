package com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.util.converter;

import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.domain.response.UserTaskStatisticResponse;
import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.entity.UserTask;
import org.springframework.util.StringUtils;

public class UserTaskConverter {

    public static void convert(UserTask userTask, UserTaskStatisticResponse taskStatistic) {
        taskStatistic.setId(userTask.getId());
        taskStatistic.setName(userTask.getTask().getName());
        taskStatistic.setType(userTask.getTask().getTaskType().getName());
        taskStatistic.setDifficulty(StringUtils.capitalize(userTask.getTask().getDifficulty().name().toLowerCase()));
        taskStatistic.setGrade(userTask.getGrade());
        taskStatistic.setTime(userTask.getTime());
        taskStatistic.setDate(userTask.getDate());
    }
}
