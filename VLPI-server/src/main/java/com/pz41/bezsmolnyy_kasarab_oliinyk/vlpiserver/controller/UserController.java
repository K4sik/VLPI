package com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.controller;

import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.config.jwt.JWTTokenProvider;
import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.domain.response.UserResponse;
import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.domain.response.UserStatisticResponse;
import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.domain.response.UserTaskStatisticResponse;
import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.entity.User;
import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.service.UserService;
import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.util.converter.UserTaskConverter;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.constants.SecurityConstants.TOKEN_PREFIX;

@RestController
@RequestMapping(value = "users")
public class UserController {

    private final JWTTokenProvider jwtTokenProvider;
    private final UserService userService;

    public UserController(JWTTokenProvider jwtTokenProvider, UserService userService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @GetMapping("/token")
    public ResponseEntity<UserResponse> getLoggedUser(@RequestHeader("Authorization") String token) {
        if(token.startsWith(TOKEN_PREFIX)) {
            String email = jwtTokenProvider.getEmail(token.substring(7));
            User user = userService.findByEmail(email);
            UserResponse userResponse = new UserResponse();
            BeanUtils.copyProperties(user, userResponse);
            return ResponseEntity.ok(userResponse);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/statistic")
    public ResponseEntity<UserStatisticResponse> getUserStatistic(@RequestHeader("Authorization") String token) {
        if(token.startsWith(TOKEN_PREFIX)) {
            String email = jwtTokenProvider.getEmail(token.substring(7));
            User user = userService.findByEmail(email);
            UserStatisticResponse userResponse = new UserStatisticResponse();
            BeanUtils.copyProperties(user, userResponse);

            AtomicInteger averageGradeAtomic = new AtomicInteger();
            List<UserTaskStatisticResponse> taskStatisticList = new ArrayList<>();
            user.getUserTasks().forEach(task -> {
                averageGradeAtomic.addAndGet(task.getGrade());
                UserTaskStatisticResponse taskStatistic = new UserTaskStatisticResponse();
                UserTaskConverter.convert(task, taskStatistic);
                taskStatisticList.add(taskStatistic);
            });

            byte averageGrade = (byte) (averageGradeAtomic.get()/taskStatisticList.size());

            userResponse.setAverageGrade(averageGrade);
            userResponse.setTasks(taskStatisticList);
            return ResponseEntity.ok(userResponse);
        }
        return ResponseEntity.badRequest().build();
    }
}
