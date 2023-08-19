package com.group4.www.commands.show;

import com.group4.www.commands.contracts.Command;
import com.group4.www.core.contacts.Repository;
import com.group4.www.models.utils.FormattingHelpers;
import com.group4.www.models.utils.ValidationHelpers;

import java.util.List;

public class ShowAllTeamMembers implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    private static final String TEAM_NOT_EXIST = "This team does not exist";
    public static final String MEMBERS_HEADER = "members";
    private final Repository repository;

    public ShowAllTeamMembers(Repository repository) {
        this.repository = repository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        return FormattingHelpers.showAll(repository.findElement
                (repository.getTeams(),(team -> team.getName().equals(parameters.get(0))),
                        TEAM_NOT_EXIST).getMembers(), MEMBERS_HEADER);
    }
}
