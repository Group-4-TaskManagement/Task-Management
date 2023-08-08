package com.group4.www.core;

import com.group4.www.commands.Command;
import com.group4.www.commands.CreatePerson;
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
                return null;
            case CREATEBUG:
                return null;
            case CREATESTORY:
                return null;
            case CREATEFEEDBACK:
                return null;
            case CREATEBOARD:
                return null;
            case CREATEBOARDINTEAM:
                return null;
            case SHOWALLMEMBERS:
                return null;
            case SHOWPERSONACTIVITY:
                return null;
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
                return null;
            case REVERTBUGSTATUS:
                return null;
            case ADVANCESTORYPRIORITY:
                return null;
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

