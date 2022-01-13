package org.kio55.timetableLab.web.controllers;

import org.kio55.timetableLab.web.model.request.CreateClassroomRequest;
import org.kio55.timetableLab.web.model.request.DateRequest;
import org.kio55.timetableLab.web.model.response.ClassroomResponse;
import org.kio55.timetableLab.web.service.classroom.IClassroomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/classrooms")
public class ClassroomController {
    private final IClassroomService classroomService;

    public ClassroomController(IClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<ClassroomResponse>> getClassrooms() {
        List<ClassroomResponse> classroomList = classroomService.getClassrooms();

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(classroomList);
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<ClassroomResponse> getClassroomById(@PathVariable("id") final String classroomId) {
        try {
            ClassroomResponse classroom = classroomService.getClassroomById(classroomId);
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(classroom);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<ClassroomResponse> createClassroom(@RequestBody final CreateClassroomRequest addedClassroom) {
        try {
            ClassroomResponse response = classroomService.addClassroom(addedClassroom);
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(response);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<ClassroomResponse> deleteClassroomById(@PathVariable("id") final String classroomId) {
        try {
            ClassroomResponse classroom = classroomService.deleteClassroom(classroomId);
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(classroom);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/free")
    @ResponseBody
    public ResponseEntity<List<ClassroomResponse>> findFree(@RequestBody final DateRequest date) {
        try {
            List<ClassroomResponse> response = classroomService.findFreeClassroom(date);
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(response);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
