package org.kio55.timetableLab.core.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Type {
    private final String typeId;
    private final String type;

    @JsonCreator
    public Type(
            @JsonProperty("typeId") final String typeId,
            @JsonProperty("typeOfClass") final String type
    ) {
        this.typeId = typeId;
        this.type = type;
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
        Type type1 = (Type) o;
        return Objects.equals(typeId, type1.typeId) && Objects.equals(type, type1.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeId, type);
    }
}
