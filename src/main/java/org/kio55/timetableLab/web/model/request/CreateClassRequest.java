package org.kio55.timetableLab.web.model.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class CreateClassRequest {
    @JsonProperty("groups")
    private final List<String> groupsIds;
    @JsonProperty("name")
    private final String name;
    @JsonProperty("teacher")
    private final String teacherId;
    @JsonProperty("dates")
    private final List<String> dates;
    @JsonProperty("type")
    private final String typeIds;
    @JsonProperty("classroom")
    private final String classroomId;

    @JsonCreator
    public CreateClassRequest(
            @JsonProperty("groups") List<String> groupsIds,
            @JsonProperty("name") String name,
            @JsonProperty("teacher") String teacherId,
            @JsonProperty("dates") List<String> dates,
            @JsonProperty("type") String typeIds,
            @JsonProperty("classroom") String classroomId
    ) {
        this.groupsIds = groupsIds;
        this.name = name;
        this.teacherId = teacherId;
        this.dates = dates;
        this.typeIds = typeIds;
        this.classroomId = classroomId;
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

    public List<String> getDates() {
        return dates;
    }

    public String getTypeIds() {
        return typeIds;
    }

    public String getClassroomId() {
        return classroomId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateClassRequest that = (CreateClassRequest) o;
        return Objects.equals(groupsIds, that.groupsIds) && Objects.equals(name, that.name) && Objects.equals(teacherId, that.teacherId) && Objects.equals(dates, that.dates) && Objects.equals(typeIds, that.typeIds) && Objects.equals(classroomId, that.classroomId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupsIds, name, teacherId, dates, typeIds, classroomId);
    }
}
