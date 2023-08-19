package com.group4.www.commands.listings;

import com.group4.www.commands.contracts.Command;
import com.group4.www.core.contacts.Repository;
import com.group4.www.models.contracts.Member;
import com.group4.www.models.enums.StatusStory;
import com.group4.www.models.utils.ListingHelper;
import com.group4.www.models.utils.ParsingHelpers;
import com.group4.www.models.utils.ValidationHelpers;

import java.util.List;

public class FilterStoriesByStatusAndAssignee implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    public static final String MEMBER_DOES_NOT_EXIST = "Member with name %s does not exist";
    public static final String PARSE_STORY_STATUS_ERROR = "Status of story can be Not Done, InProgress and Done!";
    private Member member;
    private final Repository repository;
    private StatusStory statusStory;

    public FilterStoriesByStatusAndAssignee(Repository repository) {
        this.repository = repository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        parseParameters(parameters);

        return repository.listStoriesByGivenCondition
                (ListingHelper.filterByStatusAndAssignee(repository.getStories()
                        ,statusStory.toString(),member.getName()));
    }

    private void parseParameters(List<String> parameters){
        statusStory = ParsingHelpers.tryParseEnum(parameters.get(0), StatusStory.class, PARSE_STORY_STATUS_ERROR);
        member = repository.findElement(repository.getMembers(),member1 -> member1.getName().equals(parameters.get(1))
                ,String.format(MEMBER_DOES_NOT_EXIST,parameters.get(1)));
    }
}
