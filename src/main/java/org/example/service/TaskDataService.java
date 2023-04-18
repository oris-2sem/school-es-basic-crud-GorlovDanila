package org.example.service;

import lombok.AllArgsConstructor;
import org.example.entity.Task;
import org.example.repository.ClassRepository;
import org.example.repository.LessonRepository;
import org.example.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
public class TaskDataService {

    private TaskRepository taskRepository;
    private LessonRepository lessonRepository;

    private ClassRepository classRepository;

    @Transactional
    public void save(Task task) {
        taskRepository.save(task);
    }

    @Transactional
    public void delete(Task task) {
        taskRepository.delete(task);
    }

    @Transactional
    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }

    @Transactional
    public Iterable<Task> findAll() {
        return taskRepository.findAll();
    }

    @Transactional
    public void update(Long id, String commentary, String description, Integer mark, String type, Long lessonId, Long classId) {
        Optional<Task> task = taskRepository.findById(id);
        task.ifPresent(currentTask -> {
            currentTask.setCommentary(commentary);
            currentTask.setDescription(description);
            currentTask.setMark(mark);
            currentTask.setType(type);
            lessonRepository.findById(lessonId).ifPresent(currentTask::setLesson);
            classRepository.findById(classId).ifPresent(curClass -> currentTask.setStudents(curClass.getStudents()));
        });
    }

    public Set<Task> getTasksById(Set<Long> tasksId) {
        Set<Task> tasks = new HashSet<>();
        for (Long i : tasksId) {
            taskRepository.findById(i).ifPresent(tasks::add);
        }
        return tasks;
    }
}
