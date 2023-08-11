package com.group4.www.commands.creations;

import com.group4.www.commands.Command;
import com.group4.www.commands.Listing.ListingHelper;
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

        List<Task> tasks = ListingHelper.listOfTasksWithAssignee(repository.getBugs(),
                repository.getStories(),
                repository.getFeedbacks());

        return repository.filterTaskByStatusAndAssignee(ListingHelper.filterByStatusAndAssignee(
                tasks,
                parameters.get(0),
                parameters.get(1)));
    }
}
