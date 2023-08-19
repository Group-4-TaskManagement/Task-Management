package com.group4.www.commands.show;

import com.group4.www.commands.contracts.Command;
import com.group4.www.core.contacts.Repository;
import com.group4.www.models.utils.FormattingHelpers;
import com.group4.www.models.utils.ValidationHelpers;

import java.util.List;

public class ShowAllTeam implements Command {
    public static final String TEAMS_HEADER = "teams";
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 0;
    private final Repository repository;

    public ShowAllTeam(Repository repository) {
        this.repository = repository;
    }

    @Override
    public String execute(List<String> parameters) {



        return FormattingHelpers.showAll(repository.getTeams(), TEAMS_HEADER);
    }

}
