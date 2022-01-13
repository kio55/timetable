package org.kio55.timetableLab.core.repository.groupRepository;

import org.kio55.timetableLab.core.model.Group;

import java.util.List;

public interface IGroupRepository {

    List<Group> getGroups();

    Group getGroupById(String groupId);

    Group createGroup(Group newGroup);

    boolean isExist(String groupId);

    boolean isEmpty();

    Group deleteGroup(String groupId);
}
