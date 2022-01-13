package org.kio55.timetableLab.core.repository.groupRepository;

import org.kio55.timetableLab.core.model.Group;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapGroupRepository implements IGroupRepository {
    private final Map<String, Group> groupMap;

    public MapGroupRepository(Map<String, Group> groupMap) {
        this.groupMap = groupMap;
    }

    @Override
    public List<Group> getGroups() {
        return new ArrayList<>(this.groupMap.values());
    }

    @Override
    public Group getGroupById(String groupId) {
        return this.groupMap.getOrDefault(groupId, null);
    }

    @Override
    public Group createGroup(Group newGroup) {
        groupMap.put(newGroup.getGroupId(), newGroup);
        return newGroup;
    }

    @Override
    public boolean isExist(String groupId) {
        return groupMap.containsKey(groupId);
    }

    @Override
    public boolean isEmpty() {
        return groupMap.isEmpty();
    }

    @Override
    public Group deleteGroup(String groupId) {
        Group deletedGroup = groupMap.get(groupId);
        groupMap.remove(groupId);
        return deletedGroup;
    }
}
