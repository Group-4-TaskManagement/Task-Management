package com.group4.www.commands.creations;

import com.group4.www.commands.Command;
import com.group4.www.core.contacts.Repository;
import com.group4.www.models.utils.ParsingHelpers;
import com.group4.www.models.utils.ValidationHelpers;

import java.util.List;

public class ChangeFeedbackRating  implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    public static final String RATING_CHANGED_MESSAGE = "Rating changed to: %s";
    private int newRating;
    private int id;
    private final Repository repository;

    public ChangeFeedbackRating(Repository repository) {
        this.repository = repository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount
                (parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        parseParameters(parameters);
        repository.changeFeedbackRating(newRating,id);
        return String.format(RATING_CHANGED_MESSAGE,newRating);
    }
    private void parseParameters(List<String> parameters)
    {
        newRating= ParsingHelpers.tryParseInteger(
                parameters.get(0),"new rating");
        id= ParsingHelpers.tryParseInteger(parameters.get(1),"task id");
    }

}
