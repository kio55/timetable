package org.kio55.timetableLab.web.model.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.kio55.timetableLab.core.model.Classroom;

import java.util.Objects;

public class CreateClassroomRequest {
    @JsonProperty("classroom")
    private final String classroom;
    @JsonProperty("universityBuilding")
    private final String universityBuilding;

    @JsonCreator
    public CreateClassroomRequest(final Classroom classroom) {
        this.classroom = classroom.getClassroom();
        this.universityBuilding = classroom.getUniversityBuilding();
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
        CreateClassroomRequest that = (CreateClassroomRequest) o;
        return Objects.equals(classroom, that.classroom) && Objects.equals(universityBuilding, that.universityBuilding);
    }

    @Override
    public int hashCode() {
        return Objects.hash(classroom, universityBuilding);
    }
}
