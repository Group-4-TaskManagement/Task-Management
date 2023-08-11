package com.group4.www.commands.creations;

import com.group4.www.commands.Command;
import com.group4.www.core.contacts.Repository;
import com.group4.www.models.contracts.Board;
import com.group4.www.models.utils.ValidationHelpers;

import java.util.List;

public class CreateBoard implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    public static final String CREATED_BOARD = "Board with name %s was created";

    private final Repository repository;
    private String name;

    public CreateBoard(Repository repository) {
        this.repository = repository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        parseParameters(parameters);
        Board board= repository.createBoard(name);
        return String.format(CREATED_BOARD,board.getName());
    }
    private void parseParameters(List<String> parameters){
        name=parameters.get(0);
    }
}
