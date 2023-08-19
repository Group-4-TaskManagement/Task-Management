package com.group4.www.models.tasks;

import com.group4.www.models.contracts.Member;
import com.group4.www.models.tasks.contracts.AssignableTask;

abstract class AssignableTaskBase extends TaskBase implements AssignableTask {
    private Member assignee;

    public AssignableTaskBase(int id, String title, String description) {
        super(id, title, description);
    }

    @Override
    public Member getAssignee() {
        return assignee;
    }

    @Override
    public void addAssignee(Member member) {
        this.assignee=member;
    }

    @Override
    public String getAsString() {
        if(getAssignee()==null){
            return String.format("%s"
                    +"ASSIGNEE:Not assigned yet\n",super.getAsString());
        }
        return String.format("%s"
                +"ASSIGNEE:%s\n",super.getAsString(),getAssignee().getName());
    }
}
