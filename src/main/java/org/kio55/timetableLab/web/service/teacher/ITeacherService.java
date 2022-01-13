package org.kio55.timetableLab.web.service.teacher;

import org.kio55.timetableLab.core.model.Teacher;
import org.kio55.timetableLab.web.model.request.CreateTeacherRequest;
import org.kio55.timetableLab.web.model.response.TeacherResponse;

import java.util.List;

public interface ITeacherService {

    boolean isValid(Teacher teacher);

    List<TeacherResponse> getTeachers();

    TeacherResponse getTeacherById(String teacherId) throws Exception;

    TeacherResponse addTeacher(CreateTeacherRequest addedTeacher) throws Exception;

    TeacherResponse deleteTeacher(String deletedTeacher) throws Exception;
}
