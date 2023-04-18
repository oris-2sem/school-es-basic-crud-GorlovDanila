package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.entity.Teacher;
import org.example.service.ClassDataService;
import org.example.service.TeacherDataService;
import org.example.service.TimetableDataService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/teacher")
public class TeacherController {
    private final TeacherDataService teacherDataService;
    private final ClassDataService classDataService;
    private final TimetableDataService timetableDataService;

    @GetMapping
    public Iterable<Teacher> showTeachers() {
        return teacherDataService.findAll();
    }

    @PutMapping
    public void createTeacher(@RequestParam("first_name") String firstName,
                              @RequestParam("last_name") String lastName,
                              @RequestParam("profile") String profile,
                              @RequestParam("class") Long classId,
                              @RequestParam("timetables") Set<Long> timetablesId) {
        Teacher currentTeacher = new Teacher();
        currentTeacher.setFirstName(firstName);
        currentTeacher.setLastName(lastName);
        currentTeacher.setProfile(profile);
        classDataService.findById(classId).ifPresent(currentTeacher::setClassId);
        currentTeacher.setTimetables(timetableDataService.getTimetablesById(timetablesId));
        teacherDataService.save(currentTeacher);
    }

    @DeleteMapping
    public void deleteTeacher(@RequestParam("teacher_id") Long teacher_id) {
        teacherDataService.findById(teacher_id).ifPresent(teacherDataService::delete);
    }

    @PatchMapping
    public void updateTeacher(@RequestParam("id") Long id,
                              @RequestParam("first_name") String firstName,
                              @RequestParam("last_name") String lastName,
                              @RequestParam("profile") String profile,
                              @RequestParam("class") Long classId,
                              @RequestParam("timetables") Set<Long> timetablesId) {
        teacherDataService.update(id, firstName, lastName, profile, classId, timetableDataService.getTimetablesById(timetablesId));
    }
}
