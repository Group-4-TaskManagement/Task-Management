package com.group4.www.commands;

import com.group4.www.commands.contracts.Command;
import com.group4.www.core.contacts.Repository;
import com.group4.www.models.enums.StatusStory;
import com.group4.www.models.utils.ParsingHelpers;
import com.group4.www.models.utils.ValidationHelpers;

import java.util.List;

public class ChangeStoryStatus implements Command {
    public static final String PARSE_STORY_STATUS_ERROR = "Status for story can be Not Done, InProgress or Done!";
    public static final String STORY_STATUS_CHANGED = " Status of story was changed successfully.";
    private final Repository repository;
    private int id;
    private StatusStory statusStory;

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    public ChangeStoryStatus(Repository repository) {
        this.repository = repository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        parseParameters(parameters);

        repository.changeStoryStatus(id, statusStory);

        return STORY_STATUS_CHANGED;
    }

    private void parseParameters(List<String> parameters){
        id = ParsingHelpers.tryParseInteger(parameters.get(0), "id for bug");
        statusStory = ParsingHelpers.tryParseEnum(parameters.get(1), StatusStory.class, PARSE_STORY_STATUS_ERROR);
    }
}
