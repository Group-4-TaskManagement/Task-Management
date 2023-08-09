package com.group4.www.commands.creations;

import com.group4.www.commands.Command;
import com.group4.www.core.contacts.Repository;
import com.group4.www.models.utils.ParsingHelpers;
import com.group4.www.models.utils.ValidationHelpers;

import java.util.List;

public class AdvanceStoryPriority implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    public static final String ADVANCE_STORY_PRIORITY_MESS = "Priority of story with ID:%d changed successfully!";
    private final Repository repository;
    private int id;

    public AdvanceStoryPriority(Repository repository) {this.repository = repository;}

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        parseParameters(parameters);

        repository.advancePriorityStory(id);
        return String.format(ADVANCE_STORY_PRIORITY_MESS,id);
    }

    private void parseParameters(List<String> parameters){
        id = ParsingHelpers.tryParseInteger(parameters.get(0),"id for Story");
    }
}
