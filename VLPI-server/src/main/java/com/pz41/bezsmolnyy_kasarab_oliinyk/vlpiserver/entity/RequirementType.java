package com.pz41.bezsmolnyy_kasarab_oliinyk.vlpiserver.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "requirement_type")
@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class RequirementType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, unique = true, length = 50)
    @NonNull
    private String name;

    @OneToMany(mappedBy = "requirementType", orphanRemoval = true)
    private List<Requirement> requirements = new ArrayList<>();

}