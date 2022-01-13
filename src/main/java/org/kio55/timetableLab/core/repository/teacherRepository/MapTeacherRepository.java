package org.kio55.timetableLab.core.repository.teacherRepository;

import org.kio55.timetableLab.core.model.Teacher;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapTeacherRepository implements ITeacherRepository {
    private final Map<String, Teacher> teacherMap;

    public MapTeacherRepository(Map<String, Teacher> teacherMap) {
        this.teacherMap = teacherMap;
    }

    @Override
    public List<Teacher> getTeachers() {
        return new ArrayList<>(this.teacherMap.values());
    }

    @Override
    public Teacher getTeacherById(String teacherId) {
        return this.teacherMap.getOrDefault(teacherId, null);
    }

    @Override
    public Teacher createTeacher(Teacher newTeacher) {
        this.teacherMap.put(newTeacher.getTeacherId(), newTeacher);
        return newTeacher;
    }

    @Override
    public boolean isExist(String teacherId) {
        return this.teacherMap.containsKey(teacherId);
    }

    @Override
    public boolean isEmpty() {
        return this.teacherMap.isEmpty();
    }

    @Override
    public Teacher deleteTeacher(String teacherId) {
        Teacher deletedTeacher = this.teacherMap.get(teacherId);
        this.teacherMap.remove(teacherId);
        return deletedTeacher;
    }
}
