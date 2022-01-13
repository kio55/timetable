package org.kio55.timetableLab.web.service.teacher;

import org.kio55.timetableLab.core.model.Teacher;
import org.kio55.timetableLab.core.repository.teacherRepository.ITeacherRepository;
import org.kio55.timetableLab.web.model.request.CreateTeacherRequest;
import org.kio55.timetableLab.web.model.response.TeacherResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TeacherService implements ITeacherService {
    private final ITeacherRepository teacherRepository;

    public TeacherService(ITeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }


    @Override
    public boolean isValid(Teacher teacher) {
        return true;
    }

    @Override
    public List<TeacherResponse> getTeachers() {
        List<TeacherResponse> teachersList = new ArrayList<>();
        for (Teacher teacher : teacherRepository.getTeachers()) {
            teachersList.add(new TeacherResponse(teacher));
        }
        return teachersList;
    }

    @Override
    public TeacherResponse getTeacherById(String teacherId) throws Exception {
        if (teacherRepository.isEmpty() || !teacherRepository.isExist(teacherId)) {
            throw new Exception();
        }
        return new TeacherResponse(teacherRepository.getTeacherById(teacherId));
    }

    @Override
    public TeacherResponse addTeacher(CreateTeacherRequest addedTeacher) throws Exception {
        String uniqueID = UUID.randomUUID().toString();
        while (teacherRepository.isExist(uniqueID)) {
            uniqueID = UUID.randomUUID().toString();
        }
        return new TeacherResponse(teacherRepository.createTeacher(new Teacher(uniqueID, addedTeacher.getName(), addedTeacher.getSurname(), addedTeacher.getThirdName())));
    }

    @Override
    public TeacherResponse deleteTeacher(String deletedTeacherId) throws Exception {
        if (teacherRepository.isEmpty() || !teacherRepository.isExist(deletedTeacherId)) {
            throw new Exception();
        }
        return new TeacherResponse(teacherRepository.deleteTeacher(deletedTeacherId));
    }
}
