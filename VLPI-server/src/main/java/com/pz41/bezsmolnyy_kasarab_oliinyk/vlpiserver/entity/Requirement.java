package com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "requirement")
@Entity
@Setter
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public class Requirement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "text", nullable = false)
    @NonNull
    private String text;

    @ManyToOne(optional = false)
    @JoinColumn(name = "requirement_type_id", nullable = false)
    @NonNull
    private RequirementType requirementType;

    @OneToMany(mappedBy = "requirement", orphanRemoval = true)
    private List<TaskDetailsRequirement> taskRequirements = new ArrayList<>();

    @OneToMany(mappedBy = "requirement", orphanRemoval = true)
    private List<UserTaskSolution> userTaskSolutions = new ArrayList<>();

}