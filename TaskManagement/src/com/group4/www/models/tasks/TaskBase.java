package com.group4.www.models.tasks;

import com.group4.www.models.EventLogImpl;
import com.group4.www.models.contracts.*;
import com.group4.www.models.tasks.contracts.Task;
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
    public static final String DESCR_LENGTH_ERROR =
            String.format("Description must be between %d and %d symbols", DESCR_MIN_LENGTH, DESCR_MAX_LENGTH);
    private static final String COMMENT_NOT_EXIST = "Comment does not exist";
    private static final String COMMENT_ADD = "A comment was added: \"%s\"";
    private static final String COMMENT_REMOVE = "The comment was removed";

    private int id;
    private String title;
    private String description;
    private final List<Comment> comments;
    private final List<EventLog> taskActivity;

    public TaskBase(int id,String title, String description) {
        this.id = id;
        setTitle(title);
        setDescription(description);
        this.comments = new ArrayList<>();
        this.taskActivity = new ArrayList<>();
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
        addLogChanges(String.format(COMMENT_ADD,comment.getMessage()));
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
        taskActivity.add(eventLog);
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
    public int getId() {
        return id;
    }

    @Override
    public List<EventLog> getTaskActivity() {
        return new ArrayList<>(taskActivity);
    }

    @Override
    public String getAsString() {
        return String.format("ID:%d\n" +
                "TITLE:%s\n" +
                "STATUS:%s\n",getId(),getTitle(),getStatus());
    }

    public List<Comment> getComments() {
        return new ArrayList<>(comments);
    }
}


