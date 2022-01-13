package org.kio55.timetableLab.web.model.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.kio55.timetableLab.core.model.Teacher;

import java.util.Objects;

public class CreateTeacherRequest {
    @JsonProperty("name")
    private final String name;
    @JsonProperty("surname")
    private final String surname;
    @JsonProperty("thirdName")
    private final String thirdName;

    @JsonCreator
    public CreateTeacherRequest(final Teacher teacher) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateTeacherRequest that = (CreateTeacherRequest) o;
        return Objects.equals(name, that.name) && Objects.equals(surname, that.surname) && Objects.equals(thirdName, that.thirdName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, thirdName);
    }
}
