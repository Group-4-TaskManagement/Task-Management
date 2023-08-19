package com.group4.www.commands;

import com.group4.www.commands.contracts.Command;
import com.group4.www.core.contacts.Repository;
import com.group4.www.models.enums.Priority;
import com.group4.www.models.utils.ParsingHelpers;
import com.group4.www.models.utils.ValidationHelpers;

import java.util.List;

public class ChangePriority implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    public static final String PARSE_STORY_PRIORITY_ERROR = "The priority of task can be Low, Medium or High!";
    public  static final String TASK_NOT_EXIST = "Task does not exist";

    private final Repository repository;
    private int id;
    private Priority priority;

    public ChangePriority(Repository repository) {
        this.repository = repository;
    }

    @Override
    public String execute(List<String> parameters) {


        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        parseParameters(parameters);

        return repository.findElement(repository.getAssignableTasks(),(element -> element.getId()==id), TASK_NOT_EXIST)
                .changePriority(priority);


    }

    private void parseParameters(List<String> parameters){
        id = ParsingHelpers.tryParseInteger(parameters.get(0), "id for story");
        priority = ParsingHelpers.tryParseEnum(parameters.get(1), Priority.class, PARSE_STORY_PRIORITY_ERROR);
    }
}
