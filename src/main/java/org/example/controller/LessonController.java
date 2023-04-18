package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.entity.Lesson;
import org.example.service.LessonDataService;
import org.example.service.TaskDataService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/lesson")
public class LessonController {
    private final LessonDataService lessonDataService;
    private final TaskDataService taskDataService;

    @GetMapping
    public Iterable<Lesson> showLessons() {
        return lessonDataService.findAll();
    }

    @PutMapping
    public void createLesson(@RequestParam("theme") String theme,
                             @RequestParam("subject") String subject,
                             @RequestParam("tasks") Set<Long> tasksId) {
        Lesson currentLesson = new Lesson();
        currentLesson.setTheme(theme);
        currentLesson.setSubject(subject);
        currentLesson.setTasks(taskDataService.getTasksById(tasksId));
        lessonDataService.save(currentLesson);
    }

    @DeleteMapping
    public void deleteLesson(@RequestParam("lesson_id") Long lesson_id) {
        lessonDataService.findById(lesson_id).ifPresent(lessonDataService::delete);
    }

    @PatchMapping
    public void updateLesson(@RequestParam("id") Long id,
                             @RequestParam("theme") String theme,
                             @RequestParam("subject") String subject,
                             @RequestParam("tasks") Set<Long> tasksId) {
        lessonDataService.update(id, theme, subject, taskDataService.getTasksById(tasksId));
    }
}
