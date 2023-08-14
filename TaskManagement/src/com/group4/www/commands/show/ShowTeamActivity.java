package com.group4.www.commands.show;

import com.group4.www.commands.contracts.Command;
import com.group4.www.core.contacts.Repository;
import com.group4.www.models.utils.ValidationHelpers;

import java.util.List;

public class ShowTeamActivity implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    private String teamName;
    private final Repository repository;

    public ShowTeamActivity(Repository repository) {
        this.repository = repository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount
                (parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        parseParameters(parameters);
        return repository.showTeamActivity(teamName);
    }
    private void parseParameters(List<String> parameters){
        teamName = parameters.get(0);
    }
}
