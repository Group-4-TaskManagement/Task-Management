package com.group4.www.commands.show;

import com.group4.www.commands.contracts.Command;
import com.group4.www.core.contacts.Repository;
import com.group4.www.models.utils.FormattingHelpers;
import com.group4.www.models.utils.ValidationHelpers;

import java.util.List;

public class ShowBoardActivity implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    private static final String BOARD_NOT_EXIST = "This board does not exist";
    public static final String ACTIVITY_HEADER = "activity";
    private final Repository repository;

    public ShowBoardActivity(Repository repository) {
        this.repository = repository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters,EXPECTED_NUMBER_OF_ARGUMENTS);
        return FormattingHelpers.showAll((repository.findElement
                (repository.getBoards(),(board -> board.getName().equals(parameters.get(0)))
                        ,BOARD_NOT_EXIST).getBoardActivity()), ACTIVITY_HEADER);
    }
}
