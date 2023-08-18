package com.group4.www.commands;

import com.group4.www.commands.contracts.Command;
import com.group4.www.core.contacts.Repository;
import com.group4.www.models.enums.Priority;
import com.group4.www.models.utils.ParsingHelpers;
import com.group4.www.models.utils.ValidationHelpers;

import java.util.List;

public class ChangeStoryPriority implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    public static final String PARSE_STORY_PRIORITY_ERROR = "The priority of story can be Low, Medium or High!";
    public static final String STORY_PRIORITY_CHANGE = " Priority of story was changed successfully.";
    private final Repository repository;
    private int id;
    private Priority priorityStory;

    public ChangeStoryPriority(Repository repository) {
        this.repository = repository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        parseParameters(parameters);

        repository.changeStoryPriority(id,priorityStory);

        return STORY_PRIORITY_CHANGE;
    }

    private void parseParameters(List<String> parameters){
        id = ParsingHelpers.tryParseInteger(parameters.get(0), "id for story");
        priorityStory = ParsingHelpers.tryParseEnum(parameters.get(1), Priority.class, PARSE_STORY_PRIORITY_ERROR);
    }
}
