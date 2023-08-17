package com.group4.www.commands.show;

import com.group4.www.commands.contracts.Command;
import com.group4.www.core.contacts.Repository;
import com.group4.www.models.utils.ParsingHelpers;
import com.group4.www.models.utils.ValidationHelpers;

import java.util.List;

public class ShowTaskActivity implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    private final Repository repository;
    private int id;
    public ShowTaskActivity(Repository repository) {
        this.repository = repository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        parseParameters(parameters);

        return repository.showTaskActivity(id);
    }

    private void parseParameters(List<String> parameters){
        id = ParsingHelpers.tryParseInteger(parameters.get(0),"id for task");
    }
}
