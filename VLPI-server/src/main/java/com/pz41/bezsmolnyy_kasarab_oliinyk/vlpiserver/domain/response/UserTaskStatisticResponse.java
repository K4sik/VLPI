package com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.domain.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserTaskStatisticResponse {

    private Long id;
    private String name;
    private String type;
    private String difficulty;
    private byte grade;
    private short time;
    private LocalDateTime date;
}
