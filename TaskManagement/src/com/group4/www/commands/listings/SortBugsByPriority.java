package com.group4.www.commands.listings;

import com.group4.www.commands.contracts.Command;
import com.group4.www.models.tasks.contracts.Bug;
import com.group4.www.models.tasks.contracts.Story;
import com.group4.www.models.utils.FormattingHelpers;
import com.group4.www.models.utils.ListingHelper;
import com.group4.www.core.contacts.Repository;
import com.group4.www.models.utils.ValidationHelpers;

import java.util.Comparator;
import java.util.List;

public class SortBugsByPriority implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 0;
    public static final String BUGS_HEADER = "BUGS";
    private final Repository repository;

    public SortBugsByPriority(Repository repository) {
        this.repository = repository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        List<Bug> bugs = ListingHelper.sortByCondition
                (repository.getBugs(), Comparator.comparing(Bug::getPriority));

        return FormattingHelpers.listingFormatted(bugs,BUGS_HEADER);
    }
}
