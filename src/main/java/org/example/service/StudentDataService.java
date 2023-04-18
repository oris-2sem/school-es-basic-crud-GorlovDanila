package org.example.service;

import lombok.AllArgsConstructor;
import org.example.entity.Parent;
import org.example.entity.Student;
import org.example.entity.Task;
import org.example.repository.ClassRepository;
import org.example.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
public class StudentDataService {

    private StudentRepository studentRepository;
    private ClassRepository classRepository;

    @Transactional
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Transactional
    public void delete(Student student) {
        studentRepository.delete(student);
    }

    @Transactional
    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    @Transactional
    public Iterable<Student> findAll() {
        return studentRepository.findAll();
    }

    @Transactional
    public void update(Long id, String firstName, String lastName, Long class_id, Set<Parent> parents, Set<Task> tasks) {
        Optional<Student> student = studentRepository.findById(id);
        student.ifPresent(currentStudent -> {
            currentStudent.setFirstName(firstName);
            currentStudent.setLastName(lastName);
            currentStudent.setTasks(tasks);
            classRepository.findById(class_id).ifPresent(currentStudent::setClassId);
            currentStudent.setParents(parents);
        });
    }

    public Set<Student> getStudentsById(Set<Long> studentsId) {
        Set<Student> students = new HashSet<>();
        for (Long i : studentsId) {
            studentRepository.findById(i).ifPresent(students::add);
        }
        return students;
    }
}
