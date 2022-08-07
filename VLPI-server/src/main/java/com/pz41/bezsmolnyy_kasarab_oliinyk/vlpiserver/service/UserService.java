package com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.service;

import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.entity.User;

import java.util.List;

public interface UserService {

    void save(User user);

    User findById(Long id);

    List<User> findAll();

    boolean existsByEmail(String email);

    User findByEmail(String email);

    String signin(String username, String password);

}
