package com.group4.www.commands.creations;

import com.group4.www.commands.Command;
import com.group4.www.core.contacts.Repository;

import java.util.List;

public class ShowAllTeam implements Command {
    private final Repository repository;

    public ShowAllTeam(Repository repository) {
        this.repository = repository;
    }

    @Override
    public String execute(List<String> parameters) {
        return String.valueOf(repository.showAllTeams());
    }

}
