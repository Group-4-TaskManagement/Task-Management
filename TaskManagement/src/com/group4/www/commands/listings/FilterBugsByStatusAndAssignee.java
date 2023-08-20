package com.group4.www.commands.listings;

import com.group4.www.commands.contracts.Command;
import com.group4.www.models.contracts.Member;
import com.group4.www.models.tasks.contracts.Bug;
import com.group4.www.models.utils.FormattingHelpers;
import com.group4.www.models.utils.ListingHelper;
import com.group4.www.core.contacts.Repository;
import com.group4.www.models.enums.StatusBug;
import com.group4.www.models.utils.ParsingHelpers;
import com.group4.www.models.utils.ValidationHelpers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.group4.www.commands.listings.FilterBugsByAssignee.MEMBER_DOES_NOT_EXIST;

public class FilterBugsByStatusAndAssignee implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    public static final String BUGS_HEADER = "BUGS";
    private final Repository repository;
    private Member member;
    private StatusBug statusBug;

    public FilterBugsByStatusAndAssignee(Repository repository) {
        this.repository = repository;
    }


    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        parseParameters(parameters);

        List<Bug> bugs = ListingHelper.filterByCondition
                (ListingHelper.listOfTasksWithAssignee(repository.getBugs())
                        , bug -> bug.getAssignee().getName().equals(member.getName()) &&
                                bug.getStatus().equals(statusBug.toString()));
        return FormattingHelpers.listingFormatted(bugs, BUGS_HEADER);
    }

    private void parseParameters(List<String> parameters) {
        statusBug = ParsingHelpers.tryParseEnum(parameters.get(0), StatusBug.class, "Bug status can be Active or Fixed!");
        member = repository.findElement(repository.getMembers()
                ,member1 -> member1.getName().equals(parameters.get(1)),
                String.format(MEMBER_DOES_NOT_EXIST,parameters.get(1)));
    }

}
