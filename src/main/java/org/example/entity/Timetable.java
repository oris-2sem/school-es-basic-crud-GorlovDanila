package org.example.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "timetables")
public class Timetable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Time time;
    private Date date;
    private String room;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "timetable")
    private Set<Class> classes;

    @ManyToMany
    @JoinTable(
            name = "timetable_teacher",
            joinColumns = @JoinColumn(name = "timetable_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id"))
    Set<Teacher> teachers;

}
