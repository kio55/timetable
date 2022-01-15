package org.kio55.timetableLab.core.repository.teacherRepository;

import org.kio55.timetableLab.core.model.Teacher;
import org.springframework.jdbc.core.JdbcOperations;

import java.util.List;

public class PostgresTeacherRepository implements ITeacherRepository {
    private final JdbcOperations jdbcOperations;

    public PostgresTeacherRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public List<Teacher> getTeachers() {
        return jdbcOperations.query("SELECT teacherid, name, surname, thirdname FROM teacher ORDER BY teacherid", (resultSet, i) -> new Teacher(
                resultSet.getString("teacherid"),
                resultSet.getString("name"),
                resultSet.getString("surname"),
                resultSet.getString("thirdname")
        ));
    }

    @Override
    public Teacher getTeacherById(String teacherId) {
        return jdbcOperations.queryForObject("SELECT teacherid, name, surname, thirdname FROM teacher WHERE teacherid=?", (resultSet, i) -> new Teacher(
                resultSet.getString("teacherid"),
                resultSet.getString("name"),
                resultSet.getString("surname"),
                resultSet.getString("thirdname")
        ) , teacherId);
    }

    @Override
    public Teacher createTeacher(Teacher newTeacher) {
        jdbcOperations.update("INSERT INTO teacher (teacherid, name, surname, thirdname) VALUES (?, ?, ?, ?)",
                newTeacher.getTeacherId(), newTeacher.getName(), newTeacher.getSurname(), newTeacher.getThirdName());
        return newTeacher;
    }

    @Override
    public boolean isExist(String teacherId) {
        return jdbcOperations.query("SELECT teacherid, name, surname, thirdname FROM teacher WHERE teacherid=?", (resultSet, i) -> new Teacher(
                resultSet.getString("teacherid"),
                resultSet.getString("name"),
                resultSet.getString("surname"),
                resultSet.getString("thirdname")
        ), teacherId).size() > 0;
    }

    @Override
    public boolean isEmpty() {
        return Boolean.FALSE.equals(jdbcOperations.queryForObject("select count(1) where exists (select * from teacher)", boolean.class));
    }

    @Override
    public Teacher deleteTeacher(String teacherId) {
        Teacher teacher = getTeacherById(teacherId);
        jdbcOperations.update("DELETE FROM teacher WHERE teacherid = ?", teacherId);
        return teacher;
    }
}
