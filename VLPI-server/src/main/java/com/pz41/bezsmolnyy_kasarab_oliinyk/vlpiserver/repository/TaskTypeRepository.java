package com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.repository;

import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.entity.TaskType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskTypeRepository extends JpaRepository<TaskType, Long> {
}