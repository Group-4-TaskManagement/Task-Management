package com.group4.www.models.contracts;

import com.group4.www.models.enums.StatusFeedback;

public interface Feedback extends Task {

    int getRating();

    StatusFeedback getStatusFeedback();
}
