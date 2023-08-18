package com.group4.www.models.tasks.contracts;

import com.group4.www.models.enums.StatusFeedback;

public interface Feedback extends Task {

    int getRating();






    void revertStatusFeedback();

    void advanceStatus();



    void changeRating(int newRating);
}
