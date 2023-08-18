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

    Comment createComment(String author, String message);

    Member createPerson(String name);

    Team createTeam(String name);

    Board createBoard(String name);

    Bug createBugInBoard(String title, String description,Priority priority, SeverityBug severity,List<String> steps,String boardName);

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

    String showTaskActivity(int id);

    void changeFeedbackRating(int newRating, int taskID);

    String assignTaskToMember(int taskID, String memberName);

    String unAssignTaskToMember(int taskID, String memberName);

    String addCommentToTask(Comment comment, int taskID);


    void changeStatus(int id, String status);

    void changeBugPriority(int id, Priority priorityBug);

    void changeBugSeverity(int id, SeverityBug severityBug);

    void changeStoryStatus(int id, StatusStory statusStory);

    void changeStoryPriority(int id, Priority priorityStory);

    void changeStorySize(int id, SizeStory sizeStory);

    Bug findBugByID(int id);

    Story findStoryByID(int id);

    Feedback findFeedbackByID(int id);

    Task findTaskByID(int id);

    Member findMember(String memberName);

    Board findBoard(String boardName);

    Team findTeam(String teamName);

    String listTasksByGivenCondition(List<Task> tasks);

    String listBugsByGivenCondition(List<Bug> bugs);

    String listStoriesByGivenCondition(List<Story> stories);

    String listFeedbackByGivenCondition(List<Feedback> feedbacks);

    List<Bug> getBugs();

    List<Story> getStories();

    List<Task> getTasks();

    List<Feedback> getFeedbacks();



    //TODO SHow history of given task;
}
