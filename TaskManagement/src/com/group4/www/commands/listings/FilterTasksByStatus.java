package com.group4.www.commands.listings;

import com.group4.www.commands.contracts.Command;
import com.group4.www.models.tasks.contracts.AssignableTask;
import com.group4.www.models.utils.FormattingHelpers;
import com.group4.www.models.utils.ListingHelper;
import com.group4.www.core.contacts.Repository;
import com.group4.www.models.tasks.contracts.Task;
import com.group4.www.models.utils.ValidationHelpers;

import java.util.List;

public class FilterTasksByStatus implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    public static final String TASKS_HEADER = "TASKS";
    private final Repository repository;

    public FilterTasksByStatus(Repository repository) {
        this.repository = repository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        List<AssignableTask> assignableTasks = ListingHelper.listOfTasksWithAssignee(repository.getAssignableTasks());

        ListingHelper.filterByCondition(assignableTasks, assignableTask -> assignableTask.getStatus().equals(parameters.get(0)));

        return FormattingHelpers.listingFormatted(assignableTasks, TASKS_HEADER);
    }

}
