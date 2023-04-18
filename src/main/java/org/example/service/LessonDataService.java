package org.example.service;

import lombok.AllArgsConstructor;
import org.example.entity.Lesson;
import org.example.entity.Task;
import org.example.repository.LessonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
public class LessonDataService {
    private LessonRepository lessonRepository;

    @Transactional
    public void save(Lesson lesson) {
        lessonRepository.save(lesson);
    }

    @Transactional
    public void delete(Lesson lesson) {
        lessonRepository.delete(lesson);
    }

    @Transactional
    public Optional<Lesson> findById(Long id) {
        return lessonRepository.findById(id);
    }

    @Transactional
    public Iterable<Lesson> findAll() {
        return lessonRepository.findAll();
    }

    @Transactional
    public void update(Long id, String theme, String subject, Set<Task> tasks) {
        Optional<Lesson> lesson = lessonRepository.findById(id);
        lesson.ifPresent(currentLesson -> {
            currentLesson.setTheme(theme);
            currentLesson.setSubject(subject);
            currentLesson.setTasks(tasks);
        });
    }
}
