package org.kio55.timetableLab.web.model.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.kio55.timetableLab.core.model.Classroom;

import java.util.Objects;

public class ClassroomResponse {
    @JsonProperty("classroomId")
    private final String classroomId;
    @JsonProperty("classroom")
    private final String classroom;
    @JsonProperty("universityBuilding")
    private final String universityBuilding;

    @JsonCreator
    public ClassroomResponse(final Classroom classroom) {
        this.classroomId = classroom.getClassroomId();
        this.classroom = classroom.getClassroom();
        this.universityBuilding = classroom.getUniversityBuilding();
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
        ClassroomResponse that = (ClassroomResponse) o;
        return Objects.equals(classroomId, that.classroomId) && Objects.equals(classroom, that.classroom) && Objects.equals(universityBuilding, that.universityBuilding);
    }

    @Override
    public int hashCode() {
        return Objects.hash(classroomId, classroom, universityBuilding);
    }
}
