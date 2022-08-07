package com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.domain.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserStatisticResponse extends UserResponse {

    private byte averageGrade;
    private List<UserTaskStatisticResponse> tasks = new ArrayList<>();
}
