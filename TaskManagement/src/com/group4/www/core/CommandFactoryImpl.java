package com.group4.www.core;

import com.group4.www.commands.Command;
import com.group4.www.commands.creations.*;
import com.group4.www.commands.enums.CommandType;
import com.group4.www.core.contacts.CommandFactory;
import com.group4.www.core.contacts.Repository;
import com.group4.www.models.utils.ParsingHelpers;

public class CommandFactoryImpl implements CommandFactory {
    private static final String INVALID_COMMAND = "Invalid command name: %s!";

    @Override
    public Command createCommandFromCommandName(String commandTypeAsString, Repository repository) {
        CommandType commandType = ParsingHelpers.tryParseEnum(commandTypeAsString,
                CommandType.class, String.format(INVALID_COMMAND, commandTypeAsString));
        switch (commandType) {
            case CREATEPERSON:
                return new CreatePerson(repository);
            case CREATETEAM:
                return new CreateTeam(repository);
            case CREATEBUGINBOARD:
                return new CreateBug(repository);
            case CREATESTORYINBOARD:
                return new CreateStory(repository);
            case CREATEFEEDBACKINBOARD:
                return new CreateFeedback(repository);
            case CREATEBOARD:
                return new CreateBord(repository);
            case CREATEBOARDINTEAM:
                return new CreateBoardInTeam(repository);
            case SHOWALLMEMBERS:
                return new ShowAllMembers(repository);
            case SHOWPERSONACTIVITY:
                return new ShowPersonActivity(repository);
            case SHOWALLTEAMS:
                return new ShowAllTeam(repository);
            case SHOWTEAMACTIVITY:
                return new ShowTeamActivity(repository);
            case ADDMEMBERTOTEAM:
                return new AddMemberToTeam(repository);
            case SHOWALLTEAMMEMBERS:
                return new ShowAllTeamMembers(repository);
            case SHOWALLTEAMBOARDS:
                return new ShowAllTeamBoards(repository);
            case SHOWBOARDACTIVITY:
                return new ShowBoardActivity(repository);
            case CHANGEFEEDBACKRATING:
                return new ChangeFeedbackRating(repository);
            case ASSIGNTASKTOMEMBER:
                return new AssignTaskToMember(repository);
            case UNASSIGNTASKTOMEMBER:
                return new UnAssignTaskToMember(repository);
            case ADDCOMMENTTOTASK:
                return new AddCommentToTask(repository);
            case CHANGEBUGSTATUS:
                return new ChangeBugStatus(repository);
            case CHANGEBUGPRIORITY:
                return new ChangeBugPriority(repository);
            case CHANGEBUGSEVERITY:
                return new ChangeBugSeverity(repository);
            case CHANGESTORYSTATUS:
                return null;
            case CHANGESTORYPRIORITY:
                return null;
            case CHANGESTORYSIZE:
                return null;
            case CHANGEFEEDBACKSTATUS:
                return null;
            default: throw new IllegalArgumentException(String.format(INVALID_COMMAND,commandTypeAsString));
        }
    }

}

