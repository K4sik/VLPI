package com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.domain.request;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserTaskResultDetailsRequest {

    private Long id;
    private List<UserTaskResultDetailsRequirementRequest> requirements = new ArrayList<>();
}
