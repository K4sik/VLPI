package com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.domain.response;

import lombok.Data;

@Data
public class TaskDetailsRequirementResponse {

    private Long id;
    private String text;
    private boolean isCorrect;
}
