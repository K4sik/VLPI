package com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.util.converter;

import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.domain.response.TaskDetailsRequirementResponse;
import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.domain.response.TaskDetailsResponse;
import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.domain.response.TaskResponse;
import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.domain.response.TaskUserResponse;
import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.entity.Task;
import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.entity.TaskDetails;
import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.entity.TaskDetailsRequirement;
import org.springframework.util.StringUtils;

import java.util.Locale;

public class TaskUserConverter {

    public static void convert(Task task, TaskUserResponse response) {
        response.setId(task.getId());
        response.setDifficulty(StringUtils.capitalize(task.getDifficulty().name().toLowerCase()));
        response.setName(task.getName());
        response.setDescription(task.getDescription());
        response.setAuthorName(task.getUser().getFullName());
        response.setAuthorEmail(task.getUser().getEmail());
    }
}
