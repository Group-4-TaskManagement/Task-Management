package com.group4.www.commands;

import com.group4.www.core.contacts.Repository;
import com.group4.www.models.utils.ValidationHelpers;

import java.util.List;

public class CreatePerson implements Command{
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    public static final String CREATED_MEMBER_MESS = "Member with name %s was created";

    private final Repository repository;


    public CreatePerson(Repository repository) {
        this.repository = repository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters,EXPECTED_NUMBER_OF_ARGUMENTS);
        repository.createPerson(parameters.get(0));

        return String.format(CREATED_MEMBER_MESS,parameters.get(0));
    }

}
