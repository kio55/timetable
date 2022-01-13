package org.kio55.timetableLab.web.controllers;

import org.kio55.timetableLab.web.model.request.CreateTypeRequest;
import org.kio55.timetableLab.web.model.response.TypeResponse;
import org.kio55.timetableLab.web.service.type.ITypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/types")
public class TypeController {
    private final ITypeService typeService;

    public TypeController(ITypeService typeService) {
        this.typeService = typeService;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<TypeResponse>> getTypes() {
        List<TypeResponse> typeList = typeService.getTypes();

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(typeList);
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<TypeResponse> getTypeById(@PathVariable("id") final String typeId) {
        try {
            TypeResponse type = typeService.getTypeById(typeId);
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(type);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<TypeResponse> createType(@RequestBody final CreateTypeRequest addedType) {
        try {
            TypeResponse response = typeService.addType(addedType);
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(response);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<TypeResponse> deleteTypeById(@PathVariable("id") final String typeId) {
        try {
            TypeResponse type = typeService.deleteType(typeId);
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(type);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
