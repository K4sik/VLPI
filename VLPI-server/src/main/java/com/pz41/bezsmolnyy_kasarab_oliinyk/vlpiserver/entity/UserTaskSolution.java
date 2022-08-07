package com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.entity;

import lombok.*;

import javax.persistence.*;

@Table(name = "user_task_solution")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserTaskSolution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_task_id", nullable = false)
    @NonNull
    private UserTask userTask;

    @ManyToOne(optional = false)
    @JoinColumn(name = "requirement_id", nullable = false)
    @NonNull
    private Requirement requirement;

    @ManyToOne(optional = false)
    @JoinColumn(name = "task_details_id", nullable = false)
    @NonNull
    private TaskDetails taskDetails;

}