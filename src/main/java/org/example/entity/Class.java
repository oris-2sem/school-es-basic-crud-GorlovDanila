package org.example.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "classes")
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dateInsert;
    private String letter;

    @ManyToOne
    @JoinColumn(name = "timetable_id", referencedColumnName = "id")
    private Timetable timetable;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    private Teacher teacher;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classId")
    private Set<Student> students;

}
