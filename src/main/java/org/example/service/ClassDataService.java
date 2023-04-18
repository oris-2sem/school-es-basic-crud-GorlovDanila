package org.example.service;

import lombok.AllArgsConstructor;
import org.example.entity.Class;
import org.example.entity.Student;
import org.example.entity.Teacher;
import org.example.entity.Timetable;
import org.example.repository.ClassRepository;
import org.example.repository.TeacherRepository;
import org.example.repository.TimetableRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
public class ClassDataService {
    private final ClassRepository classRepository;
    private final TimetableRepository timetableRepository;
    private final TeacherRepository teacherRepository;

    @Transactional
    public void save(Class currentClass) {
        classRepository.save(currentClass);
    }

    @Transactional
    public void delete(Class currentClass) {
        classRepository.delete(currentClass);
    }

    @Transactional
    public Optional<Class> findById(Long id) {
        return classRepository.findById(id);
    }

    @Transactional
    public Iterable<Class> findAll() {
        return classRepository.findAll();
    }


    @Transactional
    public void update(Long id, Date date_insert, String letter, Long timetableId, Long teacherId, Set<Student> students) {
        Optional<Class> currentClass = classRepository.findById(id);
        currentClass.ifPresent(curClass -> {
            curClass.setDateInsert(date_insert);
            curClass.setLetter(letter);
            curClass.setStudents(students);
            Optional<Timetable> timetable = timetableRepository.findById(timetableId);
            timetable.ifPresent(curClass::setTimetable);
            Optional<Teacher> teacher = teacherRepository.findById(teacherId);
            teacher.ifPresent(curClass::setTeacher);
        });
    }

    public Set<Class> getClassesById(Set<Long> classesId) {
        Set<Class> classes = new HashSet<>();
        for (Long i : classesId) {
            classRepository.findById(i).ifPresent(classes::add);
        }
        return classes;
    }
}
