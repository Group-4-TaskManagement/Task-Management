package com.group4.www.core;

import com.group4.www.core.contacts.Repository;
import com.group4.www.models.BoardImpl;
import com.group4.www.models.contracts.*;
import com.group4.www.models.enums.Priority;
import com.group4.www.models.enums.SeverityBug;
import com.group4.www.models.enums.SizeStory;
import com.group4.www.models.enums.StatusStory;
import com.group4.www.models.tasks.FeedbackImpl;
import com.group4.www.models.tasks.StoryImpl;
import com.group4.www.models.tasks.contracts.Task;
import com.group4.www.models.contracts.Board;
import com.group4.www.models.tasks.contracts.Bug;
import com.group4.www.models.tasks.contracts.Feedback;
import com.group4.www.models.tasks.contracts.Story;

import java.util.ArrayList;
import java.util.List;

public class RepositoryImpl implements Repository {
    private static final String MEMBER_NOT_EXIST = "A member with named %s does not exist";
    private static final String BOARD_NOT_EXIST = "The board does not exist";
    private static final String TEAM_NOT_EXIST = "The team does not exist";
    private int Id;
    private List<Team> teams;
    private List<Member> members;
    private List<Board> boards;
    private List<Bug> bugs;
    private List<Story> stories;
    private List<Feedback> feedbacks;

    public RepositoryImpl(){ Id = 0;}

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
        Story story = new StoryImpl(++Id, title, description, assignee, priority, size, status);
        this.stories.add(story);
        //TODO Find for assignee in members
        return story;
    }

    @Override
    public Feedback createFeedback(String title, String description, Member assignee, int rating) {
        Feedback feedback = new FeedbackImpl(++Id, title, description, assignee, rating);
        //TODO Find for assignee in members
        this.feedbacks.add(feedback);
        return feedback;
    }

    @Override
    public Board createBoardInTeam(String name, String teamName) {
        Board board = new BoardImpl(name);
        this.boards.add(board);
        //TODO Find for teams to add
        return board;
    }

    @Override
    public List<Member> showAllMembers() {
        return new ArrayList<>(members);
    }

    @Override
    public List<EventLog> showPersonActivity(String memberName) {
        for (Member member : members) {
            if (member.getName().equals(memberName)) {
                return new ArrayList<>(member.getActivityHistory());
            }

        }
        throw new IllegalArgumentException(String.format(MEMBER_NOT_EXIST, memberName));
    }

    @Override
    public List<Team> showAllTeams() {
        return new ArrayList<>(teams);
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
        Bug bug = findBugByID(bugID);
        bug.revertStatus();

    }

    @Override
    public void advancePriority(int taskID) {
        for (Task task : tasks) {
            if (task.getId() == taskID) {
                if (task.getClass().isInstance(Story.class)) {
                    Story story = (Story) task;
                    story.advancePriority();
                } else {
                    Bug bug = (Bug) task;
                    bug.advancePriority();
                }
            }

        }

    }

    @Override
    public void revertPriority(int taskID) {
        for (Task task : tasks) {
            if (task.getId() == taskID) {
                if (task.getClass().isInstance(Story.class)) {
                    Story story = (Story) task;
                    story.revertPriority();
                } else {
                    Bug bug = (Bug) task;
                    bug.revertPriority();
                }
            }

        }

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

        Feedback feedback = findFeedbackByID(taskID);
        feedback.advanceStatus();

    }

    @Override
    public void revertFeedbackStatus(int taskID) {
        Feedback feedback = findFeedbackByID(taskID);

        feedback.revertStatusFeedback();

    }

    @Override
    public void changeFeedbackRating(int newRating, int taskID) {
        Feedback feedback = findFeedbackByID(taskID);
        feedback.changeRating(newRating);

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
        for (Bug bug : bugs) {
            if (bug.getId() == id)
                return bug;
        }
        throw new IllegalArgumentException(String.format("There is no bug with ID:%d", id));
    }

    @Override
    public Story findStoryByID(int id) {
        for (Story story : stories) {
            if (story.getId() == id)
                return story;
        }
        throw new IllegalArgumentException(String.format("There is no story with ID:%d", id));
    }

    @Override
    public Feedback findFeedbackByID(int id) {
        for (Feedback feedback : feedbacks) {
            if (feedback.getId() == id) {
                return feedback;
            }
        }
        throw new IllegalArgumentException(String.format("There is no feedback with ID:%d", id));
    }

    @Override
    public Member findMember(String memberName) {

        for (Member member : members) {
            if (member.getName().equals(memberName)) {
                return member;
            }

        }
        throw new IllegalArgumentException(String.format(MEMBER_NOT_EXIST, memberName));
    }

    @Override
    public Board findBoard(String boardName) {
        for (Board board : boards) {
            if (board.getName().equals(boardName)) {
                return board;
            }

        }
        throw new IllegalArgumentException(BOARD_NOT_EXIST);
    }

    @Override
    public Team findTeam(String teamName) {
        for (Team team : teams) {
            if (team.getName().equals(teamName)) {
                return team;
            }

        }
        throw new IllegalArgumentException(TEAM_NOT_EXIST);
    }
}
