package com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.repository;

import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

}