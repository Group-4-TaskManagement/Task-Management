package com.group4.www.commands;

import com.group4.www.commands.contracts.Command;
import com.group4.www.core.contacts.Repository;
import com.group4.www.models.utils.ParsingHelpers;
import com.group4.www.models.utils.ValidationHelpers;

import java.util.List;

public class ChangeStatus implements Command {


    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    public static final String TASK_NOT_EXIST = "Task does not exist";


    private final Repository repository;
    private int id;
    private String status;


    public ChangeStatus(Repository repository) {
        this.repository = repository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        parseParameters(parameters);
        status = parameters.get(1);




        return repository.findElement(repository.getTasks(),(task -> task.getId()==id),TASK_NOT_EXIST)
                .changeStatus(status);
    }

    private void parseParameters(List<String> parameters) {
        id = ParsingHelpers.tryParseInteger(parameters.get(0), "id of task");

    }

}