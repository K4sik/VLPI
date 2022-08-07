package com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "module")
@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, unique = true, length = 50)
    @NonNull
    private String name;

    @OneToMany(mappedBy = "module", orphanRemoval = true)
    private List<TaskType> taskTypes = new ArrayList<>();

}