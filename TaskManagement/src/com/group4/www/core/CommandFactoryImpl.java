package com.group4.www.core;

import com.group4.www.commands.*;
import com.group4.www.commands.contracts.Command;
import com.group4.www.commands.creations.*;
import com.group4.www.core.enums.CommandType;
import com.group4.www.commands.listings.*;
import com.group4.www.commands.show.*;
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
                return new CreateBoard(repository);
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
                return new ChangeStoryStatus(repository);
            case CHANGESTORYPRIORITY:
                return new ChangeStoryPriority(repository);
            case CHANGESTORYSIZE:
                return new ChangeStorySize(repository);
            case CHANGEFEEDBACKSTATUS:
                return new ChangeFeedbackStatus(repository);
            case FILTERTASKSBYTITLE:
                return new FilterTasksByTitle(repository);
            case SORTTASKSBYTITLE:
                return new SortTasksByTitle(repository);
            case FILTERTASKSBYSTATUS:
                return new FilterTasksByStatus(repository);
            case FILTERTASKSBYASSIGNEE:
                return new FilterTasksByAssignee(repository);
            case FILTERTASKSBYSTATUSANDASSIGNEE:
                return new FilterTasksByStatusAndAssignee(repository);
            case SORTASSIGNEDTASKSBYTITLE:
                return new SortAssignedTasksByTitle(repository);
            case FILTERBUGSBYSTATUS:
                return new FilterBugsByStatus(repository);
            case FILTERBUGSBYASSIGNEE:
                return new FilterBugsByAssignee(repository);
            case FILTERBUGSBYSTATUSANDASSIGNEE:
                return new FilterBugsByStatusAndAssignee(repository);
            case SORTBUGSBYTITLE:
                return new SortBugsByTitle(repository);
            case SORTBUGSBYPRIORITY:
                return new SortBugsByPriority(repository);
            case SORTBUGSBYSEVERITY:
                return new SortBugsBySeverity(repository);
            case SHOWTASKACTIVITY:
                return new ShowTaskActivity(repository);
            case FILTERSTORIESBYSTATUS:
                return new FilterStoriesByStatus(repository);
            case FILTERSTORIESBYSTATUSANDASSIGNEE:
                return new FilterStoriesByStatusAndAssignee(repository);
            case FILTERSTORIESBYASSIGNEE:
                return new FilterStoriesByAssignee(repository);
            case SORTSTORIESBYTITLE:
                return new SortStoriesByTitle(repository);
            case SORTSTORIESBYPRIORITY:
                return new SortStoriesByPriority(repository);
            case SORTSTORIESBYSIZE:
                return new SortStoriesBySize(repository);
            default: throw new IllegalArgumentException(String.format(INVALID_COMMAND,commandTypeAsString));
        }
    }

}

