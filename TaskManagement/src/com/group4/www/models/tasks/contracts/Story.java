package com.group4.www.models.tasks.contracts;


import com.group4.www.models.contracts.Comment;
import com.group4.www.models.enums.Priority;
import com.group4.www.models.enums.SizeStory;
import com.group4.www.models.enums.StatusStory;

public interface Story extends AssignableTask {


    void addComment(Comment comment);

    void removeComment(Comment comment);

    public SizeStory getSize();

    public Priority getPriority();





    String changeSize(SizeStory sizeStory);


}
