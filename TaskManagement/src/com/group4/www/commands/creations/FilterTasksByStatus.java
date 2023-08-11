package com.group4.www.commands.creations;

import com.group4.www.commands.Command;
import com.group4.www.commands.Listing.ListingHelper;
import com.group4.www.core.contacts.Repository;
import com.group4.www.models.enums.SizeStory;
import com.group4.www.models.utils.ParsingHelpers;
import com.group4.www.models.utils.ValidationHelpers;

import java.util.List;

public class FilterTasksByStatus implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    private final Repository repository;
    private String status;

    public FilterTasksByStatus(Repository repository) {
        this.repository = repository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);


        return repository.filterTaskByStatus(ListingHelper.filterByStatus(
                repository.getBugs(),
                repository.getStories(),
                repository.getFeedbacks(),
                parameters.get(0)));
    }

}
