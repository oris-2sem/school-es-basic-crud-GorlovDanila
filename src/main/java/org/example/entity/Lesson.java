package org.example.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "lessons")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String theme;
    private String subject;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lesson")
    private Set<Task> tasks;
}
