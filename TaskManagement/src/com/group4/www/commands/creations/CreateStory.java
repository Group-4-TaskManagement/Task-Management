package com.group4.www.commands.creations;

import com.group4.www.commands.Command;
import com.group4.www.core.contacts.Repository;
import com.group4.www.models.contracts.Member;
import com.group4.www.models.enums.Priority;
import com.group4.www.models.enums.SizeStory;
import com.group4.www.models.enums.StatusStory;
import com.group4.www.models.tasks.contracts.Story;
import com.group4.www.models.utils.ParsingHelpers;
import com.group4.www.models.utils.ValidationHelpers;

import java.util.List;

public class CreateStory implements Command {
    public static final String STORY_PRIORITY_ERR = "Priority should be Low,Medium or High!";
    public static final String STORY_SIZE_ERR = "Size of story should be Small,Medium or Large!";
    public static final String STORY_STATUS_ERR = "Status should be Not Done, In Progress or Done!";
    public static final String STORY_CREATED = "Story with ID:%d was created";
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 7;
    private final Repository repository;
    private String title;
    private String description;
    private Member member;
    private Priority priority;
    private SizeStory sizeStory;
    private StatusStory statusStory;

    public CreateStory(Repository repository) {this.repository = repository;}


    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        parseParameters(parameters);

        Story story = repository.createStoryInBoard(title, description, member, priority, sizeStory, statusStory,parameters.get(6));
        return String.format(STORY_CREATED, story.getId());
    }

    private void parseParameters(List<String> parameters){
        title = parameters.get(0);
        description = parameters.get(1);
        member = repository.findMember(parameters.get(2));
        priority = ParsingHelpers.tryParseEnum(parameters.get(3),Priority.class, STORY_PRIORITY_ERR);
        sizeStory = ParsingHelpers.tryParseEnum(parameters.get(4),SizeStory.class, STORY_SIZE_ERR);
        statusStory = ParsingHelpers.tryParseEnum(parameters.get(5),StatusStory.class, STORY_STATUS_ERR);
    }
}
