package com.group4.www.models;

import com.group4.www.models.contracts.*;
import com.group4.www.models.enums.Priority;
import com.group4.www.models.enums.SizeStory;
import com.group4.www.models.enums.StatusStory;

import java.util.ArrayList;
import java.util.List;

public class StoryImpl extends TaskBase implements Story {

    public static final String PRIORITY_CHANGE =
            "The priority of the story was changed from %s to %s.";
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

        switch (priority.toString()) {

            case "High":
                this.priority = Priority.MEDIUM;
                addLogChanges(String.format(PRIORITY_CHANGE, Priority.HIGH.toString(), Priority.MEDIUM.toString()));
                break;

            case "Medium":
                this.priority = Priority.HIGH;
                addLogChanges(String.format(PRIORITY_CHANGE, Priority.MEDIUM.toString(), Priority.HIGH.toString()));
                break;

            case "Low":
                addLogChanges(String.format(PRIORITY_ERROR,Priority.LOW));


        }
    }

    @Override
    public void advancePriority() {
        if(priority == Priority.LOW){
            this.priority= Priority.MEDIUM;
            addLogChanges(String.format(PRIORITY_CHANGE,Priority.LOW.toString(),Priority.MEDIUM.toString()));
        }
        else if (priority== Priority.MEDIUM){
            this.priority= Priority.HIGH;
            addLogChanges(String.format(PRIORITY_CHANGE,Priority.MEDIUM,Priority.HIGH));
        }
        else addLogChanges(String.format(PRIORITY_ERROR,Priority.HIGH));
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
        if(getSize() != SizeStory.LARGE){
            String currentSize = getSize().toString();
            size = (SizeStory.values()[getSize().ordinal() -1]);
            addLogChanges(String.format(SIZE_CHANGE,currentSize,getSize()));
        }
        else {
            addLogChanges(String.format(SIZE_ERROR,getSize()));
        }

    }
    public void advanceSize(){
        if(getSize() != SizeStory.SMALL){
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
}



