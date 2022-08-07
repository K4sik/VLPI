package com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "task_details")
@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class TaskDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "wording", nullable = false)
    @NonNull
    private String wording;

    @ManyToOne(optional = false)
    @JoinColumn(name = "task_id", nullable = false)
    @NonNull
    private Task task;

    @OneToMany(mappedBy = "taskDetails", orphanRemoval = true)
    private List<TaskDetailsRequirement> taskDetailsRequirements = new ArrayList<>();

}