package com.group4.www.models;

import com.group4.www.models.contracts.Comment;
import com.group4.www.models.contracts.EventLog;
import com.group4.www.models.contracts.Member;
import com.group4.www.models.enums.PriorityBug;
import com.group4.www.models.enums.SizeStory;
import com.group4.www.models.enums.StatusStory;

import java.util.ArrayList;
import java.util.List;

public class StoryImpl extends TaskBase implements com.group4.www.models.contracts.Story {

    private PriorityBug priority;

    private SizeStory size;

    private StatusStory status;

    private List<Comment> comments;

    private List<EventLog> changesHistory;
    public StoryImpl(String title, String description, Member assignee) {
        super(title, description, assignee);
        this.comments = new ArrayList<>();
        this.changesHistory = new ArrayList<>();
    }

    public void addComment(Comment comment){

        comments.add(comment);
    }
    public void  removeComment(Comment comment){
        for (Comment comment1:comments) {
           if(comment.equals(comment1)){
               comments.remove(comment);
           }

        }
    }
}
