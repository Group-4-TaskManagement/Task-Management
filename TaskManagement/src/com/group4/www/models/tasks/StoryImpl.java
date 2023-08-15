package com.group4.www.models.tasks;

import com.group4.www.models.contracts.*;
import com.group4.www.models.enums.Priority;
import com.group4.www.models.enums.SizeStory;
import com.group4.www.models.enums.StatusStory;
import com.group4.www.models.tasks.contracts.Story;

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

    public SizeStory getSize() {
        return size;
    }

    public Priority getPriority() {
        return priority;
    }

    @Override
    public String getAsString() {
        return String.format("%s" +
                "PRIORITY:%s\n" +
                "SIZE:%s\n",super.getAsString(),getPriority(),getSize());
    }
}



