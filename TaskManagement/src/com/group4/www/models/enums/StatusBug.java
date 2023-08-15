package com.group4.www.models.enums;

public enum StatusBug {
    ACTIVE,
    FIXED;
    String ANSI_GREEN = "\u001B[32m";
    String ANSI_RESET = "\u001B[0m";
    @Override
    public String toString() {

        switch (this) {
            case ACTIVE:
                return ANSI_GREEN + "Active" + ANSI_RESET;
            case FIXED:
                return ANSI_GREEN + "Fixed" + ANSI_RESET;
            default:
                return "Unknown";
        }
    }
}
