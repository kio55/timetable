package org.kio55.timetableLab.web.service.type;

import org.kio55.timetableLab.core.model.Type;
import org.kio55.timetableLab.core.repository.typeRepository.ITypeRepository;
import org.kio55.timetableLab.web.model.request.CreateTypeRequest;
import org.kio55.timetableLab.web.model.response.TypeResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TypeService implements ITypeService {
    private final ITypeRepository typeRepository;

    public TypeService(ITypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    @Override
    public List<TypeResponse> getTypes() {
        List<TypeResponse> typeList = new ArrayList<>();
        for (Type type : typeRepository.getTypes()) {
            typeList.add(new TypeResponse(type));
        }
        return typeList;
    }

    @Override
    public TypeResponse getTypeById(String typeId) throws Exception {
        if (typeRepository.isEmpty() || !typeRepository.isExist(typeId)) {
            throw new Exception();
        }
        return new TypeResponse(typeRepository.getTypeById(typeId));
    }

    @Override
    public TypeResponse addType(CreateTypeRequest addedType) throws Exception {
        String uniqueID = UUID.randomUUID().toString();
        while (typeRepository.isExist(uniqueID)) {
            uniqueID = UUID.randomUUID().toString();
        }
        return new TypeResponse(typeRepository.createType(new Type(uniqueID, addedType.getType())));
    }

    @Override
    public TypeResponse deleteType(String deletedType) throws Exception {
        if (typeRepository.isEmpty() || !typeRepository.isExist(deletedType)) {
            throw new Exception();
        }
        return new TypeResponse(typeRepository.deleteType(deletedType));
    }
}
