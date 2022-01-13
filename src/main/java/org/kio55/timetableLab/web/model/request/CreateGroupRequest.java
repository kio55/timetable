package org.kio55.timetableLab.web.model.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class CreateGroupRequest {
    @JsonProperty("groupId")
    private final String groupId;
    @JsonProperty("term")
    private final int term;
    @JsonProperty("year")
    private final int year;

    @JsonCreator
    public CreateGroupRequest(
            @JsonProperty("groupId") final String groupId,
            @JsonProperty("term") final int term,
            @JsonProperty("year") final int year
    ) {
        this.groupId = groupId;
        this.term = term;
        this.year = year;
    }

    public String getGroupId() {
        return groupId;
    }

    public int getTerm() {
        return term;
    }

    public int getYear() {
        return year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateGroupRequest that = (CreateGroupRequest) o;
        return Objects.equals(groupId, that.groupId) && Objects.equals(term, that.term) && Objects.equals(year, that.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, term, year);
    }
}
