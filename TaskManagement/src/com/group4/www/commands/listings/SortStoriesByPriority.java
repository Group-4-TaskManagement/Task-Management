package com.group4.www.commands.listings;

import com.group4.www.commands.contracts.Command;
import com.group4.www.core.contacts.Repository;
import com.group4.www.models.tasks.contracts.Story;
import com.group4.www.models.utils.FormattingHelpers;
import com.group4.www.models.utils.ListingHelper;
import com.group4.www.models.utils.ValidationHelpers;

import java.util.Comparator;
import java.util.List;

public class SortStoriesByPriority implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 0;
    public static final String STORIES_HEADER = "STORIES";
    private final Repository repository;

    public SortStoriesByPriority(Repository repository) {
        this.repository = repository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        List <Story> stories = ListingHelper
                .sortByCondition(repository.getStories(), Comparator.comparing(Story::getPriority));
    return FormattingHelpers.listingFormatted(stories,STORIES_HEADER);

    }
}
