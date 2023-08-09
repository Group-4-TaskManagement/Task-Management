package com.group4.www.models.tasks;

import com.group4.www.models.contracts.*;
import com.group4.www.models.enums.Priority;
import com.group4.www.models.enums.SizeStory;
import com.group4.www.models.enums.StatusStory;
import com.group4.www.models.tasks.contracts.Story;

public class StoryImpl extends TaskBase implements Story {

    public static final String PRIORITY_CHANGE =
            "The priority of the story was changed from %s to %s. ";
    public static final String PRIORITY_ERROR =
            "The priority of the story can not be changed, it is already at %s!";

    public static final String STATUS_CHANGE = "The status of the story was changed from %s to %s.";
    public static final String STATUS_ERROR = "The status of the story can not be changed, it is already at %s!";

    public static final String SIZE_CHANGE =
            "The size of the story was changed from %s to %s.";
    public static final String SIZE_ERROR =
            "The size of the story can not be changed, it is already at %s!";



    private Priority priority;

    private SizeStory size;

    private StatusStory status;



    public StoryImpl(int id,String title, String description, Member assignee,Priority priority, SizeStory size,StatusStory status) {
        super(id,title, description, assignee);
        this.priority = priority;
        this.size = size;
        this.status = status;

    }

    @Override
    public void revertPriority() {
        if (getPriority() != Priority.LOW) {
            String currentPriority = getPriority().toString();
            status = (StatusStory.values()[getPriority().ordinal() - 1]);
            System.out.printf(PRIORITY_CHANGE,currentPriority,getPriority());
            addLogChanges(String.format(PRIORITY_CHANGE,currentPriority,getPriority()));
        } else {
            addLogChanges(String.format(PRIORITY_ERROR, getPriority()));
            throw new IllegalArgumentException(String.format(PRIORITY_ERROR,getPriority()));
        }
    }


    @Override
    public void advancePriority() {
        if (getPriority() != Priority.HIGH) {
            String currentPriority = getPriority().toString();
            status = (StatusStory.values()[getPriority().ordinal() + 1]);
            System.out.printf(PRIORITY_CHANGE,currentPriority,getPriority());
            addLogChanges(String.format(PRIORITY_CHANGE,currentPriority,getPriority()));
        } else {
            addLogChanges(String.format(PRIORITY_ERROR, getPriority()));
            throw new IllegalArgumentException(String.format(PRIORITY_ERROR,getPriority()));
        }

    }


    public  void revertStatus() {
        if (getStatus() != StatusStory.NOT_DONE) {
            String currentStatus = getStatus().toString();
            status = (StatusStory.values()[getStatus().ordinal() - 1]);
            addLogChanges(String.format(STATUS_CHANGE,currentStatus,getStatus()));
        } else {
            addLogChanges(String.format(STATUS_ERROR, getStatus()));
        }
    }

    public void advanceStatus() {
        if (getStatus() != StatusStory.DONE) {
            String currentStatus = getStatus().toString();
            status = (StatusStory.values()[getStatus().ordinal() + 1]);
            addLogChanges(String.format(STATUS_CHANGE,currentStatus,getStatus()));
        } else {
            addLogChanges(String.format(STATUS_ERROR, getStatus()));
        }
    }

    public  void revertSize(){
        if(getSize() != SizeStory.SMALL){
            String currentSize = getSize().toString();
            size = (SizeStory.values()[getSize().ordinal() -1]);
            addLogChanges(String.format(SIZE_CHANGE,currentSize,getSize()));
        }
        else {
            addLogChanges(String.format(SIZE_ERROR,getSize()));
        }

    }
    public void advanceSize(){
        if(getSize() != SizeStory.LARGE){
            String currentSize = getSize().toString();
            size = (SizeStory.values()[getSize().ordinal()  + 1]);
            addLogChanges(String.format(SIZE_CHANGE,currentSize,getSize()));
        }
        else {
            addLogChanges(String.format(SIZE_ERROR,getSize()));
        }
    }




    public StatusStory getStatus() {
        return status;
    }

    public SizeStory getSize() {
        return size;
    }

    public Priority getPriority() {
        return priority;
    }
}



