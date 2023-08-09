package com.group4.www.commands.creations;

import com.group4.www.commands.Command;
import com.group4.www.core.contacts.Repository;
import com.group4.www.models.contracts.Board;
import com.group4.www.models.contracts.Team;
import com.group4.www.models.utils.ValidationHelpers;

import java.util.List;

public class CreateBoardInTeam implements Command {
    public static final String BOARD_CREATED_IN_TEAM_MESS = "Board with name %s was created and added to team %s.";
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    private final Repository repository;

    private String boardName;
    private Team team;

    public CreateBoardInTeam(Repository repository) {this.repository = repository;}

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        parseParameters(parameters);

        Board board = repository.createBoardInTeam(boardName,team.getName());
        return String.format(BOARD_CREATED_IN_TEAM_MESS,boardName,team.getName());
    }


    private void parseParameters(List<String> parameters){
        boardName = parameters.get(0);
        team = repository.findTeam(parameters.get(1));
    }
}
