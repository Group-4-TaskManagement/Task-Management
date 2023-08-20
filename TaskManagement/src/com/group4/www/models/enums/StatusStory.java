package com.group4.www.models.enums;

public enum StatusStory {
    NOT_DONE,
    IN_PROGRESS,
    DONE;

    @Override
    public String toString() {
        switch (this){
            case NOT_DONE:
                return "Not_Done";

            case DONE :
                return "Done";

            case IN_PROGRESS:
                return "InProgress";

            default:
                return "Unknown";
        }

    }
}
