package org.kio55.timetableLab.web.model.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.kio55.timetableLab.core.model.Classroom;
import org.kio55.timetableLab.core.model.Group;
import org.kio55.timetableLab.core.model.Teacher;
import org.kio55.timetableLab.core.model.Type;

import java.util.List;
import java.util.Objects;

public class ClassResponse {
    @JsonProperty("classId")
    private final String classId;
    @JsonProperty("groups")
    private final List<Group> groups;
    @JsonProperty("name")
    private final String name;
    @JsonProperty("teacher")
    private final Teacher teacher;
    @JsonProperty("dates")
    private final List<String> dates;
    @JsonProperty("type")
    private final Type type;
    @JsonProperty("classroom")
    private final Classroom classroom;

    @JsonCreator
    public ClassResponse(String classId, List<Group> groups, String name, Teacher teacher, List<String> dates, Type type, Classroom classroom) {
        this.classId = classId;
        this.groups = groups;
        this.name = name;
        this.teacher = teacher;
        this.dates = dates;
        this.type = type;
        this.classroom = classroom;
    }

    public String getClassId() {
        return classId;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public String getName() {
        return name;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public List<String> getDates() {
        return dates;
    }

    public Type getType() {
        return type;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassResponse that = (ClassResponse) o;
        return Objects.equals(classId, that.classId) && Objects.equals(groups, that.groups) && Objects.equals(name, that.name) && Objects.equals(teacher, that.teacher) && Objects.equals(dates, that.dates) && Objects.equals(type, that.type) && Objects.equals(classroom, that.classroom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(classId, groups, name, teacher, dates, type, classroom);
    }
}
