package com.group4.www.commands.show;

import com.group4.www.commands.contracts.Command;
import com.group4.www.core.contacts.Repository;
import com.group4.www.models.contracts.Member;
import com.group4.www.models.utils.FormattingHelpers;
import com.group4.www.models.utils.ValidationHelpers;

import java.util.List;

public class ShowPersonActivity implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    private static final String MEMBER_NOT_EXIST = "A member with name %s does not exist";

    public static final String ACTIVITY_HEADER = "activity";
    private Member member;
    private final Repository repository;

    public ShowPersonActivity(Repository repository) {this.repository = repository;}

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        parseParameters(parameters);



       return FormattingHelpers.showAll(repository.findElement(repository.getMembers(),
                (member -> member.getName().equals(member.getName())),MEMBER_NOT_EXIST).getMemberActivity(), ACTIVITY_HEADER);


    }

    private void parseParameters(List<String> parameters){
        member = repository.findElement(repository.getMembers()
                ,member1 -> member1.getName().equals(parameters.get(0)),
                String.format(MEMBER_NOT_EXIST,parameters.get(0)));
    }
}
