package com.group4.www.commands.listings;

import com.group4.www.commands.contracts.Command;
import com.group4.www.core.contacts.Repository;
import com.group4.www.models.contracts.Member;
import com.group4.www.models.utils.ListingHelper;
import com.group4.www.models.utils.ValidationHelpers;

import java.util.List;

public class FilterStoriesByAssignee implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    public static final String MEMBER_DOES_NOT_EXIST = "Member with name %s does not exist";
    private Member member;
    private final Repository repository;

    public FilterStoriesByAssignee(Repository repository) {
        this.repository = repository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        parseParameters(parameters);

        return repository.listStoriesByGivenCondition
                (ListingHelper.filterByCondition
                        (ListingHelper.listOfTasksWithAssignee(repository.getStories())
                                ,story -> story.getAssignee().getName().equals(member.getName())));
    }

    private void parseParameters(List<String> parameters){
        member = repository.findElement(repository.getMembers(),
                member1 -> member1.getName().equals(parameters.get(0)),
                String.format(MEMBER_DOES_NOT_EXIST,parameters.get(0)));
    }
}
