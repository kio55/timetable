package org.kio55.timetableLab.web.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.kio55.timetableLab.core.model.Teacher;

import java.util.Objects;

public class TeacherResponse {
    @JsonProperty("teacherId")
    private final String teacherId;
    @JsonProperty("name")
    private final String name;
    @JsonProperty("surname")
    private final String surname;
    @JsonProperty("thirdName")
    private final String thirdName;

    public TeacherResponse(final Teacher teacher) {
        this.teacherId = teacher.getTeacherId();
        this.name = teacher.getName();
        this.surname = teacher.getSurname();
        this.thirdName = teacher.getThirdName();
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getThirdName() {
        return thirdName;
    }

    public String getTeacherId() {
        return teacherId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeacherResponse that = (TeacherResponse) o;
        return Objects.equals(teacherId, that.teacherId) && Objects.equals(name, that.name) && Objects.equals(surname, that.surname) && Objects.equals(thirdName, that.thirdName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherId, name, surname, thirdName);
    }
}
