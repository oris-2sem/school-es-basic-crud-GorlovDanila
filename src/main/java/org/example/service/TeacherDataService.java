package org.example.service;

import lombok.AllArgsConstructor;
import org.example.entity.Teacher;
import org.example.entity.Timetable;
import org.example.repository.ClassRepository;
import org.example.repository.TeacherRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
public class TeacherDataService {

    private TeacherRepository teacherRepository;
    private ClassRepository classRepository;

    @Transactional
    public void save(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    @Transactional
    public void delete(Teacher teacher) {
        teacherRepository.delete(teacher);
    }

    @Transactional
    public Optional<Teacher> findById(Long id) {
        return teacherRepository.findById(id);
    }

    @Transactional
    public Iterable<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    @Transactional
    public void update(Long id, String firstName, String lastName, String profile, Long classId, Set<Timetable> timetables) {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        teacher.ifPresent(currentTeacher -> {
            currentTeacher.setFirstName(firstName);
            currentTeacher.setLastName(lastName);
            currentTeacher.setProfile(profile);
            classRepository.findById(classId).ifPresent(currentTeacher::setClassId);
            currentTeacher.setTimetables(timetables);
        });
    }

    public Set<Teacher> getTeachersById(Set<Long> teachersId) {
        Set<Teacher> teachers = new HashSet<>();
        for (Long i : teachersId) {
            teacherRepository.findById(i).ifPresent(teachers::add);
        }
        return teachers;
    }
}
