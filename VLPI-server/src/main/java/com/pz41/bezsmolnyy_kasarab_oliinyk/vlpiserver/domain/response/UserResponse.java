package com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.domain.response;

import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.entity.enums.UserRole;
import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private String fullName;
    private String email;
    private UserRole userRole;
}
