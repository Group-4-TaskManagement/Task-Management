package com.group4.www.commands.creations;

import com.group4.www.commands.Command;

import com.group4.www.core.contacts.Repository;
import com.group4.www.models.enums.StatusBug;
import com.group4.www.models.utils.ParsingHelpers;
import com.group4.www.models.utils.ValidationHelpers;

import java.util.List;

public class ChangeBugStatus implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    public static final String STATUS_PARSE_ERROR = "Status of bug can be Active or Fixed!";
    public static final String BUG_STATUS_CHANGED = " Status of bug was changed successfully";
    private final Repository repository;
    private int id;
    private StatusBug statusBug;

    public ChangeBugStatus(Repository repository) {this.repository = repository;}

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        parseParameters(parameters);

        repository.changeBugStatus(id, statusBug);
        return BUG_STATUS_CHANGED;
    }

    private void parseParameters(List<String> parameters){
        id = ParsingHelpers.tryParseInteger(parameters.get(0), "id of bug");
        statusBug = ParsingHelpers.tryParseEnum(parameters.get(1),StatusBug.class, STATUS_PARSE_ERROR);
    }
}
