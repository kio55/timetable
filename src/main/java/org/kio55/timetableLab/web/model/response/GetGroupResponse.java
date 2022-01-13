package org.kio55.timetableLab.web.model.response;

import org.kio55.timetableLab.core.model.Group;

import java.util.Objects;

public class GetGroupResponse {
    private final String groupId;
    private final int term;
    private final int year;

    public GetGroupResponse(final Group group) {
        this.groupId = group.getGroupId();
        this.term = group.getTerm();
        this.year = group.getYear();
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
        GetGroupResponse that = (GetGroupResponse) o;
        return term == that.term && year == that.year && Objects.equals(groupId, that.groupId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, term, year);
    }
}
