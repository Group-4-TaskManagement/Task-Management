package com.group4.www.commands.listings;

import com.group4.www.commands.contracts.Command;
import com.group4.www.models.tasks.contracts.AssignableTask;
import com.group4.www.models.utils.ListingHelper;
import com.group4.www.core.contacts.Repository;
import com.group4.www.models.tasks.contracts.Task;
import com.group4.www.models.utils.ValidationHelpers;

import java.util.List;

public class FilterTasksByStatusAndAssignee implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    private final Repository repository;

    public FilterTasksByStatusAndAssignee(Repository repository) {
        this.repository = repository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        List<AssignableTask> assignableTasks = ListingHelper.listOfTasksWithAssignee(repository.getAssignableTasks());

        return repository.listAssignableTasksByGivenCondition(ListingHelper.filterByCondition
                (assignableTasks,assignableTask ->
                        assignableTask.getStatus().equals(parameters.get(0)) &&
                        assignableTask.getAssignee().getName().equals(parameters.get(1))));
    }
}
