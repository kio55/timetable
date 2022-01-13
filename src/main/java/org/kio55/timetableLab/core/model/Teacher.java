package org.kio55.timetableLab.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Teacher {
    private final String teacherId;
    private final String name;
    private final String surname;
    private final String thirdName;

    public Teacher(
            @JsonProperty("teacherId") final String teacherId,
            @JsonProperty("name") final String name,
            @JsonProperty("surname") final String surname,
            @JsonProperty("thirdName") final String thirdName
    ) {
        this.teacherId = teacherId;
        this.name = name;
        this.surname = surname;
        this.thirdName = thirdName;
    }

    public String getTeacherId() {
        return teacherId;
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
        Teacher teacher = (Teacher) o;
        return Objects.equals(teacherId, teacher.teacherId) && Objects.equals(name, teacher.name) && Objects.equals(surname, teacher.surname) && Objects.equals(thirdName, teacher.thirdName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherId, name, surname, thirdName);
    }
}
