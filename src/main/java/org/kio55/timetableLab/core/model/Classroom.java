package org.kio55.timetableLab.core.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Classroom {
    private final String classroomId;
    private final String classroom;
    private final String universityBuilding;

    @JsonCreator
    public Classroom(
            @JsonProperty("classroomId") final String classroomId,
            @JsonProperty("classroom") final String classroom,
            @JsonProperty("universityBuilding") final String universityBuilding
    ) {
        this.classroomId = classroomId;
        this.classroom = classroom;
        this.universityBuilding = universityBuilding;
    }

    public String getClassroomId() {
        return classroomId;
    }

    public String getClassroom() {
        return classroom;
    }

    public String getUniversityBuilding() {
        return universityBuilding;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Classroom classroom1 = (Classroom) o;
        return Objects.equals(classroomId, classroom1.classroomId) && Objects.equals(classroom, classroom1.classroom) && Objects.equals(universityBuilding, classroom1.universityBuilding);
    }

    @Override
    public int hashCode() {
        return Objects.hash(classroomId, classroom, universityBuilding);
    }
}
