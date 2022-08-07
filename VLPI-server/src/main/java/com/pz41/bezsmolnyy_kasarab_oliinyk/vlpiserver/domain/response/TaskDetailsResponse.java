package com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.domain.response;

import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.entity.TaskDetailsRequirement;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TaskDetailsResponse {

    private Long id;
    private String wording;

    private List<TaskDetailsRequirementResponse> requirements = new ArrayList<>();
}
