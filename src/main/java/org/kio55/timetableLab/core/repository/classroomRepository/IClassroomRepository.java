package org.kio55.timetableLab.core.repository.classroomRepository;

import org.kio55.timetableLab.core.model.Classroom;

import java.util.List;

public interface IClassroomRepository {

    List<Classroom> getClassrooms();

    Classroom getClassroomById(String classroomId);

    Classroom createClassroom(Classroom newClassroom);

    boolean isExist(String classroomId);

    boolean isEmpty();

    Classroom deleteClassroom(String classroomId);
}
