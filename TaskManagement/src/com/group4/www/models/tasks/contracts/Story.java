package com.group4.www.models.tasks.contracts;


import com.group4.www.models.contracts.Comment;
import com.group4.www.models.enums.StatusStory;

public interface Story extends Task {


    void addComment(Comment comment);

    void removeComment(Comment comment);



}
