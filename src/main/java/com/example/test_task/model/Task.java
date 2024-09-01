package com.example.test_task.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tasks")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "task_gen")
    @SequenceGenerator(
            name = "task_gen",
            sequenceName = "task_seq",
            allocationSize = 1,
            initialValue = 1
    )
    private Long id;

    private String description;

    private boolean completed;
}
