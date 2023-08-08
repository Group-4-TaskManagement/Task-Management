package com.group4.www.models.tasks;

import com.group4.www.models.tasks.contracts.Feedback;
import com.group4.www.models.contracts.Member;
import com.group4.www.models.enums.StatusFeedback;
import com.group4.www.models.utils.ValidationHelpers;

public class FeedbackImpl extends TaskBase implements Feedback {
    public static final String STATUS_CHANGE =
            "The status of the feedback was changed from %s to %s.";
    public static final String STATUS_ERROR =
            "The status of the feedback can not be changed, it is already at %s!";

    private  static  final int  RATING_MIN = 1;
    private  static  final int  RATING_MAX = 10;
    private  static  final String  RATING_ERROR = "Rating must be between %d and %d";
    private int rating;
    int id;
    private StatusFeedback statusFeedback;
    public FeedbackImpl(int id,String title, String description, Member assignee,int rating) {
        super(id,title, description, assignee);
        setRating(rating);
        this.statusFeedback=StatusFeedback.NEW;
    }

    @Override
    public int getId() {
        return id;
    }

   private void setRating(int rating) {
        ValidationHelpers.validateIntRange(rating,
                RATING_MIN,RATING_MAX,String.format(RATING_ERROR,RATING_MIN,RATING_MAX));
        this.rating = rating;
    }

    public void setStatusFeedback(StatusFeedback statusFeedback) {
        this.statusFeedback = statusFeedback;
    }
    @Override
    public StatusFeedback getStatusFeedback() {
        return statusFeedback;
    }





    public  void revertStatusFeedback() {
        if (getStatusFeedback() != StatusFeedback.NEW) {
            String currentStatus = getStatusFeedback().toString();
            statusFeedback = (StatusFeedback.values()[getStatusFeedback().ordinal() - 1]);
            addLogChanges(String.format(STATUS_CHANGE,currentStatus,getStatusFeedback()));
        } else {
            addLogChanges(String.format(STATUS_ERROR, getStatusFeedback()));
        }
    }

    public void advanceStatus() {
        if (getStatusFeedback() != StatusFeedback.DONE) {
            String currentStatus = getStatusFeedback().toString();
            statusFeedback = (StatusFeedback.values()[getStatusFeedback().ordinal() + 1]);
            addLogChanges(String.format(STATUS_CHANGE,currentStatus,getStatusFeedback()));
        } else {
            addLogChanges(String.format(STATUS_ERROR, getStatusFeedback()));
        }
    }

    @Override
    public void changeRating(int newRating) {
           setRating(newRating);
    }

    @Override
    public int getRating() {

        return rating;
    }



}
