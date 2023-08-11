package com.group4.www.commands.creations;

import com.group4.www.commands.Command;
import com.group4.www.commands.Listing.ListingHelper;
import com.group4.www.core.contacts.Repository;
import com.group4.www.models.tasks.contracts.Task;
import com.group4.www.models.utils.ValidationHelpers;

import java.util.List;

public class FilterTasksByTitle implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    private final Repository repository;

    public FilterTasksByTitle(Repository repository) {this.repository = repository;}

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        List<Task> tasks = ListingHelper.mergeToTasks(repository.getBugs(),
                repository.getStories(),
                repository.getFeedbacks());

        return repository.filterTasksByTitle(ListingHelper.filterByTitle(tasks,parameters.get(0)));
    }
}
