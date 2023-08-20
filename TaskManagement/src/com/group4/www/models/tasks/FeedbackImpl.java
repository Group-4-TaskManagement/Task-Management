package com.group4.www.models.tasks;

import com.group4.www.models.tasks.contracts.Feedback;
import com.group4.www.models.enums.StatusFeedback;
import com.group4.www.models.utils.ValidationHelpers;

public class FeedbackImpl extends TaskBase implements Feedback {
    public static final String STATUS_CHANGE =
            "The status of item with ID:%d switched from %s to %s.";
    public static final String STATUS_SAME_ERROR =
            "The status of the feedback can not be changed, it is already at %s!";

    private static final int RATING_MIN = 1;
    private static final int RATING_MAX = 10;
    private static final String RATING_ERROR = "Rating must be between %d and %d";
    public static final String STATUS_ERROR = "Status of feedback can be New, Unscheduled, Scheduled or Done!";
    private int rating;
    private StatusFeedback statusFeedback;

    public FeedbackImpl(int id, String title, String description, int rating) {
        super(id, title, description);
        setRating(rating);
        this.statusFeedback = StatusFeedback.NEW;
    }


    private void setRating(int rating) {
        ValidationHelpers.validateIntRange(rating,
                RATING_MIN, RATING_MAX, String.format(RATING_ERROR, RATING_MIN, RATING_MAX));
        this.rating = rating;
    }
//    @Override
//    public void statusFeedback(StatusFeedback statusFeedback) {
//        this.statusFeedback = statusFeedback;
//    }

    @Override
    public String getStatus() {
        return statusFeedback.toString();
    }


    @Override
    public String changeStatus(String command) {
        String currentStatus = statusFeedback.toString();
        if(currentStatus.equals(command)){
            throw new IllegalArgumentException
                    (String.format(STATUS_SAME_ERROR,currentStatus));
        }
        switch (command) {
            case "New":
                this.statusFeedback = StatusFeedback.NEW;
                return addLogAndPrintMessage(currentStatus);
            case "Unscheduled":
                this.statusFeedback = StatusFeedback.UNSCHEDULED;
                return addLogAndPrintMessage(currentStatus);
            case "Scheduled":
                this.statusFeedback = StatusFeedback.SCHEDULED;
                return addLogAndPrintMessage(currentStatus);
            case "Done":
                this.statusFeedback = StatusFeedback.DONE;
                return addLogAndPrintMessage(currentStatus);
            case "Advance":
               return advanceStatus();

            case "Revert":
               return revertStatusFeedback();

            default:
                throw new IllegalArgumentException(STATUS_ERROR);
        }
//        System.out.printf("Feedback status changed to %s!\n",statusFeedback);
    }

    String addLogAndPrintMessage(String statusPrint) {
        super.addLogChanges(String.format(STATUS_CHANGE, getId(), statusPrint, getStatus()));
        return String.format(STATUS_CHANGE, getId(), statusPrint, getStatus());
    }


    public String revertStatusFeedback() {
        if (statusFeedback != StatusFeedback.NEW) {
            String currentStatus = statusFeedback.toString();
            statusFeedback = (StatusFeedback.values()[statusFeedback.ordinal() - 1]);
            addLogChanges(String.format(STATUS_CHANGE, getId(), currentStatus, statusFeedback));
            return String.format(STATUS_CHANGE, getId(), currentStatus, statusFeedback);
        } else {
            addLogChanges(String.format(STATUS_SAME_ERROR, statusFeedback));
            return String.format(STATUS_SAME_ERROR, statusFeedback);
        }
    }

    public String advanceStatus() {
        if (statusFeedback != StatusFeedback.DONE) {
            String currentStatus = statusFeedback.toString();
            statusFeedback = (StatusFeedback.values()[statusFeedback.ordinal() + 1]);
            addLogChanges(String.format(STATUS_CHANGE, getId(), currentStatus, statusFeedback));
            return String.format(STATUS_CHANGE, getId(), currentStatus, statusFeedback);
        } else {
            addLogChanges(String.format(STATUS_SAME_ERROR, statusFeedback));
            return String.format(STATUS_SAME_ERROR, statusFeedback);
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


    @Override
    public String getAsString() {
        return String.format("%s" +
                "RATING:%d\n", super.getAsString(), getRating());
    }
}
