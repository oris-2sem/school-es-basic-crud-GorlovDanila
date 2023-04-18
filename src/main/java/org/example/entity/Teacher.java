package org.example.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String profile;

    @ManyToMany(mappedBy = "teachers")
    Set<Timetable> timetables;

    @OneToOne(mappedBy = "teacher")
    private Class classId;
}
