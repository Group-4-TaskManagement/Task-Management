package com.group4.www.models.tasks.contracts;

import com.group4.www.models.contracts.Member;
import com.group4.www.models.enums.Priority;

public interface AssignableTask extends Task{

    void addAssignee(Member member);

    Member getAssignee();

    String changePriority(Priority priorityStory);

    Priority getPriority();

}
