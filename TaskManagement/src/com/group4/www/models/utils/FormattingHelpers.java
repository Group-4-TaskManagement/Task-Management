package com.group4.www.models.utils;

import com.group4.www.models.contracts.Printable;
import com.group4.www.models.tasks.contracts.Task;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FormattingHelpers {
    public static <T extends Printable> String showAll(List<T> elements, String typeName){
        StringBuilder builder = new StringBuilder();
        builder.append(FormattingHelpers.pad(typeName.toUpperCase(),19,'-')).append("\n");
        if(elements.size()==0){
            builder.append(String.format("There are no %s added yet.\n",typeName));
            builder.append(FormattingHelpers.pad("",19,'-')).append("\n");
            return builder.toString();
        }
        for(T element: elements){
            builder.append(FormattingHelpers.pad(element.getAsString(),19, ' '));
            builder.append("\n");
        }
        builder.append(FormattingHelpers.pad("",19,'-'));
        return builder.toString();
    }

    public static <T extends Task> String listingFormatted(List<T> tasks, String header){
        StringBuilder builder = new StringBuilder();
        builder.append(FormattingHelpers.pad(header,19,'-')).append("\n");
        if (tasks.isEmpty()){
            builder.append("There are no result with current filter.\n");
            builder.append(FormattingHelpers.pad("",20,'-'));
            return builder.toString().trim();
        }
        tasks.stream().forEach(task-> builder.append(
                        FormattingHelpers.pad(task.getAsString(),19,' '))
                .append("\n"));
        builder.append(FormattingHelpers.pad("",20,'-'));
        return builder.toString().trim();
    }

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
