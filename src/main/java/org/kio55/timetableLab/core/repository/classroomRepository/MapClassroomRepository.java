package org.kio55.timetableLab.core.repository.classroomRepository;

import org.kio55.timetableLab.core.model.Classroom;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapClassroomRepository implements IClassroomRepository {
    private final Map<String, Classroom> classroomMap;

    public MapClassroomRepository(Map<String, Classroom> classroomMap) {
        this.classroomMap = classroomMap;
    }

    @Override
    public List<Classroom> getClassrooms() {
        return new ArrayList<>(this.classroomMap.values());
    }

    @Override
    public Classroom getClassroomById(String classroomId) {
        return this.classroomMap.getOrDefault(classroomId, null);
    }

    @Override
    public Classroom createClassroom(Classroom newClassroom) {
        this.classroomMap.put(newClassroom.getClassroomId(), newClassroom);
        return newClassroom;
    }

    @Override
    public boolean isExist(String classroomId) {
        return this.classroomMap.containsKey(classroomId);
    }

    @Override
    public boolean isEmpty() {
        return this.classroomMap.isEmpty();
    }

    @Override
    public Classroom deleteClassroom(String classroomId) {
        Classroom deletedClassroom = classroomMap.get(classroomId);
        this.classroomMap.remove(classroomId);
        return deletedClassroom;
    }
}
