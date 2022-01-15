package org.kio55.timetableLab.core.repository.groupRepository;

import org.kio55.timetableLab.core.model.Group;
import org.springframework.jdbc.core.JdbcOperations;

import java.util.List;

public class PostgresGroupRepository implements IGroupRepository {
    private final JdbcOperations jdbcOperations;

    public PostgresGroupRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public List<Group> getGroups() {
        return jdbcOperations.query("SELECT groupid, term, year FROM \"group\" ORDER BY groupid", (resultSet, i) -> new Group(
                resultSet.getString("groupId"),
                resultSet.getInt("term"),
                resultSet.getInt("year")
        ));
    }

    @Override
    public Group getGroupById(String groupId) {
        return jdbcOperations.queryForObject("SELECT groupid, term, year FROM \"group\" WHERE groupid = ?", (resultSet, i) -> new Group(
                resultSet.getString("groupId"),
                resultSet.getInt("term"),
                resultSet.getInt("year")
        ), groupId);
    }

    @Override
    public Group createGroup(Group newGroup) {
        jdbcOperations.update("INSERT INTO \"group\" (groupid, term, year) VALUES (?, ?, ?)",
                newGroup.getGroupId(), newGroup.getTerm(), newGroup.getYear());
        return newGroup;
    }

    @Override
    public boolean isExist(String groupId) {
        return jdbcOperations.query("SELECT groupid, term, year FROM \"group\" WHERE groupid = ?", (resultSet, i) -> new Group(
                resultSet.getString("groupId"),
                resultSet.getInt("term"),
                resultSet.getInt("year")
        ), groupId).size() > 0;
    }

    @Override
    public boolean isEmpty() {
        return Boolean.FALSE.equals(jdbcOperations.queryForObject("select count(1) where exists (select * from \"group\")", boolean.class));
    }

    @Override
    public Group deleteGroup(String groupId) {
        Group group = getGroupById(groupId);
        jdbcOperations.update("DELETE FROM \"group\" WHERE groupid = ?", groupId);
        jdbcOperations.update("DELETE FROM groupclass WHERE groupid = ?", groupId);
        return group;
    }
}
