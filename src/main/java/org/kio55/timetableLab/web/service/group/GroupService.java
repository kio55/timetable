package org.kio55.timetableLab.web.service.group;

import org.kio55.timetableLab.core.model.Group;
import org.kio55.timetableLab.core.repository.groupRepository.IGroupRepository;
import org.kio55.timetableLab.web.model.request.CreateGroupRequest;
import org.kio55.timetableLab.web.model.response.CreateGroupResponse;
import org.kio55.timetableLab.web.model.response.GetGroupResponse;

import java.util.ArrayList;
import java.util.List;

public class GroupService implements IGroupService {
    private final IGroupRepository groupRepository;

    public GroupService(IGroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public boolean isValid(Group group) {
        return true;
    }

    @Override
    public List<GetGroupResponse> getGroups() {
        List<GetGroupResponse> groupsList = new ArrayList<>();

        for (Group group : groupRepository.getGroups()) {
            groupsList.add(new GetGroupResponse(group));
        }
        return groupsList;
    }

    @Override
    public GetGroupResponse getGroupById(String groupId) throws Exception {
        if (groupRepository.isEmpty()) {
            throw new Exception();
        }
        return new GetGroupResponse(groupRepository.getGroupById(groupId));
    }

    @Override
    public CreateGroupResponse addGroup(CreateGroupRequest addedGroup) throws Exception {
        if (groupRepository.isExist(addedGroup.getGroupId())) {
            throw new Exception();
        }
        Group group = new Group(addedGroup.getGroupId(), addedGroup.getTerm(), addedGroup.getYear());

        return new CreateGroupResponse(groupRepository.createGroup(group));
    }

    @Override
    public GetGroupResponse deleteGroup(String deletedGroup) throws Exception {
        if (groupRepository.isEmpty() || !groupRepository.isExist(deletedGroup)) {
            throw new Exception();
        }
        return new GetGroupResponse(groupRepository.deleteGroup(deletedGroup));
    }
}
