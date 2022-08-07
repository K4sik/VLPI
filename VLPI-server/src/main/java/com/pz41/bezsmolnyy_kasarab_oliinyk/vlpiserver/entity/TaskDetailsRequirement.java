package com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.entity;

import lombok.*;

import javax.persistence.*;

@Table(name = "task_details_requirement")
@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class TaskDetailsRequirement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "task_details_id", nullable = false)
    @NonNull
    private TaskDetails taskDetails;

    @ManyToOne(optional = false)
    @JoinColumn(name = "requirement_id", nullable = false)
    @NonNull
    private Requirement requirement;

    @Column(name = "is_correct", nullable = false)
    @NonNull
    private boolean isCorrect;

    @Column(name = "weight", nullable = false)
    @NonNull
    private float weight;

}