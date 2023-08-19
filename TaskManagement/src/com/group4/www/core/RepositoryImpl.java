package com.group4.www.core;

import com.group4.www.core.contacts.Repository;
import com.group4.www.models.BoardImpl;
import com.group4.www.models.CommentImpl;
import com.group4.www.models.MemberImpl;
import com.group4.www.models.TeamImpl;
import com.group4.www.models.contracts.*;
import com.group4.www.models.enums.*;
import com.group4.www.models.tasks.BugImpl;
import com.group4.www.models.tasks.FeedbackImpl;
import com.group4.www.models.tasks.StoryImpl;
import com.group4.www.models.tasks.contracts.*;
import com.group4.www.models.utils.FormattingHelpers;
import com.group4.www.models.utils.ListingHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RepositoryImpl implements Repository {
    private static final String MEMBER_NOT_EXIST = "A member with name %s does not exist";
    private static final String BOARD_NOT_EXIST = "This board does not exist";
    private static final String TEAM_NOT_EXIST = "This team does not exist";
    public static final String TASK_NOT_EXIST = "Task does not exist";
    public static final String BOARD_EXISTS = "Board with this name, already exist.";
    public static final String MEMBER_EXISTS = "Member with this name, already exist.";
    public static final String TEAM_EXISTS = "Team with this name, already exist.";

    private static int Id;
    private final List<Team> teams = new ArrayList<>();
    private final List<Member> members = new ArrayList<>();
    private final List<Board> boards = new ArrayList<>();
    private final List<Bug> bugs = new ArrayList<>();
    private final List<Story> stories = new ArrayList<>();
    private final List<Feedback> feedbacks = new ArrayList<>();

    public RepositoryImpl(){ Id = 0;}

    @Override
    public Comment createComment(String author, String message) {
        Comment comment = new CommentImpl(author, message);
        return comment;
    }

    @Override
    public Member createPerson(String name) {
        members.stream().forEach((member) -> {
            if (member.getName().equals(name)) {
                throw new IllegalArgumentException(MEMBER_EXISTS);
            }
        });
        Member member = new MemberImpl(name);
        this.members.add(member);
        return member;
    }

    @Override
    public Team createTeam(String name) {
        teams.stream().forEach((team) -> {
            if (team.getName().equals(name)) {
                throw new IllegalArgumentException(TEAM_EXISTS);
            }
        });
        Team team = new TeamImpl(name);
        this.teams.add(team);
        return team;
    }

    @Override
    public Board createBoard(String name) {
        boards.stream().forEach((board) -> {
            if (board.getName().equals(name)) {
                throw new IllegalArgumentException(BOARD_EXISTS);
            }
        });
        Board board = new BoardImpl(name);
        this.boards.add(board);
        return board;
    }

    @Override
    public Bug createBugInBoard(String title, String description, Priority priority, SeverityBug severity, List<String> steps, String boardName) {
        Bug bug = new BugImpl(++Id, title, description, priority, severity, steps);
        findElement(boards,(board -> board.getName().equals(boardName)),BOARD_NOT_EXIST).addTask(bug);
        this.bugs.add(bug);
        return bug;
    }

    @Override
    public Story createStoryInBoard(String title, String description, Priority priority, SizeStory size, StatusStory status, String boardname) {
        Story story = new StoryImpl(++Id, title, description, priority, size, status);
        findElement(boards,(board -> board.getName().equals(boardname)),BOARD_NOT_EXIST).addTask(story);
        this.stories.add(story);
        return story;
    }

    @Override
    public Feedback createFeedbackInBoard(String title, String description, int rating, String boardName) {
        Feedback feedback = new FeedbackImpl(++Id, title, description, rating);
        findElement(boards,(board -> board.getName().equals(boardName)),BOARD_NOT_EXIST).addTask(feedback);
        this.feedbacks.add(feedback);
        return feedback;
    }

    @Override
    public Board createBoardInTeam(String name, String teamName) {
        Board board = createBoard(name);
        findElement(teams,team -> team.getName().equals(teamName),TEAM_NOT_EXIST).addBoard(board);
        return board;
    }




    @Override
    public void addMemberToTeam(String personName, String teamName) {
        findElement(teams,team -> team.getName().equals(teamName),TEAM_NOT_EXIST)
                .addMember(findElement(members,member -> member.getName().equals(personName), MEMBER_NOT_EXIST));
    }



    @Override
    public void changeFeedbackRating(int newRating, int taskID) {
        findElement(getFeedbacks(),(feedback -> feedback.getId()==taskID),TASK_NOT_EXIST).changeRating(newRating);
    }

    @Override
    public void assignTaskToMember(int taskID, String memberName) {
        Member member = findElement(members,(member1 -> member1.getName().equals(memberName)),MEMBER_NOT_EXIST);
        AssignableTask task = findElement(getAssignableTasks(),task1 -> task1.getId()==taskID, TASK_NOT_EXIST);
        member.addTask(task);
        task.addAssignee(member);
    }

    @Override
    public void unAssignTaskToMember(int taskID, String memberName) {
        findElement(members,(member -> member.getName().equals(memberName)),MEMBER_NOT_EXIST)
                .removeTask(getTasks().stream().filter(task -> task.getId()==taskID).findFirst().get());
    }

    @Override
    public void addCommentToTask(Comment comment, int taskID) {
        findElement(getTasks(),(task -> task.getId()==taskID),TASK_NOT_EXIST)
                .addComment(comment);
    }






    @Override
    public String changeBugSeverity(int id, SeverityBug severityBug) {
      return   findElement(bugs,(bug -> bug.getId()==id), TASK_NOT_EXIST)
                .setSeverity(severityBug);
    }



    @Override
    public String changeStorySize(int id, SizeStory sizeStory) {
      return   findElement(stories,(story -> story.getId()==id), TASK_NOT_EXIST)
                .changeSize(sizeStory);
    }

    @Override
    public <T> T findElement(List<T> list, Predicate<T> condition, String message){
        return list.stream().filter(condition).findAny().orElseThrow(() -> new IllegalArgumentException(message));
    }




    @Override
    public List<Bug> getBugs() {
        return new ArrayList<>(bugs);
    }

    @Override
    public List<Story> getStories() {
        return new ArrayList<>(stories);
    }

    @Override
    public List<Feedback> getFeedbacks() {
        return new ArrayList<>(feedbacks);
    }

    @Override
    public List<Task> getTasks() {
        List<Task> tasks = Stream.of(bugs, stories).flatMap(task -> task.stream()).collect(Collectors.toList());
        return Stream.of(tasks, feedbacks).flatMap(task -> task.stream()).collect(Collectors.toList());
    }
    @Override
    public List<AssignableTask> getAssignableTasks(){
        return Stream.of(bugs,stories).flatMap(assignableTasks -> assignableTasks.stream()).collect(Collectors.toList());
    }

    @Override
    public List<Member> getMembers() {return new ArrayList<>(members);}

    public List<Team> getTeams() {
        return teams;
    }

    public List<Board> getBoards() {
        return boards;
    }
}
