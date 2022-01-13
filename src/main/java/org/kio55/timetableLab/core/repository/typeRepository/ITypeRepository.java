package org.kio55.timetableLab.core.repository.typeRepository;

import org.kio55.timetableLab.core.model.Type;

import java.util.List;

public interface ITypeRepository {

    List<Type> getTypes();

    Type getTypeById(String typeId);

    Type createType(Type newType);

    boolean isExist(String typeId);

    boolean isEmpty();

    Type deleteType(String typeId);
}
