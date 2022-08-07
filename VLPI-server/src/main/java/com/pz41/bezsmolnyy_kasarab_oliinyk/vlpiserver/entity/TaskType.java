package com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "task_type")
@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class TaskType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, unique = true, length = 50)
    @NonNull
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "module_id", nullable = false)
    @NonNull private Module module;

    @OneToMany(mappedBy = "taskType", orphanRemoval = true)
    private List<Task> tasks = new ArrayList<>();

}