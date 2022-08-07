package com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.repository;

import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.entity.UserTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTaskRepository extends JpaRepository<UserTask, Long> {

    boolean existsByUserEmailAndTaskId(String email, Long id);
}