package org.kio55.timetableLab.core.repository.classRepository;

import org.kio55.timetableLab.core.model.Classes;

import java.util.List;

public interface IClassRepository {

    List<Classes> getClasses();

    Classes getClassById(String classId);

    Classes createClass(Classes newClass);

    boolean isExist(String classId);

    boolean isEmpty();

    Classes deleteClass(String classId);
}
