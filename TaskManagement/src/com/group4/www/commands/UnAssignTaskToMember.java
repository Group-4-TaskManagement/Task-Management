package com.group4.www.commands;

import com.group4.www.commands.contracts.Command;
import com.group4.www.core.contacts.Repository;
import com.group4.www.models.contracts.Member;
import com.group4.www.models.utils.ParsingHelpers;
import com.group4.www.models.utils.ValidationHelpers;

import java.util.List;

public class UnAssignTaskToMember implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    public static final String MEMBER_DOES_NOT_EXIST = "Member with name %s does not exist";
    public static final String TASK_REMOVED = "Task with ID:%d was removed.";
    private final Repository repository;
    private Member member;
    private int id;

    public UnAssignTaskToMember(Repository repository) {
        this.repository = repository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters,EXPECTED_NUMBER_OF_ARGUMENTS);

        parseParameters(parameters);

        repository.unAssignTaskToMember(id,member.getName());

        return String.format(TASK_REMOVED,id);
    }

    private void parseParameters(List<String> parameters){
        id = ParsingHelpers.tryParseInteger(parameters.get(0),"id for task");
        member = repository.findElement(repository.getMembers()
                ,member1 -> member1.getName().equals(parameters.get(1)),
                String.format(MEMBER_DOES_NOT_EXIST,parameters.get(1)));

    }
}
