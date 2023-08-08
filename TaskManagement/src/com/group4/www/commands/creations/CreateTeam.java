package com.group4.www.commands.creations;

import com.group4.www.commands.Command;
import com.group4.www.core.contacts.Repository;
import com.group4.www.models.utils.ValidationHelpers;

import java.util.List;

public class CreateTeam implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    public static final String CREATED_TEAM_MESS = "Team with name %s was created";
    private final Repository repository;


    public CreateTeam(Repository repository) {
        this.repository = repository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters,EXPECTED_NUMBER_OF_ARGUMENTS);
        repository.createTeam(parameters.get(0));

        return String.format(CREATED_TEAM_MESS,parameters.get(0));
    }
}
