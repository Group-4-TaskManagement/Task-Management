package com.group4.www.models;

import com.group4.www.models.contracts.*;
import com.group4.www.models.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

abstract class TaskBase implements Task, Identifiable {
    public static final int TITLE_MIN_LENGTH = 10;
    public static final int TITLE_MAX_LENGTH = 50;
    public static final String TITLE_LENGTH_ERROR =
            String.format("Title length must be between %d and %d symbols!", TITLE_MIN_LENGTH, TITLE_MAX_LENGTH);
    public static final int DESCR_MIN_LENGTH = 10;
    public static final int DESCR_MAX_LENGTH = 500;
    public static final String DESCR_LENGTH_ERROR =
            String.format("Description must be between %d and %d symbols", DESCR_MIN_LENGTH, DESCR_MAX_LENGTH);

    private static final String COMMENT_NOT_EXIST = "Comment does not exist";
    private static final String COMMENT_ADD = "The comment was added";
    private static final String COMMENT_REMOVE = "The comment was removed";



    private int id;
    private String title;
    private String description;
    private Member assignee;
    private final List<Comment> comments;
    private final List<EventLog> logChanges;

    public TaskBase(int id,String title, String description, Member assignee) {
        this.id = id;
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


    public void addComment(Comment comment){
        comments.add(comment);
        addLogChanges(COMMENT_ADD);
    }


    public void  removeComment(Comment comment){
        if(comments.contains(comment)){
            comments.remove(comment);
            addLogChanges(COMMENT_REMOVE);
        }
        else {

            throw new IllegalArgumentException(COMMENT_NOT_EXIST);
        }
    }

    public void addLogChanges(String message){
        EventLog eventLog = new EventLogImpl(message);
        logChanges.add(eventLog);
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
    public Member getAssignee() {
        return assignee;
    }

    @Override
    public int getId() {
        return id;
    }
}
