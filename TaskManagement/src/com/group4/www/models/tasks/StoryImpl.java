package com.group4.www.models.tasks;

import com.group4.www.models.contracts.*;
import com.group4.www.models.enums.Priority;
import com.group4.www.models.enums.SizeStory;
import com.group4.www.models.enums.StatusStory;
import com.group4.www.models.tasks.contracts.AssignableTask;
import com.group4.www.models.tasks.contracts.Story;
import com.group4.www.models.utils.ParsingHelpers;
import com.group4.www.models.utils.ValidationHelpers;

public class StoryImpl extends AssignableTaskBase implements Story {

    public static final String PRIORITY_CHANGE =
            "The priority of story with ID:%d was changed from %s to %s.";
    public static final String PRIORITY_ERROR =
            "The priority of the story can not be changed, it is already at %s!";

    public static final String STATUS_CHANGE = "The status of story with ID:%d was changed from %s to %s.";
    public static final String STATUS_ERROR = "The status of the story can not be changed, it is already at %s!";
    public static final String STATUS_PARSE_ERROR = "Status of bug can be Not_Done, Done or InProgress!";

    public static final String SIZE_CHANGE =
            "The size of story with ID:%d was changed from %s to %s.";
    public static final String SIZE_ERROR =
            "The size of the story can not be changed, it is already at %s!";



    private Priority priority;

    private SizeStory size;

    private StatusStory status;



    public StoryImpl(int id,String title, String description,Priority priority, SizeStory size,StatusStory status) {
        super(id,title, description);
        this.priority = priority;
        this.size = size;
        this.status = status;

    }

    @Override
    public String getStatus() {
        return status.toString();
    }

    @Override
    public void changeStatus(String statusChange) {
        StatusStory statusStory = ParsingHelpers.tryParseEnum(statusChange,StatusStory.class,STATUS_PARSE_ERROR);

        if(statusStory==status) {
            throw new IllegalArgumentException(String.format(STATUS_ERROR, getStatus()));
        }else {


            super.addLogChanges(String.format(STATUS_CHANGE,getId(),getStatus(),statusStory));
            this.status = statusStory;
        }

    }

    @Override
    public SizeStory getSize() {
        return size;
    }

    @Override
    public Priority getPriority() {
        return priority;
    }

    @Override
    public void setStatus(StatusStory statusStory) {
        if(statusStory==status) {
            throw new IllegalArgumentException(String.format(STATUS_ERROR, getStatus()));
        }else {
            System.out.printf(STATUS_CHANGE,getId(), getStatus(), statusStory);
            super.addLogChanges(String.format(STATUS_CHANGE,getId(),getStatus(),statusStory));
            this.status = statusStory;
        }
    }

    @Override
    public void setPriority(Priority priorityStory) {
        if(priorityStory==priority) {
            throw new IllegalArgumentException(String.format(PRIORITY_ERROR, getPriority()));
        }else {
            System.out.printf(PRIORITY_CHANGE,getId(), getPriority(), priorityStory);
            super.addLogChanges(String.format(PRIORITY_CHANGE,getId(),getPriority(),priorityStory));
            this.priority = priorityStory;
        }
    }

    @Override
    public void setSize(SizeStory sizeStory) {
        if(sizeStory==size) {
            throw new IllegalArgumentException(String.format(SIZE_ERROR, getSize()));
        }else {
            System.out.printf(SIZE_CHANGE, getId(), getSize(), sizeStory);
            super.addLogChanges(String.format(SIZE_CHANGE,getId(),getSize(),sizeStory));
            this.size = sizeStory;
        }
    }

    @Override
    public String getAsString() {
        return String.format("%s" +
                "PRIORITY:%s\n" +
                "SIZE:%s\n",super.getAsString(),getPriority(),getSize());
    }
}



