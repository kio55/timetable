package org.kio55.timetableLab.web.service.classes;

import org.kio55.timetableLab.core.model.*;
import org.kio55.timetableLab.core.repository.classRepository.IClassRepository;
import org.kio55.timetableLab.core.repository.classroomRepository.IClassroomRepository;
import org.kio55.timetableLab.core.repository.groupRepository.IGroupRepository;
import org.kio55.timetableLab.core.repository.teacherRepository.ITeacherRepository;
import org.kio55.timetableLab.core.repository.typeRepository.ITypeRepository;
import org.kio55.timetableLab.web.model.request.CreateClassRequest;
import org.kio55.timetableLab.web.model.request.DateRequest;
import org.kio55.timetableLab.web.model.response.ClassResponse;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ClassService implements IClassService {
    private final IClassroomRepository classroomRepository;
    private final IClassRepository classRepository;
    private final IGroupRepository groupRepository;
    private final ITeacherRepository teacherRepository;
    private final ITypeRepository typeRepository;

    public ClassService(IClassroomRepository classroomRepository, IClassRepository classRepository, IGroupRepository groupRepository, ITeacherRepository teacherRepository, ITypeRepository typeRepository) {
        this.classroomRepository = classroomRepository;
        this.classRepository = classRepository;
        this.groupRepository = groupRepository;
        this.teacherRepository = teacherRepository;
        this.typeRepository = typeRepository;
    }

    private ClassResponse responseFromClass(Classes classes) {
        List<Group> groups = new ArrayList<>();
        for (String groupId: classes.getGroupsIds()) {
            try {
                groups.add(groupRepository.getGroupById(groupId));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Teacher teacher = null;
        try {
            teacher = teacherRepository.getTeacherById(classes.getTeacherId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Type type = null;
        try {
            type = typeRepository.getTypeById(classes.getTypeId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Classroom classroom = null;
        try {
            classroom = classroomRepository.getClassroomById(classes.getClassroomId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<String> dates = new ArrayList<>();
        for (LocalDateTime date : classes.getDates()) {
            dates.add(date.toString());
        }
        return new ClassResponse(classes.getClassId(), groups, classes.getName(), teacher, dates, type, classroom);
    }

    @Override
    public boolean isValid(CreateClassRequest request) {
        boolean checkGroups = !groupRepository.isEmpty();
        for (String groupId : request.getGroupsIds()) {
            checkGroups = checkGroups && groupRepository.isExist(groupId);
        }
        return classroomRepository.isExist(request.getClassroomId()) && checkGroups && teacherRepository.isExist(request.getTeacherId()) && typeRepository.isExist(request.getTypeIds());
    }

    @Override
    public List<ClassResponse> getClasses() {
        List<ClassResponse> response = new ArrayList<>();
        for (Classes classes : classRepository.getClasses()) {
            response.add(responseFromClass(classes));
        }
        return response;
    }

    @Override
    public ClassResponse getClassById(String classId) throws Exception {
        if (classRepository.isEmpty() || !classRepository.isExist(classId)) {
            throw new Exception();
        }
        return responseFromClass(classRepository.getClassById(classId));
    }

    @Override
    public ClassResponse addClass(CreateClassRequest addedClass) throws Exception {
        String uniqueID = UUID.randomUUID().toString();
        while (classroomRepository.isExist(uniqueID)) {
            uniqueID = UUID.randomUUID().toString();
        }
        if (!isValid(addedClass)) {
            throw new Exception();
        }
        List<LocalDateTime> dates = new ArrayList<>();
        for (String date : addedClass.getDates()) {
            LocalDateTime dateTime = LocalDateTime.parse(date);
            dates.add(dateTime);
        }
        Classes added = new Classes(uniqueID, addedClass.getGroupsIds(), addedClass.getName(), addedClass.getTeacherId(), dates, addedClass.getTypeIds() ,addedClass.getClassroomId());
        classRepository.createClass(added);
        return responseFromClass(added);
    }

    @Override
    public ClassResponse deleteClass(String deletedClass) throws Exception {
        if (classRepository.isEmpty() || !classRepository.isExist(deletedClass)) {
            throw new Exception();
        }

        return responseFromClass(classRepository.deleteClass(deletedClass));
    }

    @Override
    public List<ClassResponse> getClassesByGroupId(String groupId) throws Exception {
        if (classRepository.isEmpty() || groupRepository.isEmpty() || !groupRepository.isExist(groupId)) {
            throw new Exception();
        }

        List<ClassResponse> classResponses = new ArrayList<>();
        for (Classes classes : classRepository.getClasses()) {
            List<String> groups = classes.getGroupsIds();
            for (String id: groups) {
                if (id.equals(groupId)) {
                    classResponses.add(responseFromClass(classes));
                    break;
                }
            }
        }
        return classResponses;
    }

    @Override
    public List<ClassResponse> getClassesByIdAndDate(String groupId, DateRequest date) throws Exception {
        if (classRepository.isEmpty() || groupRepository.isEmpty() || !groupRepository.isExist(groupId)) {
            throw new Exception();
        }

        List<ClassResponse> classesList = getClassesByGroupId(groupId);

        List<ClassResponse> response = new ArrayList<>();

        LocalDateTime dateTime = LocalDateTime.parse(date.getDate());

        for (ClassResponse classes : classesList) {
            List<String> classDates = classes.getDates();
            for (String classDate : classDates) {
                LocalDateTime classDateTime = LocalDateTime.parse(classDate);
                if (classDateTime.getYear() == dateTime.getYear()
                        && classDateTime.getMonth() == dateTime.getMonth()
                        && classDateTime.getDayOfMonth() == dateTime.getDayOfMonth()) {
                    response.add(classes);
                    break;
                }
            }
        }

        return response;
    }

    @Override
    public List<ClassResponse> getClassesByTeacherAndDate(String teacherId, DateRequest date) throws Exception {
        if (classRepository.isEmpty() || teacherRepository.isEmpty() || !teacherRepository.isExist(teacherId)) {
            throw new Exception();
        }
        LocalDateTime searchedTime = LocalDateTime.parse(date.getDate());
        List<ClassResponse> classesResponseList = new ArrayList<>();
        for (Classes classes : classRepository.getClasses()) {
            if (classes.getTeacherId().equals(teacherId)) {
                for (LocalDateTime dateTime : classes.getDates()) {
                    if (dateTime.getYear() == searchedTime.getYear()
                            && dateTime.getMonth() == searchedTime.getMonth()
                            && dateTime.getDayOfMonth() == searchedTime.getDayOfMonth()
                    ) {
                        classesResponseList.add(responseFromClass(classes));
                        break;
                    }
                }
            }
        }
        return classesResponseList;
    }
}
