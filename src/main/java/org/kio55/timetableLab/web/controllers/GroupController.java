package org.kio55.timetableLab.web.controllers;

import org.kio55.timetableLab.core.model.Group;
import org.kio55.timetableLab.web.model.request.CreateGroupRequest;
import org.kio55.timetableLab.web.model.response.CreateGroupResponse;
import org.kio55.timetableLab.web.model.response.GetGroupResponse;
import org.kio55.timetableLab.web.service.group.IGroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/groups")
public class GroupController {
    private final IGroupService groupService;

    public GroupController(IGroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<GetGroupResponse>> getGroups() {
        List<GetGroupResponse> groupsList = groupService.getGroups();

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(groupsList);
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<GetGroupResponse> getGroupById(@PathVariable("id") final String groupId) {
        try {
            GetGroupResponse group = groupService.getGroupById(groupId);
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(group);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<CreateGroupResponse> createGroup(@RequestBody final CreateGroupRequest addedGroup) {
        try {
            CreateGroupResponse response = groupService.addGroup(addedGroup);
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(response);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<GetGroupResponse> deleteGroupById(@PathVariable("id") final String groupId) {
        try {
            GetGroupResponse group = groupService.deleteGroup(groupId);
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(group);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
