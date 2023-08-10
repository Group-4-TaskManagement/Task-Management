package com.group4.www.models.utils;

import java.math.BigDecimal;
import java.util.ArrayList;

public class FormattingHelpers {
    public static String pad(String source, int length, char paddingSymbol) {
        String[] input = source.split("");
        if (input.length >= length) return source;

        ArrayList<String> output = new ArrayList<>();
        for (String st :
                input) {
            output.add(st);
        }

        while (output.size() < length) {
            output.add(0, String.valueOf(paddingSymbol));
            output.add(String.valueOf(paddingSymbol));
        }

        String result = "";
        for (String st :
                output) {
            result += st;
        }

        return result;
    }
}
