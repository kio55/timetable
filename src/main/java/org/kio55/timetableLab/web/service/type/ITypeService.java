package org.kio55.timetableLab.web.service.type;

import org.kio55.timetableLab.web.model.request.CreateTypeRequest;
import org.kio55.timetableLab.web.model.response.TypeResponse;

import java.util.List;

public interface ITypeService {

    List<TypeResponse> getTypes();

    TypeResponse getTypeById(String typeId) throws Exception;

    TypeResponse addType(CreateTypeRequest addedType) throws Exception;

    TypeResponse deleteType(String deletedType) throws Exception;
}
