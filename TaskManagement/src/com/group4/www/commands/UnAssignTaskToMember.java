package com.group4.www.commands;

import com.group4.www.commands.contracts.Command;
import com.group4.www.core.contacts.Repository;
import com.group4.www.models.utils.ParsingHelpers;
import com.group4.www.models.utils.ValidationHelpers;

import java.util.List;

public class UnAssignTaskToMember implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    public static final String TASK_REMOVED = "Task with ID:%d was removed.";
    private final Repository repository;

    private int id;

    public UnAssignTaskToMember(Repository repository) {
        this.repository = repository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters,EXPECTED_NUMBER_OF_ARGUMENTS);

        parseParameters(parameters);

        repository.unAssignTaskToMember(Integer.parseInt(parameters.get(0)),parameters.get(1));

        return String.format(TASK_REMOVED,parameters.get(0));
    }

    private void parseParameters(List<String> parameters){
        id = ParsingHelpers.tryParseInteger(parameters.get(0),"id for task");
    }
}
