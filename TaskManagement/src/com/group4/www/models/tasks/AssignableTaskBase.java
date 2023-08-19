package com.group4.www.models.tasks;

import com.group4.www.models.contracts.Member;
import com.group4.www.models.enums.Priority;
import com.group4.www.models.tasks.contracts.AssignableTask;

abstract class AssignableTaskBase extends TaskBase implements AssignableTask {

    public static final String PRIORITY_CHANGE =
            "The priority of task with ID:%d was changed from %s to %s.";
    public static final String PRIORITY_ERROR =
            "The priority of the task can not be changed, it is already at %s!";


    private Member assignee;

    private Priority priority;

    public AssignableTaskBase(int id, String title, String description,Priority priority) {
        super(id, title, description);
        this.priority = priority;
    }

    @Override
    public Member getAssignee() {
        return assignee;
    }

    @Override
    public void addAssignee(Member member) {
        this.assignee=member;
    }

    public Priority getPriority() {
        return priority;
    }

    @Override
    public String changePriority(Priority priorityStory) {
        String currentPriority = priority.toString();
        if (priorityStory == priority) {
            throw new IllegalArgumentException(String.format(PRIORITY_ERROR, getPriority()));
        } else {

            super.addLogChanges(String.format(PRIORITY_CHANGE, getId(), getPriority(), priorityStory));
            this.priority = priorityStory;
            return String.format(PRIORITY_CHANGE, getId(), currentPriority, priorityStory);
        }

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
