package com.group4.www.models.utils;

public class ParsingHelpers {

    public static final String INVALID_INPUT_FOR_INTEGER = "Invalid input %s. Should be a number";

    public static <E extends Enum<E>> E tryParseEnum(String valueToParse, Class<E> type, String errorMessage) {
        try {
            return Enum.valueOf(type, valueToParse.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format(errorMessage, valueToParse));
        }
    }

    public static int tryParseInteger(String valueToParse, String parameter) {
        try {
            return Integer.parseInt(valueToParse);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(String.format(INVALID_INPUT_FOR_INTEGER, parameter));
        }
    }
}

