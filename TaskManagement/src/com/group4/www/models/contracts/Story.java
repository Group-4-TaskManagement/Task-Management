package com.group4.www.models.contracts;


import com.group4.www.models.enums.StatusStory;

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
