package com.group4.www.commands.creations;

import com.group4.www.commands.Command;
import com.group4.www.commands.Listing.ListingHelper;
import com.group4.www.core.contacts.Repository;
import com.group4.www.models.contracts.Member;
import com.group4.www.models.utils.ValidationHelpers;

import java.util.List;

public class FilterBugsByAssignee implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    private final Repository repository;
    private Member member;
    public FilterBugsByAssignee(Repository repository) {
        this.repository = repository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        parseParameters(parameters);

        return repository.filterBugsByAssignee(ListingHelper.filterByAssignee(repository.getBugs(),member.getName()));
    }

    private void parseParameters(List<String> parameters){
        member = repository.findMember(parameters.get(0));
        //TODO NOT WORKING
    }
}
