package com.group4.www.models.tasks.contracts;

import com.group4.www.models.enums.StatusFeedback;
import com.group4.www.models.tasks.contracts.Task;

public interface Feedback extends Task {

    int getRating();

    StatusFeedback getStatusFeedback();
}
