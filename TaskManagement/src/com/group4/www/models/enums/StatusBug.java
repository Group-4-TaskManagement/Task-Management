package com.group4.www.models.enums;

public enum StatusBug {
    ACTIVE,
    FIXED;

    @Override
    public String toString() {

        switch (this) {
            case ACTIVE:
                return "Active";
            case FIXED:
                return "Fixed";
            default:
                return "Unknown";
        }
    }
}
