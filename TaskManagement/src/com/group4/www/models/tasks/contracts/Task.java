package com.group4.www.models.tasks.contracts;

import com.group4.www.models.contracts.Comment;
import com.group4.www.models.contracts.Identifiable;
import com.group4.www.models.contracts.Member;
import com.group4.www.models.contracts.Printable;

public interface Task extends Identifiable, Printable {
    String getTitle();

    String getDescription();

    Member getAssignee();

    void addComment(Comment comment);

    void removeComment(Comment comment);

    void addLogChanges(String message);



}
