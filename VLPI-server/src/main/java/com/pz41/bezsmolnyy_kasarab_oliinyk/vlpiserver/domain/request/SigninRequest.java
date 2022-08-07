package com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.domain.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SigninRequest {

    private String email;
    private String password;
}
