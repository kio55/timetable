package org.kio55.timetableLab.web.service.classroom;

import org.kio55.timetableLab.core.model.Classes;
import org.kio55.timetableLab.core.model.Classroom;
import org.kio55.timetableLab.core.repository.classRepository.IClassRepository;
import org.kio55.timetableLab.core.repository.classroomRepository.IClassroomRepository;
import org.kio55.timetableLab.web.model.request.CreateClassroomRequest;
import org.kio55.timetableLab.web.model.request.DateRequest;
import org.kio55.timetableLab.web.model.response.ClassroomResponse;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ClassroomService implements IClassroomService {
    private final IClassroomRepository classroomRepository;
    private final IClassRepository classRepository;

    public ClassroomService(IClassroomRepository classroomRepository, IClassRepository classRepository) {
        this.classroomRepository = classroomRepository;
        this.classRepository = classRepository;
    }

    @Override
    public List<ClassroomResponse> getClassrooms() {
        List<ClassroomResponse> classroomsList = new ArrayList<>();
        for (Classroom classroom : classroomRepository.getClassrooms()) {
            classroomsList.add(new ClassroomResponse(classroom));
        }
        return classroomsList;
    }

    @Override
    public ClassroomResponse getClassroomById(String classroomId) throws Exception {
        if (classroomRepository.isEmpty() || !classroomRepository.isExist(classroomId)) {
            throw new Exception();
        }
        return new ClassroomResponse(classroomRepository.getClassroomById(classroomId));
    }

    @Override
    public ClassroomResponse addClassroom(CreateClassroomRequest addedClassroom) throws Exception {
        String uniqueID = UUID.randomUUID().toString();
        while (classroomRepository.isExist(uniqueID)) {
            uniqueID = UUID.randomUUID().toString();
        }
        return new ClassroomResponse(classroomRepository.createClassroom(new Classroom(uniqueID, addedClassroom.getClassroom(), addedClassroom.getUniversityBuilding())));
    }

    @Override
    public ClassroomResponse deleteClassroom(String deletedClassroom) throws Exception {
        if (classroomRepository.isEmpty() || !classroomRepository.isExist(deletedClassroom)) {
            throw new Exception();
        }
        return new ClassroomResponse(classroomRepository.deleteClassroom(deletedClassroom));
    }

    @Override
    public List<ClassroomResponse> findFreeClassroom(DateRequest date) throws Exception {
        if (classroomRepository.isEmpty()) {
            throw new Exception();
        }
        List<ClassroomResponse> response = new ArrayList<>();
        for (Classroom classroom: classroomRepository.getClassrooms()) {
            response.add(new ClassroomResponse(classroom));
        }
        if (classRepository.isEmpty()) {
            return response;
        }
        for (Classes classes : classRepository.getClasses()) {
            ClassroomResponse currentClass = new ClassroomResponse(classroomRepository.getClassroomById(classes.getClassroomId()));
            for (LocalDateTime dateTime : classes.getDates()) {
                if (dateTime.equals(LocalDateTime.parse(date.getDate())) && response.contains(currentClass)) {
                    response.remove(currentClass);
                    break;
                }
            }
        }

        return response;
    }
}
