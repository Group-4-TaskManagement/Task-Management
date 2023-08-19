package com.group4.www.core.contacts;

import com.group4.www.models.contracts.*;
import com.group4.www.models.enums.*;
import com.group4.www.models.contracts.Board;
import com.group4.www.models.tasks.contracts.*;

import java.util.List;
import java.util.function.Predicate;

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

    void assignTaskToMember(int taskID, String memberName);

    void unAssignTaskToMember(int taskID, String memberName);

    void addCommentToTask(Comment comment, int taskID);




    String changeBugSeverity(int id, SeverityBug severityBug);


    String changeStorySize(int id, SizeStory sizeStory);

    <T> T findElement(List<T> list, Predicate<T> condition, String message);

    String listAssignableTasksByGivenCondition(List<AssignableTask> assignableTasks);

    String listTasksByGivenCondition(List<Task> tasks);

    String listBugsByGivenCondition(List<Bug> bugs);

    String listStoriesByGivenCondition(List<Story> stories);

    String listFeedbackByGivenCondition(List<Feedback> feedbacks);

    List<Bug> getBugs();

    List<Story> getStories();

    List<Task> getTasks();

    List<AssignableTask> getAssignableTasks();

    List<Feedback> getFeedbacks();

    List<Member> getMembers();

}
