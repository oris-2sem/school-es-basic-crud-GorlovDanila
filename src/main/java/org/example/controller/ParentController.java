package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.entity.Parent;
import org.example.service.ParentDataService;
import org.example.service.StudentDataService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/parent")
public class ParentController {
    private final ParentDataService parentDataService;
    private final StudentDataService studentDataService;

    @GetMapping
    public Iterable<Parent> showParents() {
        return parentDataService.findAll();
    }

    @PutMapping
    public void createParent(@RequestParam("profile") String profile,
                             @RequestParam("students") Set<Long> studentsId) {
        Parent currentParent = new Parent();
        currentParent.setProfile(profile);
        currentParent.setStudents(studentDataService.getStudentsById(studentsId));
        parentDataService.save(currentParent);
    }

    @DeleteMapping
    public void deleteParent(@RequestParam("parent_id") Long parent_id) {
        parentDataService.findById(parent_id).ifPresent(parentDataService::delete);
    }

    @PatchMapping
    public void updateParent(@RequestParam("id") Long id,
                             @RequestParam("profile") String profile,
                             @RequestParam("students") Set<Long> studentsId) {
        parentDataService.update(id, profile, studentDataService.getStudentsById(studentsId));
    }
}
