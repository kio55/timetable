package org.kio55.timetableLab.core.repository.typeRepository;

import org.kio55.timetableLab.core.model.Type;
import org.springframework.jdbc.core.JdbcOperations;

import java.util.List;

public class PostgresTypeRepository implements ITypeRepository{
    private final JdbcOperations jdbcOperations;

    public PostgresTypeRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public List<Type> getTypes() {
        return jdbcOperations.query("SELECT typeid, type FROM type ORDER BY typeId", (resultSet, i) -> new Type(
                resultSet.getString("typeid"),
                resultSet.getString("type")
        ));
    }

    @Override
    public Type getTypeById(String typeId) {
        return jdbcOperations.queryForObject("SELECT typeid, type FROM type WHERE typeid=?", (resultSet, i) -> new Type(
                resultSet.getString("typeid"),
                resultSet.getString("type")
        ), typeId);
    }

    @Override
    public Type createType(Type newType) {
        jdbcOperations.update("INSERT INTO type (typeid, type) VALUES (?, ?)", newType.getTypeId(), newType.getType());
        return newType;
    }

    @Override
    public boolean isExist(String typeId) {
        return jdbcOperations.query("SELECT typeid, type FROM type WHERE typeid=?", (resultSet, i) -> new Type(
                resultSet.getString("typeid"),
                resultSet.getString("type")
        ), typeId).size() > 0;
    }

    @Override
    public boolean isEmpty() {
        return Boolean.FALSE.equals(jdbcOperations.queryForObject("select count(1) where exists (select * from type)", boolean.class));
    }

    @Override
    public Type deleteType(String typeId) {
        Type type = getTypeById(typeId);
        jdbcOperations.update("DELETE FROM type WHERE typeid = ?", typeId);
        return type;
    }
}
