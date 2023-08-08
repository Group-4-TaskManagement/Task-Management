package com.group4.www.core.contacts;

import com.group4.www.models.contracts.*;
import com.group4.www.models.enums.Priority;
import com.group4.www.models.enums.SeverityBug;
import com.group4.www.models.enums.SizeStory;
import com.group4.www.models.enums.StatusStory;
import com.group4.www.models.contracts.Board;
import com.group4.www.models.tasks.contracts.Bug;
import com.group4.www.models.tasks.contracts.Feedback;
import com.group4.www.models.tasks.contracts.Story;
import com.group4.www.models.tasks.contracts.Task;

import java.util.List;

public interface Repository {
    //MARIAN
    Member createPerson(String name);

    Team createTeam(String name);

    Bug createBug(String title, String description, Priority priority, SeverityBug severity, Member assignee);
//Stoyan
    Story createStory(String title, String description, Member assignee, Priority priority, SizeStory size, StatusStory status);

    Feedback createFeedback(String title, String description, Member assignee, int rating);

    Board createBoardInTeam(String name, String teamName);
//MONIKA
    List<Member> showAllMembers();

    List<EventLog> showPersonActivity(String memberName);

    List<Team> showAllTeams();
//MARIAN
    List<EventLog> showTeamActivity(String teamName);

    void addMemberToTeam(String personName, String teamName);

    List<Member> showAllTeamMembers(String teamName);
//STOYAN
    List<Board> showAllTeamBoards(String teamName);

    List<EventLog> showBoardActivity(String boardName);

    void advanceBugStatus(int bugID);
//MONIKA
    void revertBugStatus(int bugID);

    void advancePriority(int taskID);

    void revertPriority(int taskID);
//MARIAN
    void advanceBugSeverity(int bugID);

    void revertBugSeverity(int bugID);

    void advanceStoryStatus(int storyID);
//STOYAN
    void revertStoryStatus(int storyID);

    void advanceStorySize(int storyID);

    void revertStorySize(int storyID);
//MONIKA
    void advanceFeedbackStatus(int taskID);

    void revertFeedbackStatus(int feedbackID);

    void changeFeedbackRating(int newRating, int taskID);
//MARIAN
    void assignTaskToMember(int taskID, String memberName);

    void unAssignTaskToMember(int taskID, String memberName);

    void addCommentToTask(Comment comment, int taskID);
//STOYAN
    Bug findBugByID(int id);

    Story findStoryByID(int id);

    Feedback findFeedbackByID(int id);
//MONIKA
    Member findMember(String memberName);

    Board findBoard(String boardName);

    Team findTeam(String teamName);



}
