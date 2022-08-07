package com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.controller;

import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.domain.request.SigninRequest;
import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.domain.response.SigninResponse;
import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.entity.User;
import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("signup")
    public ResponseEntity<Void> signup(@RequestBody User user) {
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("signin")
    public ResponseEntity<SigninResponse> signin(@RequestBody SigninRequest request) {
        String token = userService.signin(request.getEmail(), request.getPassword());
        String role = "";
        if(token != null) {
            role = userService.findByEmail(request.getEmail()).getUserRole().toString();
        }
        return new ResponseEntity<>(new SigninResponse(token, role), HttpStatus.OK);
    }
}
