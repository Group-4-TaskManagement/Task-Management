package com.group4.www.commands.listings;

import com.group4.www.commands.contracts.Command;
import com.group4.www.models.utils.FormattingHelpers;
import com.group4.www.models.utils.ListingHelper;
import com.group4.www.core.contacts.Repository;
import com.group4.www.models.tasks.contracts.Task;
import com.group4.www.models.utils.ValidationHelpers;

import java.util.List;

public class FilterTasksByTitle implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    public static final String TASKS_HEADER = "TASKS";
    private final Repository repository;

    public FilterTasksByTitle(Repository repository) {this.repository = repository;}

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);


        List <Task> tasks = ListingHelper.filterByCondition
                (repository.getTasks(),task -> task.getTitle().equals(parameters.get(0)));
        return FormattingHelpers.listingFormatted(tasks,TASKS_HEADER);
    }
}
