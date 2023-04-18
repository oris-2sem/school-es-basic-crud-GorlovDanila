package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.entity.Timetable;
import org.example.service.ClassDataService;
import org.example.service.TeacherDataService;
import org.example.service.TimetableDataService;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.Date;
import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/timetable")
public class TimetableController {
    private final TimetableDataService timetableDataService;
    private final TeacherDataService teacherDataService;
    private final ClassDataService classDataService;

    @GetMapping
    public Iterable<Timetable> showTimetables() {
        return timetableDataService.findAll();
    }

    @PutMapping
    public void createTimetable(@RequestParam("time") Time time,
                                @RequestParam("date") Date date,
                                @RequestParam("room") String room,
                                @RequestParam("teachers") Set<Long> teachersId,
                                @RequestParam("classes") Set<Long> classesId) {
        Timetable currentTimetable = new Timetable();
        currentTimetable.setTime(time);
        currentTimetable.setDate(date);
        currentTimetable.setRoom(room);
        currentTimetable.setTeachers(teacherDataService.getTeachersById(teachersId));
        currentTimetable.setClasses(classDataService.getClassesById(classesId));
        timetableDataService.save(currentTimetable);
    }

    @DeleteMapping
    public void deleteTimetable(@RequestParam("timetable_id") Long timetable_id) {
        timetableDataService.findById(timetable_id).ifPresent(timetableDataService::delete);
    }

    @PatchMapping
    public void updateTimetable(@RequestParam("id") Long id,
                                @RequestParam("time") Time time,
                                @RequestParam("date") Date date,
                                @RequestParam("room") String room,
                                @RequestParam("teachers") Set<Long> teachersId,
                                @RequestParam("classes") Set<Long> classesId) {
        timetableDataService.update(id,
                time,
                date,
                room,
                teacherDataService.getTeachersById(teachersId),
                classDataService.getClassesById(classesId));
    }
}
