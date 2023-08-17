package com.group4.www.models.tasks.contracts;

import com.group4.www.models.contracts.*;

import java.util.List;

public interface Task extends Identifiable, Printable {
    String getTitle();

    String getDescription();

    Member getAssignee();

    void addComment(Comment comment);

    void removeComment(Comment comment);

    void addLogChanges(String message);

    void addAssignee(Member member);

    String getStatus();

    public List<EventLog> getTaskActivity();

}
