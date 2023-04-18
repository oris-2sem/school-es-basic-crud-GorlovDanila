package org.example.service;

import lombok.AllArgsConstructor;
import org.example.entity.Class;
import org.example.entity.Timetable;
import org.example.entity.Teacher;
import org.example.repository.TimetableRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
public class TimetableDataService {
    private TimetableRepository timetableRepository;

    @Transactional
    public void save(Timetable timetable) {
        timetableRepository.save(timetable);
    }

    @Transactional
    public void delete(Timetable timetable) {
        timetableRepository.delete(timetable);
    }

    @Transactional
    public Optional<Timetable> findById(Long id) {
        return timetableRepository.findById(id);
    }

    @Transactional
    public Iterable<Timetable> findAll() {
        return timetableRepository.findAll();
    }

    @Transactional
    public void update(Long id, Time time, Date date, String room, Set<Teacher> teachers, Set<Class> classes) {
        Optional<Timetable> timetable = timetableRepository.findById(id);
        timetable.ifPresent(currentTimetable -> {
            currentTimetable.setTime(time);
            currentTimetable.setDate(date);
            currentTimetable.setRoom(room);
            currentTimetable.setClasses(classes);
            currentTimetable.setTeachers(teachers);
        });
    }

    public Set<Timetable> getTimetablesById(Set<Long> timetablesId) {
        Set<Timetable> timetables = new HashSet<>();
        for (Long i : timetablesId) {
            timetableRepository.findById(i).ifPresent(timetables::add);
        }
        return timetables;
    }
}
