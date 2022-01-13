package org.kio55.timetableLab.core.repository.teacherRepository;

import org.kio55.timetableLab.core.model.Teacher;

import java.util.List;

public interface ITeacherRepository {

    List<Teacher> getTeachers();

    Teacher getTeacherById(String teacherId);

    Teacher createTeacher(Teacher newTeacher);

    boolean isExist(String teacherId);

    boolean isEmpty();

    Teacher deleteTeacher(String teacherId);
}
