package com.group4.www.core.contacts;

import com.group4.www.models.contracts.*;
import com.group4.www.models.enums.*;
import com.group4.www.models.contracts.Board;
import com.group4.www.models.tasks.contracts.Bug;
import com.group4.www.models.tasks.contracts.Feedback;
import com.group4.www.models.tasks.contracts.Story;
import com.group4.www.models.tasks.contracts.Task;

import java.util.List;

public interface Repository {
//MARIAN

    Comment createComment(String author, String message);
    Member createPerson(String name);

    Team createTeam(String name);

    Board createBoard(String name);

    Bug createBugInBoard(String title, String description,Priority priority, SeverityBug severity,String boardName);

    Story createStoryInBoard(String title, String description,Priority priority, SizeStory size, StatusStory status,String boardName);

    Feedback createFeedbackInBoard(String title, String description,int rating,String boardName);

    Board createBoardInTeam(String name, String teamName);

    String showAllMembers();

    String showPersonActivity(String memberName);

    String showAllTeams();

    String showTeamActivity(String teamName);

    void addMemberToTeam(String personName, String teamName);

    String showAllTeamMembers(String teamName);

    String showAllTeamBoards(String teamName);

    String showBoardActivity(String boardName);

    void changeFeedbackRating(int newRating, int taskID);

    String assignTaskToMember(int taskID, String memberName);

    String unAssignTaskToMember(int taskID, String memberName);

    String addCommentToTask(Comment comment, int taskID);

    void changeBugStatus(int id, StatusBug statusBug);

    void changeBugPriority(int id, Priority priorityBug);

    void changeBugSeverity(int id, SeverityBug severityBug);
    Bug findBugByID(int id);

    Story findStoryByID(int id);

    Feedback findFeedbackByID(int id);

    Member findMember(String memberName);

    Board findBoard(String boardName);

    Team findTeam(String teamName);

    String filterTasksByTitle(List<Task> tasks);

    String sortTasksByTitle(List<Task> tasks);

    String filterTaskByStatus(List<Task> tasks);

    String filterTaskByAssignee(List<Task> tasks);

    String filterTaskByStatusAndAssignee(List<Task> tasks);

    List<Bug> getBugs();

    List<Story> getStories();

    List<Feedback> getFeedbacks();

}
