package com.group4.www.models.tasks.contracts;

import com.group4.www.models.enums.StatusFeedback;

public interface Feedback extends Task {

    int getRating();

    void setStatusFeedback(StatusFeedback statusFeedback);


    public  void revertStatusFeedback();

    public void advanceStatus();



    void changeRating(int newRating);
}
