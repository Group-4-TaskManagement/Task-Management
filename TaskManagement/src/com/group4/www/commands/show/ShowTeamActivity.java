package com.group4.www.commands.show;

import com.group4.www.commands.contracts.Command;
import com.group4.www.core.contacts.Repository;
import com.group4.www.models.utils.FormattingHelpers;
import com.group4.www.models.utils.ValidationHelpers;

import java.util.List;

public class ShowTeamActivity implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    private static final String TEAM_NOT_EXIST = "This team does not exist";

    public static final String ACTIVITY_HEADER = "activity";
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
        return FormattingHelpers.showAll(repository.findElement(repository.getTeams(),
                (team -> team.getName().equals(teamName)),TEAM_NOT_EXIST).getTeamActivity(), ACTIVITY_HEADER);
    }
    private void parseParameters(List<String> parameters){
        teamName = parameters.get(0);
    }
}
