package com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.domain.request;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserTaskResultRequest {

    private Long id;
    private short time;
    private List<UserTaskResultDetailsRequest> details = new ArrayList<>();
}
