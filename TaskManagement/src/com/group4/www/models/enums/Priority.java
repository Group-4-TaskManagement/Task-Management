package com.group4.www.models.enums;

public enum Priority {
    LOW,
    MEDIUM,
    HIGH;


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
