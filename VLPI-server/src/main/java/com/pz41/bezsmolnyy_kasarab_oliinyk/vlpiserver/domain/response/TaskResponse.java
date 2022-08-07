package com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.domain.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TaskResponse {

    private Long id;
    private String difficulty;
    private String name;
    private String description;
    private short time;
    private String authorName;
    private String authorEmail;
    private List<TaskDetailsResponse> details = new ArrayList<>();
}
