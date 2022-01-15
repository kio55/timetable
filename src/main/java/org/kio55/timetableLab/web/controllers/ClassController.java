package org.kio55.timetableLab.web.controllers;

import org.kio55.timetableLab.web.model.request.CreateClassRequest;
import org.kio55.timetableLab.web.model.request.DateRequest;
import org.kio55.timetableLab.web.model.response.ClassResponse;
import org.kio55.timetableLab.web.service.classes.IClassService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for work with classes
 */
@Controller
@RequestMapping("/classes")
public class ClassController {
    private final IClassService classService;

    /**
     * Default constructor
     *
     * @param classService service which works with classes
     */
    public ClassController(IClassService classService) {
        this.classService = classService;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<ClassResponse>> getClasses() {
        List<ClassResponse> classList = classService.getClasses();

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(classList);
    }

    @GetMapping(value = "/group/{groupId}")
    @ResponseBody
    public ResponseEntity<List<ClassResponse>> getClassByGroupId(@PathVariable("groupId") final String groupId) {
        try {
            List<ClassResponse> classes = classService.getClassesByGroupId(groupId);
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(classes);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/group/{groupId}/getByDate")
    @ResponseBody
    public ResponseEntity<List<ClassResponse>> getClassByGroupIdAndDate(@PathVariable("groupId") final String groupId,
                                                                        @RequestParam("date") final String date) {
        try {
            List<ClassResponse> classes = classService.getClassesByIdAndDate(groupId, new DateRequest(date));
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(classes);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/teacher/{teacherId}")
    @ResponseBody
    public ResponseEntity<List<ClassResponse>> getClassByTeacherAndDate(@PathVariable("teacherId") final String teacherId,
                                                                        @RequestBody final DateRequest date) {
        try {
            List<ClassResponse> classes = classService.getClassesByTeacherAndDate(teacherId, date);
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(classes);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<ClassResponse> getClassById(@PathVariable("id") final String classId) {
        try {
            ClassResponse classes = classService.getClassById(classId);
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(classes);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<ClassResponse> createClass(@RequestBody final CreateClassRequest addedClass) {
        try {
            ClassResponse response = classService.addClass(addedClass);
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(response);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<ClassResponse> deleteClassById(@PathVariable("id") final String classId) {
        try {
            ClassResponse classes = classService.deleteClass(classId);
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(classes);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
