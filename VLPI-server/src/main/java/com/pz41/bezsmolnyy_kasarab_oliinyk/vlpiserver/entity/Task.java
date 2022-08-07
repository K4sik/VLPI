package com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.entity;

import com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.entity.enums.Difficulty;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "task")
@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "difficulty", nullable = false)
    @NonNull
    private Difficulty difficulty;

    @ManyToOne(optional = false)
    @JoinColumn(name = "task_type_id", nullable = false)
    @NonNull
    private TaskType taskType;

    @Column(name = "name", nullable = false)
    @NonNull
    private String name;

    @Column(name = "description", nullable = false)
    @NonNull
    private String description;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @NonNull
    private User user;

    @Column(name = "time", nullable = false)
    @NonNull
    private short time;

    @OneToMany(mappedBy = "task", orphanRemoval = true)
    private List<TaskDetails> taskDetailses = new ArrayList<>();

}