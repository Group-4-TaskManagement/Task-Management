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
            case ADVANCEBUGSTATUS:
                return new AdvanceBugStatus(repository);
            case REVERTBUGSTATUS:
                return new RevertBugStatus(repository);
            case ADVANCESTORYPRIORITY:
                return new AdvanceStoryPriority(repository);
            case ADVANCEBUGPRIORITY:
                return null;
            case REVERTSTORYPRIORITY:
                return null;
            case REVERTBUGPRIORITY:
                return null;
            case ADVANCEBUGSEVERITY:
                return null;
            case REVERTBUGSEVERITY:
                return null;
            case ADVANCESTORYSTATUS:
                return null;
            case REVERTSTORYSTATUS:
                return new RevertStoryStatus(repository);
            case ADVANCESTORYSIZE:
                return new AdvanceStorySize(repository);
            case REVERTSTORYSIZE:
                return new RevertStorySize(repository);
            case ADVANCEFEEDBACKSTATUS:
                return null;
            case REVERTFEEDBACKSTATUS:
                return null;
            case CHANGEFEEDBACKRATING:
                return new ChangeFeedbackRating(repository);
            case ASSIGNTASKTOMEMBER:
                return new AssignTaskToMember(repository);
            case UNASSIGNTASKTOMEMBER:
                return new UnAssignTaskToMember(repository);
            case ADDCOMMENTTOTASK:
                return new AddCommentToTask(repository);
            default: throw new IllegalArgumentException(String.format(INVALID_COMMAND,commandTypeAsString));
        }
    }

}

