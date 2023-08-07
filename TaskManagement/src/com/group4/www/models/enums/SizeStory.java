package com.group4.www.models.enums;

public enum SizeStory {
    SMALL,

    MEDIUM,
    LARGE;


    @Override
    public String toString() {

        switch (this){

            case SMALL :
                return "Small";

            case MEDIUM:
                return "Medium";

            case LARGE:
                return "Large";

            default:
                return "Unknown";
        }

    }
}
