package org.kio55.timetableLab.core.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Classes {
    private final String classId;
    private final List<String> groupsIds;
    private final String name;
    private final String teacherId;
    private final List<LocalDateTime> dates;
    private final String typeId;
    private final String classroomId;


    public Classes(
            final String classId,
            final List<String> groupsIds,
            final String name,
            final String teacherId,
            final List<LocalDateTime> dates,
            final String typeId,
            final String classroomId
    ) {
        this.classId = classId;
        this.groupsIds = groupsIds;
        this.name = name;
        this.teacherId = teacherId;
        this.dates = dates;
        this.typeId = typeId;
        this.classroomId = classroomId;
    }

    public String getClassId() {
        return classId;
    }

    public List<String> getGroupsIds() {
        return groupsIds;
    }

    public String getName() {
        return name;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public List<LocalDateTime> getDates() {
        return dates;
    }

    public String getTypeId() {
        return typeId;
    }

    public String getClassroomId() {
        return classroomId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Classes classes = (Classes) o;
        return Objects.equals(classId, classes.classId) && Objects.equals(groupsIds, classes.groupsIds) && Objects.equals(name, classes.name) && Objects.equals(teacherId, classes.teacherId) && Objects.equals(dates, classes.dates) && Objects.equals(typeId, classes.typeId) && Objects.equals(classroomId, classes.classroomId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(classId, groupsIds, name, teacherId, dates, typeId, classroomId);
    }
}
