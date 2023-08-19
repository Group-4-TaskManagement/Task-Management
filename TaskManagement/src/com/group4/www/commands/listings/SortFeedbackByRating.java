package com.group4.www.commands.listings;

import com.group4.www.commands.contracts.Command;
import com.group4.www.core.contacts.Repository;
import com.group4.www.models.tasks.contracts.Feedback;
import com.group4.www.models.utils.ListingHelper;
import com.group4.www.models.utils.ValidationHelpers;

import java.util.Comparator;
import java.util.List;

public class SortFeedbackByRating implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 0;
    private final Repository repository;

    public SortFeedbackByRating(Repository repository) {
        this.repository = repository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        return repository.listFeedbackByGivenCondition((ListingHelper.sortByCondition
                (repository.getFeedbacks(), Comparator.comparing(Feedback::getRating))));
    }
}
