package com.group4.www.commands;

import com.group4.www.commands.contracts.Command;
import com.group4.www.core.contacts.Repository;
import com.group4.www.models.utils.ParsingHelpers;
import com.group4.www.models.utils.ValidationHelpers;

import java.util.List;

public class ChangeFeedbackStatus implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    public static final String STATUS_CHANGED_MESSAGE = "Status changed!";
    private String command;
    private int id;
    private final Repository repository;

    public ChangeFeedbackStatus(Repository repository) {
        this.repository = repository;
    }

    @Override
    public String execute(List<String> parameters) {
            ValidationHelpers.validateArgumentsCount
                    (parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        parseParameters(parameters);
        repository.changeFeedbackStatus(command,id);
        return String.format(STATUS_CHANGED_MESSAGE);
    }
    private void parseParameters(List<String> parameters)
    {
        command= parameters.get(0);
        id= ParsingHelpers.tryParseInteger(parameters.get(1),"task id");
    }
}
