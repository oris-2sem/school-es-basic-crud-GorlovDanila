package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.entity.Class;
import org.example.service.ClassDataService;
import org.example.service.StudentDataService;
import org.example.service.TeacherDataService;
import org.example.service.TimetableDataService;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/class")
public class ClassController {
    private final ClassDataService classDataService;
    private final TimetableDataService timetableDataService;
    private final TeacherDataService teacherDataService;
    private final StudentDataService studentDataService;

    @GetMapping
    public Iterable<Class> showClasses() {
        return classDataService.findAll();
    }

    @PutMapping
    public void createClass(@RequestParam("date_insert") Date date_insert,
                            @RequestParam("letter") String letter,
                            @RequestParam("timetable_id") Long timetable_id,
                            @RequestParam("teacher_id") Long teacher_id,
                            @RequestParam("students") Set<Long> studentsId) {
        Class currentClass = new Class();
        currentClass.setDateInsert(date_insert);
        currentClass.setLetter(letter);
        currentClass.setStudents(studentDataService.getStudentsById(studentsId));
        timetableDataService.findById(timetable_id).ifPresent(currentClass::setTimetable);
        teacherDataService.findById(teacher_id).ifPresent(currentClass::setTeacher);
        classDataService.save(currentClass);
    }

    @DeleteMapping
    public void deleteClass(@RequestParam("class_id") Long class_id) {
        classDataService.findById(class_id).ifPresent(classDataService::delete);
    }

    @PatchMapping
    public void updateClass(@RequestParam("id") Long id,
                            @RequestParam("date_insert") Date date_insert,
                            @RequestParam("letter") String letter,
                            @RequestParam("timetable_id") Long timetable_id,
                            @RequestParam("teacher_id") Long teacher_id,
                            @RequestParam("students") Set<Long> studentsId) {
        classDataService.update(id, date_insert, letter, timetable_id, teacher_id, studentDataService.getStudentsById(studentsId));
    }
}
