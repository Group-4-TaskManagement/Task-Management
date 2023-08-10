package com.group4.www.commands.creations;

import com.group4.www.commands.Command;
import com.group4.www.core.contacts.Repository;
import com.group4.www.models.contracts.Member;
import com.group4.www.models.tasks.contracts.Bug;
import com.group4.www.models.tasks.contracts.Feedback;
import com.group4.www.models.utils.ParsingHelpers;
import com.group4.www.models.utils.ValidationHelpers;

import java.util.List;

public class CreateFeedback implements Command {
    private static final String FEEDBACK_CREATED = "Feedback with ID:%d was created";
    public static final int EXPECTED_NUMBER_OF_PARAMETERS = 5;
    private final Repository repository;
    private String title;
    private String description;
    private Member member;
    private int rating;

    public CreateFeedback(Repository repository) {
        this.repository = repository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_PARAMETERS);
        parseParameters(parameters);
        Feedback feedback = repository.createFeedbackInBoard(title, description, member ,rating,parameters.get(4));
        return String.format(FEEDBACK_CREATED,feedback.getId());
    }
    private void parseParameters(List<String> parameters){
        title = parameters.get(0);
        description = parameters.get(1);
        member = repository.findMember(parameters.get(2));
        rating= ParsingHelpers.tryParseInteger(parameters.get(3),"feedback rating");
    }
}
