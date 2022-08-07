package com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.domain.response;

import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.entity.enums.Difficulty;
import lombok.Data;

@Data
public class TaskUserResponse {
    private Long id;
    private String difficulty;
    private String name;
    private String description;
    private String authorName;
    private String authorEmail;
    private boolean doneBefore;
}
