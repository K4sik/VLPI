package com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.repository;

import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.entity.TaskDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskDetailsRepository extends JpaRepository<TaskDetails, Long> {
}