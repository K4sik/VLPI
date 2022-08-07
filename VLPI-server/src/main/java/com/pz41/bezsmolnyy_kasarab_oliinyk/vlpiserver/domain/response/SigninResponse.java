package com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SigninResponse {

    private String token;
    private String role;
}
