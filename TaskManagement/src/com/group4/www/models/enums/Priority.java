package com.group4.www.models.enums;

public enum Priority {
    LOW,
    MEDIUM,
    HIGH;

    ;
    //TODO Override to string!


    @Override
    public String toString() {

        switch (this) {
            case HIGH:
                return "High";
            case MEDIUM:
                return "Medium";
            case LOW:
                return "Low";
            default:
                return "Unknown";

        }
    }
}
