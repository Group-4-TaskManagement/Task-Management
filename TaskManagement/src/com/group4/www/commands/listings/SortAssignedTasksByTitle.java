package com.group4.www.commands.listings;

import com.group4.www.commands.contracts.Command;
import com.group4.www.models.tasks.contracts.AssignableTask;
import com.group4.www.models.utils.FormattingHelpers;
import com.group4.www.models.utils.ListingHelper;
import com.group4.www.core.contacts.Repository;
import com.group4.www.models.tasks.contracts.Task;
import com.group4.www.models.utils.ValidationHelpers;

import java.util.Comparator;
import java.util.List;

public class SortAssignedTasksByTitle implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 0;
    public static final String TASKS_HEADER = "TASKS";
    private final Repository repository;

    public SortAssignedTasksByTitle(Repository repository) {
        this.repository = repository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        List<AssignableTask> assignableTaskstasks = ListingHelper.listOfTasksWithAssignee(repository.getAssignableTasks());

        ListingHelper.sortByCondition
                (assignableTaskstasks, Comparator.comparing(AssignableTask::getTitle));

        return FormattingHelpers.listingFormatted(assignableTaskstasks,TASKS_HEADER);
    }
}
