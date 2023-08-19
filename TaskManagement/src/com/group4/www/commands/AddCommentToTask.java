package com.group4.www.commands;

import com.group4.www.commands.contracts.Command;
import com.group4.www.core.contacts.Repository;
import com.group4.www.models.contracts.Comment;
import com.group4.www.models.contracts.Member;
import com.group4.www.models.utils.ParsingHelpers;
import com.group4.www.models.utils.ValidationHelpers;

import java.util.List;

public class AddCommentToTask implements Command {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;
    public static final String MEMBER_DOES_NOT_EXIST = "Member with name %s does not exist";
    public static final String COMMENT_ADDED_TO_TASK = "A comment has been added to the task with ID:%d";
    private final Repository repository;
    private Member member;
    private int taskId;

    public AddCommentToTask(Repository repository) {
        this.repository = repository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters,EXPECTED_NUMBER_OF_ARGUMENTS);

        parseParameters(parameters);

        Comment comment = repository.createComment(member.getName(),parameters.get(1));
        repository.addCommentToTask(comment,taskId);

        return String.format(COMMENT_ADDED_TO_TASK,taskId);
    }
    private void parseParameters(List<String> parameters){
        member = repository.findElement(repository.getMembers(),
                (member1 -> member1.getName().equals(parameters.get(0))),
                String.format(MEMBER_DOES_NOT_EXIST,parameters.get(0)));
        taskId = ParsingHelpers.tryParseInteger(parameters.get(2),"id for task");
    }
}
