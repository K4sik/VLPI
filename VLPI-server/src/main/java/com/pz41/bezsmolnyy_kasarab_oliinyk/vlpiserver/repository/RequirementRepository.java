package com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.repository;

import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.entity.Requirement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequirementRepository extends JpaRepository<Requirement, Long> {
}