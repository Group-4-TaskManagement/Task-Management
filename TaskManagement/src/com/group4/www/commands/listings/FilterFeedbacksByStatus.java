package com.group4.www.commands.listings;

import com.group4.www.commands.contracts.Command;
import com.group4.www.core.contacts.Repository;
import com.group4.www.models.enums.StatusFeedback;
import com.group4.www.models.tasks.contracts.Feedback;
import com.group4.www.models.utils.FormattingHelpers;
import com.group4.www.models.utils.ListingHelper;
import com.group4.www.models.utils.ParsingHelpers;
import com.group4.www.models.utils.ValidationHelpers;

import java.util.List;

public class FilterFeedbacksByStatus implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    public static final String FEEDBACKS_HEADER = "FEEDBACKS";
    public static final String PARSING_FEEDBACK_STATUS_ERR = "Status of Feedback should be New, Unscheduled, Scheduled or Done! !";
    private final Repository repository;
    private StatusFeedback status;

    public FilterFeedbacksByStatus(Repository repository) {
        this.repository = repository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        parseParameters(parameters);
        List<Feedback> feedbacks = ListingHelper.filterByCondition
                (repository.getFeedbacks(),feedback -> feedback.getStatus().equals(status.toString()));
        return FormattingHelpers.listingFormatted(feedbacks,FEEDBACKS_HEADER);
    }
    private void parseParameters(List<String> parameters){
        status= ParsingHelpers.tryParseEnum(parameters.get(0), StatusFeedback.class,PARSING_FEEDBACK_STATUS_ERR);
    }
}
