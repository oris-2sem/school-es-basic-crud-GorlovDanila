package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.entity.Student;
import org.example.service.*;
import org.example.service.StudentDataService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/student")
public class StudentController {

    private final StudentDataService studentDataService;
    private final TaskDataService taskDataService;
    private final ClassDataService classDataService;
    private final ParentDataService parentDataService;

    @GetMapping
    public Iterable<Student> showStudents() {
        return studentDataService.findAll();
    }

    @PutMapping
    public void createStudent(@RequestParam("firstName") String firstName,
                              @RequestParam("lastName") String lastName,
                              @RequestParam("tasks") Set<Long> tasksId,
                              @RequestParam("classId") Long classId,
                              @RequestParam("parents") Set<Long> parentsId) {
        Student currentStudent = new Student();
        currentStudent.setFirstName(firstName);
        currentStudent.setLastName(lastName);
        currentStudent.setTasks(taskDataService.getTasksById(tasksId));
        classDataService.findById(classId).ifPresent(currentStudent::setClassId);
        currentStudent.setParents(parentDataService.getParentsById(parentsId));
        studentDataService.save(currentStudent);
    }

    @DeleteMapping
    public void deleteStudent(@RequestParam("student_id") Long student_id) {
        studentDataService.findById(student_id).ifPresent(studentDataService::delete);
    }

    @PatchMapping
    public void updateStudent(@RequestParam("id") Long id,
                              @RequestParam("firstName") String firstName,
                              @RequestParam("lastName") String lastName,
                              @RequestParam("tasks") Set<Long> tasksId,
                              @RequestParam("classId") Long classId,
                              @RequestParam("parents") Set<Long> parentsId) {
        studentDataService.update(id, firstName, lastName, classId, parentDataService.getParentsById(parentsId), taskDataService.getTasksById(tasksId));
    }
}
