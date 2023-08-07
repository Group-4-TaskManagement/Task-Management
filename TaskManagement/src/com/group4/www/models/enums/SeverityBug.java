package com.group4.www.models.enums;

public enum SeverityBug {
    MINOR,
    MAJOR,
    CRITICAL;

    @Override
    public String toString() {

        switch (this) {
            case CRITICAL:
                return "Critical";
            case MAJOR:
                return "Major";
            case MINOR:
                return "Minor";
            default:
                return "Unknown";

        }
    }
}
