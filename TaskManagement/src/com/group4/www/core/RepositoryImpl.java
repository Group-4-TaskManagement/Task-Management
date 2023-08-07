package com.group4.www.core;

import com.group4.www.core.contacts.Repository;
import com.group4.www.models.contracts.*;
import com.group4.www.models.enums.Priority;
import com.group4.www.models.enums.SeverityBug;
import com.group4.www.models.enums.SizeStory;
import com.group4.www.models.enums.StatusStory;
import com.group4.www.models.tasks.contracts.Task;
import com.group4.www.models.contracts.Board;
import com.group4.www.models.tasks.contracts.Bug;
import com.group4.www.models.tasks.contracts.Feedback;
import com.group4.www.models.tasks.contracts.Story;

import java.util.List;

public class RepositoryImpl implements Repository {
    private List<Team> teams;
    private List<Member> members;
    private List<Board> boards;
    private List<Task> tasks;

    @Override
    public Member createPerson(String name) {
        return null;
    }

    @Override
    public Team createTeam(String name) {
        return null;
    }

    @Override
    public Bug createBug(String title, String description, Priority priority, SeverityBug severity, Member assignee) {
        return null;
    }

    @Override
    public Story createStory(String title, String description, Member assignee, Priority priority, SizeStory size, StatusStory status) {
        return null;
    }

    @Override
    public Feedback createFeedback(String title, String description, Member assignee, int rating) {
        return null;
    }

    @Override
    public Board createBoardInTeam(String name, String teamName) {
        return null;
    }

    @Override
    public List<Member> showAllMembers() {
        return null;
    }

    @Override
    public List<EventLog> showPersonActivity(String memberName) {
        return null;
    }

    @Override
    public List<Team> showAllTeams() {
        return null;
    }

    @Override
    public List<EventLog> showTeamActivity(String teamName) {
        return null;
    }

    @Override
    public void addMemberToTeam(String personName, String teamName) {

    }

    @Override
    public List<Member> showAllTeamMembers(String teamName) {
        return null;
    }

    @Override
    public List<Board> showAllTeamBoards(String teamName) {
        return null;
    }

    @Override
    public List<EventLog> showBoardActivity(String boardName) {
        return null;
    }

    @Override
    public void advanceBugStatus(int bugID) {

    }

    @Override
    public void revertBugStatus(int bugID) {

    }

    @Override
    public void advancePriority(int taskID) {

    }

    @Override
    public void revertPriority(int taskID) {

    }

    @Override
    public void advanceBugSeverity(int bugID) {

    }

    @Override
    public void revertBugSeverity(int bugID) {

    }

    @Override
    public void advanceStoryStatus(int storyID) {

    }

    @Override
    public void revertStoryStatus(int storyID) {

    }

    @Override
    public void advanceStorySize(int storyID) {

    }

    @Override
    public void revertStorySize(int storyID) {

    }

    @Override
    public void advanceFeedbackStatus(int taskID) {

    }

    @Override
    public void revertFeedbackStatus(int feedbackID) {

    }

    @Override
    public void changeFeedbackRating(int newRating, int taskID) {

    }

    @Override
    public void assignTaskToMember(int taskID, String memberName) {

    }

    @Override
    public void unAssignTaskToMember(int taskID, String memberName) {

    }

    @Override
    public void addCommentToTask(Comment comment, int taskID) {

    }

    @Override
    public Bug findBugByID(int id) {
        return null;
    }

    @Override
    public Story findStoryByID(int id) {
        return null;
    }

    @Override
    public Feedback findFeedbackByID(int id) {
        return null;
    }

    @Override
    public Member findMember(String memberName) {
        return null;
    }

    @Override
    public Board findBoard(String boardName) {
        return null;
    }

    @Override
    public Team findTeam(String teamName) {
        return null;
    }
}
