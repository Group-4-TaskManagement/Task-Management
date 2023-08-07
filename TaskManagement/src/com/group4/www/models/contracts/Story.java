package com.group4.www.models.contracts;


public interface Story extends Task {

    void addComment(Comment comment);

    void removeComment(Comment comment);
}
