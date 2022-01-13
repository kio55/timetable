package org.kio55.timetableLab.core.repository.typeRepository;

import org.kio55.timetableLab.core.model.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapTypeRepository implements ITypeRepository {
    private final Map<String, Type> typeMap;

    public MapTypeRepository(Map<String, Type> typeMap) {
        this.typeMap = typeMap;
    }

    @Override
    public List<Type> getTypes() {
        return new ArrayList<>(this.typeMap.values());
    }

    @Override
    public Type getTypeById(String typeId) {
        return this.typeMap.getOrDefault(typeId, null);
    }

    @Override
    public Type createType(Type newType) {
        typeMap.put(newType.getTypeId(), newType);
        return newType;
    }

    @Override
    public boolean isExist(String typeId) {
        return this.typeMap.containsKey(typeId);
    }

    @Override
    public boolean isEmpty() {
        return this.typeMap.isEmpty();
    }

    @Override
    public Type deleteType(String typeId) {
        Type deletedType = typeMap.get(typeId);
        this.typeMap.remove(typeId);
        return deletedType;
    }
}
