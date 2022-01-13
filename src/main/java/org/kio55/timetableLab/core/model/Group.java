package org.kio55.timetableLab.core.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * model for Group
 */
public class Group {
    private final String groupId;
    private int term;
    private int year;

    @JsonCreator
    public Group(
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

    public void updateYear() {
        term++;
        if (term > 2) {
            term = 1;
            year++;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return term == group.term && year == group.year && Objects.equals(groupId, group.groupId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, term, year);
    }
}
