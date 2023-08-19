package com.group4.www.models.tasks.contracts;

import com.group4.www.models.contracts.*;

import java.util.List;

public interface Task extends Identifiable, Printable {
    String getTitle();

    String getDescription();

    void addComment(Comment comment);

    void removeComment(Comment comment);

    void addLogChanges(String message);

    String getStatus();

    void changeStatus(String Status);

    public List<EventLog> getTaskActivity();

}
