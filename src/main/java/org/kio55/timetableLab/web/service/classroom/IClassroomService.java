package org.kio55.timetableLab.web.service.classroom;

import org.kio55.timetableLab.web.model.request.CreateClassroomRequest;
import org.kio55.timetableLab.web.model.request.DateRequest;
import org.kio55.timetableLab.web.model.response.ClassroomResponse;

import java.util.List;

public interface IClassroomService {
    List<ClassroomResponse> getClassrooms();

    ClassroomResponse getClassroomById(String classroomId) throws Exception;

    ClassroomResponse addClassroom(CreateClassroomRequest addedClassroom) throws Exception;

    ClassroomResponse deleteClassroom(String deletedClassroom) throws Exception;

    List<ClassroomResponse> findFreeClassroom(DateRequest date) throws Exception;
}
