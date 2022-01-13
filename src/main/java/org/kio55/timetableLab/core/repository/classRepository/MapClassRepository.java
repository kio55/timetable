package org.kio55.timetableLab.core.repository.classRepository;

import org.kio55.timetableLab.core.model.Classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapClassRepository implements IClassRepository {
    private final Map<String, Classes> classMap;

    public MapClassRepository(Map<String, Classes> classMap) {
        this.classMap = classMap;
    }


    @Override
    public List<Classes> getClasses() {
        return new ArrayList<>(this.classMap.values());
    }

    @Override
    public Classes getClassById(String classId) {
        return this.classMap.getOrDefault(classId, null);
    }

    @Override
    public Classes createClass(Classes newClass) {
        this.classMap.put(newClass.getClassId(), newClass);
        return newClass;
    }

    @Override
    public boolean isExist(String classId) {
        return this.classMap.containsKey(classId);
    }

    @Override
    public boolean isEmpty() {
        return this.classMap.isEmpty();
    }

    @Override
    public Classes deleteClass(String classId) {
        Classes deletedClass = this.classMap.get(classId);
        this.classMap.remove(classId);
        return deletedClass;
    }
}
