package com.group4.www.models.contracts;

public interface Task {
    String getTitle();

    String getDescription();

    Member getAssignee();

    void addComment(Comment comment);

    void removeComment(Comment comment);

    void addLogChanges(String massage);



}
