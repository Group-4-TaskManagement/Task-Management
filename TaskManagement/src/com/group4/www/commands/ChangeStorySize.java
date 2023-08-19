package com.group4.www.commands;

import com.group4.www.commands.contracts.Command;
import com.group4.www.core.contacts.Repository;
import com.group4.www.models.enums.SizeStory;
import com.group4.www.models.utils.ParsingHelpers;
import com.group4.www.models.utils.ValidationHelpers;

import java.util.List;

public class ChangeStorySize implements Command {
    public static final String PARSE_STORY_SIZE_ERROR = "Size for story can be Small, Medium or Large!";

    private final Repository repository;
    private int id;
    private SizeStory sizeStory;

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    public ChangeStorySize(Repository repository) {
        this.repository = repository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        parseParameters(parameters);

      return   repository.changeStorySize(id, sizeStory);


    }

    private void parseParameters(List<String> parameters){
        id = ParsingHelpers.tryParseInteger(parameters.get(0), "id for bug");
        sizeStory = ParsingHelpers.tryParseEnum(parameters.get(1), SizeStory.class, PARSE_STORY_SIZE_ERROR);
    }
}
