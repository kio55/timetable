package org.kio55.timetableLab.web.model.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.kio55.timetableLab.core.model.Type;
import org.kio55.timetableLab.web.model.request.CreateTypeRequest;

import java.util.Objects;

public class TypeResponse {
    @JsonProperty("typeId")
    private final String typeId;
    @JsonProperty("typeOfClass")
    private final String type;

    @JsonCreator
    public TypeResponse(final Type type) {
        this.typeId = type.getTypeId();
        this.type = type.getType();
    }

    public String getTypeId() {
        return typeId;
    }

    public String getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeResponse that = (TypeResponse) o;
        return Objects.equals(typeId, that.typeId) && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeId, type);
    }
}
