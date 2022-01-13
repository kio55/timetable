package org.kio55.timetableLab.web.controllers;

import org.kio55.timetableLab.web.model.request.CreateTeacherRequest;
import org.kio55.timetableLab.web.model.response.TeacherResponse;
import org.kio55.timetableLab.web.service.teacher.ITeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/teachers")
public class TeacherController {
    private final ITeacherService teacherService;

    public TeacherController(ITeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<TeacherResponse>> getTeachers() {
        List<TeacherResponse> teachersList = teacherService.getTeachers();

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(teachersList);
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<TeacherResponse> getTeacherById(@PathVariable("id") final String teacherId) {
        try {
            TeacherResponse teacher = teacherService.getTeacherById(teacherId);
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(teacher);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<TeacherResponse> createTeacher(@RequestBody final CreateTeacherRequest addedTeacher) {
        try {
            TeacherResponse response = teacherService.addTeacher(addedTeacher);
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(response);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<TeacherResponse> deleteTeacherById(@PathVariable("id") final String teacherId) {
        try {
            TeacherResponse group = teacherService.deleteTeacher(teacherId);
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(group);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
