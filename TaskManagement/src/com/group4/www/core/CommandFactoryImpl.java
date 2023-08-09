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
            case CREATEBUG:
                return new CreateBug(repository);
            case CREATESTORY:
                return new CreateStory(repository);
            case CREATEFEEDBACK:
                return null;
            case CREATEBOARD:
                return null;
            case CREATEBOARDINTEAM:
                return new CreateBoardInTeam(repository);
            case SHOWALLMEMBERS:
                return new ShowAllMembers(repository);
            case SHOWPERSONACTIVITY:
                return new ShowPersonActivity(repository);
            case SHOWALLTEAM:
                return null;
            case SHOWTEAMACTIVITY:
                return null;
            case ADDMEMBERTOTEAM:
                return null;
            case SHOWALLTEAMMEMBERS:
                return null;
            case SHOWALLTEAMBOARDS:
                return null;
            case SHOWBOARDACTIVITY:
                return null;
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
                return null;
            case ADVANCESTORYSIZE:
                return null;
            case REVERTSTORYSIZE:
                return null;
            case ADVANCEFEEDBACKSTATUS:
                return null;
            case REVERTFEEDBACKSTATUS:
                return null;
            case CHANGEFEEDBACKRATING:
                return null;
            case ASSIGNTASKTOMEMBER:
                return null;
            case UNASSIGNTASKTOMEMBER:
                return null;
            case ADDCOMMENTTOTASK:
                return null;
            case FINDBUG:
                return null;
            case FINDSTORY:
                return null;
            case FINDFEEDBACK:
                return null;
            case FINDMEMBER:
                return null;
            case FINDBOARD:
                return null;
            case FINDTEAM:
                return null;
            default: throw new IllegalArgumentException(String.format(INVALID_COMMAND,commandTypeAsString));
        }
    }

}

