package com.group4.www.commands.listings;

import com.group4.www.commands.contracts.Command;
import com.group4.www.models.contracts.Member;
import com.group4.www.models.enums.StatusBug;
import com.group4.www.models.tasks.contracts.AssignableTask;
import com.group4.www.models.utils.FormattingHelpers;
import com.group4.www.models.utils.ListingHelper;
import com.group4.www.core.contacts.Repository;
import com.group4.www.models.tasks.contracts.Task;
import com.group4.www.models.utils.ParsingHelpers;
import com.group4.www.models.utils.ValidationHelpers;

import java.util.List;

import static com.group4.www.commands.listings.FilterBugsByAssignee.MEMBER_DOES_NOT_EXIST;

public class FilterTasksByStatusAndAssignee implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    public static final String TASKS_HEADER = "TASKS";
    public static final String PARSING_ENUM_ERR = "Status should be one of the existing status of Bug,Story or Feedback!";
    private Member member;
    private final Repository repository;

    public FilterTasksByStatusAndAssignee(Repository repository) {
        this.repository = repository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        parseParameters(parameters);

        List<AssignableTask> assignableTasks = ListingHelper.listOfTasksWithAssignee(repository.getAssignableTasks());

       ListingHelper.filterByCondition
                (assignableTasks,assignableTask ->
                        assignableTask.getStatus().equals(parameters.get(0)) &&
                        assignableTask.getAssignee().getName().equals(parameters.get(1)));
       return FormattingHelpers.listingFormatted(assignableTasks,TASKS_HEADER);
    }

    private void parseParameters(List<String> parameters) {
        member = repository.findElement(repository.getMembers()
                ,member1 -> member1.getName().equals(parameters.get(1)),
                String.format(MEMBER_DOES_NOT_EXIST,parameters.get(1)));
    }
}
