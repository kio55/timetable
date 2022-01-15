package org.kio55.timetableLab.web.model.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.kio55.timetableLab.core.model.Type;


import java.util.Objects;

public class CreateTypeRequest {
    @JsonProperty("typeOfClass")
    private final String type;

    @JsonCreator
    public CreateTypeRequest(@JsonProperty("typeOfClass") final String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateTypeRequest that = (CreateTypeRequest) o;
        return Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }
}
