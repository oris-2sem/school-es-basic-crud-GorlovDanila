package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.entity.Task;
import org.example.service.ClassDataService;
import org.example.service.LessonDataService;
import org.example.service.TaskDataService;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/task")
public class TaskController {
    private final TaskDataService taskDataService;
    private final LessonDataService lessonDataService;
    private final ClassDataService classDataService;

    @GetMapping
    public Iterable<Task> showTasks() {
        return taskDataService.findAll();
    }

    @PutMapping
    public void createTask(@RequestParam("commentary") String commentary,
                           @RequestParam("description") String description,
                           @RequestParam("mark") Integer mark,
                           @RequestParam("type") String type,
                           @RequestParam("lesson_id") Long lessonId,
                           @RequestParam("class_id") Long classId) {
        Task currentTask = new Task();
        currentTask.setCommentary(commentary);
        currentTask.setDescription(description);
        lessonDataService.findById(lessonId).ifPresent(currentTask::setLesson);
        currentTask.setMark(mark);
        currentTask.setType(type);
        classDataService.findById(classId).ifPresent(curClass -> currentTask.setStudents(curClass.getStudents()));
        taskDataService.save(currentTask);
    }

    @DeleteMapping
    public void deleteTask(@RequestParam("task_id") Long task_id) {
        taskDataService.findById(task_id).ifPresent(taskDataService::delete);
    }

    @PatchMapping
    public void updateTask(@RequestParam("id") Long id,
                           @RequestParam("commentary") String commentary,
                           @RequestParam("description") String description,
                           @RequestParam("mark") Integer mark,
                           @RequestParam("type") String type,
                           @RequestParam("lesson_id") Long lessonId,
                           @RequestParam("class_id") Long classId) {
        taskDataService.update(id, commentary, description, mark, type, lessonId, classId);
    }
}
