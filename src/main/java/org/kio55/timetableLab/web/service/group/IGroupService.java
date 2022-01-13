package org.kio55.timetableLab.web.service.group;

import org.kio55.timetableLab.core.model.Group;
import org.kio55.timetableLab.web.model.request.CreateGroupRequest;
import org.kio55.timetableLab.web.model.response.CreateGroupResponse;
import org.kio55.timetableLab.web.model.response.GetGroupResponse;

import java.util.List;

public interface IGroupService {

    boolean isValid(Group group);

    List<GetGroupResponse> getGroups();

    GetGroupResponse getGroupById(String groupId) throws Exception;

    CreateGroupResponse addGroup(CreateGroupRequest addedGroup) throws Exception;

    GetGroupResponse deleteGroup(String deletedGroupId) throws Exception;
}
