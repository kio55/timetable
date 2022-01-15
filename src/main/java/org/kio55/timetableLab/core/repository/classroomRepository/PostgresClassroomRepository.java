package org.kio55.timetableLab.core.repository.classroomRepository;

import org.kio55.timetableLab.core.model.Classroom;
import org.springframework.jdbc.core.JdbcOperations;

import java.util.List;

public class PostgresClassroomRepository implements IClassroomRepository {
    private final JdbcOperations jdbcOperations;

    public PostgresClassroomRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public List<Classroom> getClassrooms() {
        return jdbcOperations.query("SELECT classroomid, classroom, universitybuilding FROM classroom ORDER BY classroomid",
                (resultSet, i) -> new Classroom(
                    resultSet.getString("classroomid"),
                        resultSet.getString("classroom"),
                        resultSet.getString("universitybuilding")
                    )
                );
    }

    @Override
    public Classroom getClassroomById(String classroomId) {
        return jdbcOperations.queryForObject(
                "SELECT classroomid, classroom, universitybuilding FROM classroom WHERE classroomid = ?",
                (resultSet, i) -> new Classroom(
                        resultSet.getString("classroomid"),
                        resultSet.getString("classroom"),
                        resultSet.getString("universitybuilding")
                ),
                classroomId
        );
    }

    @Override
    public Classroom createClassroom(Classroom newClassroom) {
        jdbcOperations.update("INSERT INTO classroom (classroomid, classroom, universitybuilding) VALUES (?, ?, ?)",
                newClassroom.getClassroomId(), newClassroom.getClassroom(), newClassroom.getUniversityBuilding());
        return newClassroom;
    }

    @Override
    public boolean isExist(String classroomId) {
        return jdbcOperations.query(
                "SELECT classroomid, classroom, universitybuilding FROM classroom WHERE classroomid = ?",
                (resultSet, i) -> new Classroom(
                        resultSet.getString("classroomid"),
                        resultSet.getString("classroom"),
                        resultSet.getString("universitybuilding")
                ),
                classroomId
        ).size() > 0;
    }

    @Override
    public boolean isEmpty() {
        return Boolean.FALSE.equals(jdbcOperations.queryForObject("select count(1) where exists (select * from classroom)", boolean.class));
    }

    @Override
    public Classroom deleteClassroom(String classroomId) {
        Classroom classroom = getClassroomById(classroomId);
        jdbcOperations.update("DELETE FROM classroom WHERE classroomid = ?", classroomId);
        return classroom;
    }
}
