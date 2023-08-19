package com.group4.www.models.tasks.contracts;

import com.group4.www.models.contracts.Member;

public interface AssignableTask extends Task{
    public void addAssignee(Member member);
    public Member getAssignee();
}
