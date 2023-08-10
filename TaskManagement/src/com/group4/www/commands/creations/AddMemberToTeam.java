package com.group4.www.commands.creations;

import com.group4.www.commands.Command;
import com.group4.www.core.contacts.Repository;
import com.group4.www.models.utils.ValidationHelpers;

import java.util.List;

public class AddMemberToTeam implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    public static final String MEMBER_ADDED_TO_TEAM = "Member %s added to team %s";
    private String personName;
    private String teamName;
    private final Repository repository;

    public AddMemberToTeam(Repository repository) {
        this.repository = repository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount
                (parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        parseParameters(parameters);
        repository.addMemberToTeam(personName,teamName);
        return String.format(MEMBER_ADDED_TO_TEAM,personName,teamName);
    }
    private void parseParameters(List<String> parameters){
        personName = parameters.get(0);
        teamName = parameters.get(1);
    }
}
