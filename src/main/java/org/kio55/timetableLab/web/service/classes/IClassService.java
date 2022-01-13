package org.kio55.timetableLab.web.service.classes;

import org.kio55.timetableLab.web.model.request.CreateClassRequest;
import org.kio55.timetableLab.web.model.request.DateRequest;
import org.kio55.timetableLab.web.model.response.ClassResponse;

import java.util.List;

public interface IClassService {
    boolean isValid(CreateClassRequest request);

    List<ClassResponse> getClasses();

    ClassResponse getClassById(String classId) throws Exception;

    ClassResponse addClass(CreateClassRequest addedClass) throws Exception;

    ClassResponse deleteClass(String deletedClass) throws Exception;

    List<ClassResponse> getClassesByGroupId(String groupId) throws Exception;

    List<ClassResponse> getClassesByIdAndDate(String groupId, DateRequest date) throws Exception;

    List<ClassResponse> getClassesByTeacherAndDate(String teacherId, DateRequest date) throws Exception;
}
