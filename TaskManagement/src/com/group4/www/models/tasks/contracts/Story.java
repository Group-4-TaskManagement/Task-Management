package com.group4.www.models.tasks.contracts;


import com.group4.www.models.contracts.Comment;
import com.group4.www.models.enums.StatusStory;
import com.group4.www.models.tasks.contracts.Task;

public interface Story extends Task {

    StatusStory getStatus();

    void addComment(Comment comment);

    void removeComment(Comment comment);
    public void advancePriority();
    void revertPriority();

    void revertStatus();
    void advanceStatus();
    public  void revertSize();
    public void advanceSize();


}
