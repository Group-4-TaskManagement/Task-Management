package com.group4.www.commands.listings;

import com.group4.www.commands.contracts.Command;
import com.group4.www.models.utils.FormattingHelpers;
import com.group4.www.models.utils.ListingHelper;
import com.group4.www.core.contacts.Repository;
import com.group4.www.models.enums.StatusBug;
import com.group4.www.models.utils.ParsingHelpers;
import com.group4.www.models.utils.ValidationHelpers;

import java.util.List;

public class FilterBugsByStatusAndAssignee implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    private final Repository repository;

    private StatusBug statusBug;
    public FilterBugsByStatusAndAssignee(Repository repository) {this.repository = repository;}


    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        parseParameters(parameters);


        return repository.listBugsByGivenCondition(ListingHelper.filterByCondition
                (ListingHelper.listOfTasksWithAssignee(repository.getBugs())
                        ,bug -> bug.getAssignee().getName().equals(parameters.get(1))&&
                        bug.getStatus().equals(statusBug.toString())));
    }

    private void parseParameters(List<String> parameters){
        statusBug = ParsingHelpers.tryParseEnum(parameters.get(0),StatusBug.class,"Bug status can be Active or Fixed!");
    }
}
