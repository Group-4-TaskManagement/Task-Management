package com.group4.www.commands.creations;

import com.group4.www.commands.contracts.Command;
import com.group4.www.core.contacts.Repository;
import com.group4.www.models.utils.ParsingHelpers;
import com.group4.www.models.utils.ValidationHelpers;

import java.util.List;

public class ChangeStatus implements Command {


    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    public static final String STATUS_CHANGED = "Status of item was changed successfully.";

    private final Repository repository;
    private int id;
    private String status;


    public ChangeStatus(Repository repository) {
        this.repository = repository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        parseParameters(parameters);
        status = parameters.get(1);
        repository.changeStatus(id, status);


        return STATUS_CHANGED;
    }

    private void parseParameters(List<String> parameters) {
        id = ParsingHelpers.tryParseInteger(parameters.get(0), "id of task");

    }

}