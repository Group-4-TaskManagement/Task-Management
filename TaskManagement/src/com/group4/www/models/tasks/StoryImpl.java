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


    public static final String STATUS_CHANGE ="The status of item with ID:%d switched from %s to %s.";
    public static final String STATUS_ERROR = "The status of the story can not be changed, it is already at %s!";
    public static final String STATUS_PARSE_ERROR = "Status of story can be Not_Done, Done or InProgress!";

    public static final String SIZE_CHANGE =
            "The size of story with ID:%d was changed from %s to %s.";
    public static final String SIZE_ERROR =
            "The size of the story can not be changed, it is already at %s!";





    private SizeStory size;

    private StatusStory status;



    public StoryImpl(int id,String title, String description,Priority priority, SizeStory size,StatusStory status) {
        super(id,title, description,priority);
        this.size = size;
        this.status = status;

    }

    @Override
    public String getStatus() {
        return status.toString();
    }

    @Override
    public String changeStatus(String statusChange) {
        StatusStory statusStory = ParsingHelpers.tryParseEnum(statusChange,StatusStory.class,STATUS_PARSE_ERROR);
        StatusStory currentstatusStory = status;

        if(statusStory==status) {
            throw new IllegalArgumentException(String.format(STATUS_ERROR, getStatus()));
        }else {


            super.addLogChanges(String.format(STATUS_CHANGE,getId(),currentstatusStory,statusStory));
            this.status = statusStory;
        }
        return String.format(STATUS_CHANGE,getId(),currentstatusStory,statusStory);

    }

    @Override
    public SizeStory getSize() {
        return size;
    }


    public String changeSize(SizeStory sizeStory) {
        String currentSize  = size.toString();
        if(sizeStory==size) {
            throw new IllegalArgumentException(String.format(SIZE_ERROR, getSize()));
        }else {
            super.addLogChanges(String.format(SIZE_CHANGE,getId(),getSize(),sizeStory));
            this.size = sizeStory;
            return String.format(SIZE_CHANGE, getId(), currentSize, sizeStory);
        }
    }

    @Override
    public String getAsString() {
        return String.format("%s" +
                "PRIORITY:%s\n" +
                "SIZE:%s\n",super.getAsString(),getPriority(),getSize());
    }
}



