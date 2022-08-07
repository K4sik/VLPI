package com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "user_task")
@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class UserTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @NonNull
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "task_id", nullable = false)
    @NonNull
    private Task task;

    @Column(name = "grade", nullable = false)
    @NonNull
    private byte grade;

    @Column(name = "time", nullable = false)
    @NonNull
    private short time;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @OneToMany(mappedBy = "userTask", orphanRemoval = true)
    private List<UserTaskSolution> userTaskSolutions = new ArrayList<>();

}