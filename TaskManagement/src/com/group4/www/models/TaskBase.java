package com.group4.www.models;

import com.group4.www.models.contracts.Comments;
import com.group4.www.models.contracts.EventLog;
import com.group4.www.models.contracts.Members;
import com.group4.www.models.contracts.Task;
import com.group4.www.models.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

abstract class TaskBase implements Task {
    public static final int TITLE_MIN_LENGTH = 10;
    public static final int TITLE_MAX_LENGTH = 50;
    public static final String TITLE_LENGTH_ERROR =
            String.format("Title length must be between %d and %d symbols!", TITLE_MIN_LENGTH, TITLE_MAX_LENGTH);
    public static final int DESCR_MIN_LENGTH = 10;
    public static final int DESCR_MAX_LENGTH = 500;
    public static final String DESCR_LENGTH_ERROR = String.format("Description must be between %d and %d symbols", DESCR_MIN_LENGTH, DESCR_MAX_LENGTH);
    private String title;
    private String description;
    private Members assignee;
    private final List<Comments> comments;
    private final List<EventLog> logChanges;

    public TaskBase(String title, String description, Members assignee) {
        setTitle(title);
        setDescription(description);
        this.assignee = assignee;
        this.comments = new ArrayList<>();
        this.logChanges = new ArrayList<>();
    }

    private void setTitle(String title) {
        ValidationHelpers.validateIntRange(title.length(), TITLE_MIN_LENGTH, TITLE_MAX_LENGTH, TITLE_LENGTH_ERROR);
        this.title = title;
    }

    private void setDescription(String description) {
        ValidationHelpers.validateIntRange(description.length(), DESCR_MIN_LENGTH, DESCR_MAX_LENGTH, DESCR_LENGTH_ERROR);
        this.description = description;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Members getAssignee() {
        return assignee;
    }
}
