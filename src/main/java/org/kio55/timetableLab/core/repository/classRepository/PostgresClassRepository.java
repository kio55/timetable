package org.kio55.timetableLab.core.repository.classRepository;

import org.kio55.timetableLab.core.model.Classes;
import org.kio55.timetableLab.core.model.Group;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PostgresClassRepository implements IClassRepository {
    private final JdbcOperations jdbcOperations;

    public PostgresClassRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Transactional
    @Override
    public List<Classes> getClasses() {
        return jdbcOperations.query("SELECT classid FROM class",
                (resultSet, i) -> getClassById(resultSet.getString("classid")));
    }

    @Transactional
    @Override
    public Classes getClassById(String classId) {
        List<String> groupIds = jdbcOperations.query("SELECT groupid FROM groupclass WHERE classid =?",
                (resultSet, i) -> resultSet.getString("groupid"), classId);
        List<String> stringDates = jdbcOperations.query("SELECT date FROM \"date\" WHERE classid =?",
                (resultSet, i) -> resultSet.getString("date"), classId);
        List<LocalDateTime> dates = new ArrayList<>();
        for (String date: stringDates) {
            LocalDateTime dateTime = LocalDateTime.parse(date);
            dates.add(dateTime);
        }
        return jdbcOperations.queryForObject("SELECT classid, name, teacherid, typeid, classroomid FROM class WHERE classid=?",
                (resultSet, i) -> new Classes(
                        resultSet.getString("classid"),
                        groupIds,
                        resultSet.getString("name"),
                        resultSet.getString("teacherid"),
                        dates,
                        resultSet.getString("typeid"),
                        resultSet.getString("classroomid")
                ), classId);
    }

    @Transactional
    @Override
    public Classes createClass(Classes newClass) {
        jdbcOperations.update("INSERT INTO class (classid, name, teacherid, typeid, classroomid) VALUES (?, ?, ?, ?, ?)",
                newClass.getClassId(), newClass.getName(), newClass.getTeacherId(), newClass.getTypeId(), newClass.getClassroomId());
        for (LocalDateTime date: newClass.getDates()) {
            jdbcOperations.update("INSERT INTO \"date\" (dateid, classid, date) VALUES (?, ?, ?)",
                    UUID.randomUUID().toString(), newClass.getClassId(), date.toString());
        }
        for (String groupId: newClass.getGroupsIds()) {
            jdbcOperations.update("INSERT INTO groupclass (groupclassid, groupid, classid) VALUES (?, ?, ?)",
                    UUID.randomUUID().toString(), groupId, newClass.getClassId());
        }
        return newClass;
    }

    @Override
    public boolean isExist(String classId) {
        return jdbcOperations.query("SELECT classid FROM class WHERE classid = ?",
                (resultSet, i) -> resultSet.getString("classid")
                , classId).size() > 0;
    }

    @Override
    public boolean isEmpty() {
        return Boolean.FALSE.equals(jdbcOperations.queryForObject("select count(1) where exists (select * from class)", boolean.class));
    }

    @Transactional
    @Override
    public Classes deleteClass(String classId) {
        Classes deletedClass = getClassById(classId);
        jdbcOperations.update("DELETE FROM class WHERE classid=?", classId);
        jdbcOperations.update("DELETE FROM \"date\" WHERE classid=?", classId);
        jdbcOperations.update("DELETE FROM groupclass WHERE classid=?", classId);
        return deletedClass;
    }
}
