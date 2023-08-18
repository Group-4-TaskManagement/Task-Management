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
import com.group4.www.models.tasks.contracts.Bug;
import com.group4.www.models.tasks.contracts.Feedback;
import com.group4.www.models.tasks.contracts.Story;
import com.group4.www.models.tasks.contracts.Task;
import com.group4.www.models.utils.FormattingHelpers;

import java.util.ArrayList;
import java.util.List;

public class RepositoryImpl implements Repository {
    private static final String MEMBER_NOT_EXIST = "A member with name %s does not exist";
    private static final String BOARD_NOT_EXIST = "This board does not exist";
    private static final String TEAM_NOT_EXIST = "This team does not exist";
    public static final String TASK_ADDED_TO_MEMBER = "Task with ID:%d has been added to %s's task list";
    public static final String TASK_REMOVED_TO_MEMBER = "Task with ID:%d has been removed from %s's task list";
    public static final String COMMENT_ADDED_TO_TASK = "A comment has been added to the task with ID:%d";
    public static final String TASKS_HEADER = "TASKS";
    public static final String BUGS_HEADER = "BUGS";
    public static final String FEEDBACK_NOT_EXIST = "There is no feedback with ID:%d";
    public static final String FIND_BUG_BY_ID_ERROR = "There is no bug with ID:%d";
    public static final String BUG_NOT_EXIST = FIND_BUG_BY_ID_ERROR;
    public static final String FIND_STORY_BY_ID_ERROR = "There is no story with ID:%d";
    public static final String STORY_NOT_EXIST = FIND_STORY_BY_ID_ERROR;
    public static final String TASK_NOT_EXIST = "There is no task with ID:%d";
    public static final String STORIES_HEADER = "STORIES";
    public static final String FEEDBACKS_HEADER = "FEEDBACKS";
    public static final String TEAMS_HEADER = "teams";
    public static final String ACTIVITY_HEADER = "activity";
    public static final String MEMBERS_HEADER = "members";
    public static final String BOARDS_HEADER = "boards";
    public static final String BOARD_EXISTS = "Board with this name, already exist.";
    public static final String TEAM_EXISTS = "Team with this name, already exist.";
    private static int Id;
    private final List<Team> teams = new ArrayList<>();
    private final List<Member> members = new ArrayList<>();
    private final List<Board> boards = new ArrayList<>();
    private final List<Bug> bugs = new ArrayList<>();
    private final List<Story> stories = new ArrayList<>();
    private final List<Feedback> feedbacks = new ArrayList<>();
    private final List<Task> tasks = new ArrayList<>();

    public RepositoryImpl(){ Id = 0;}

    @Override
    public Comment createComment(String author, String message) {
        Comment comment = new CommentImpl(author,message);
        return comment;
    }

    @Override
    public Member createPerson(String name) {
        members.stream().forEach((member) -> {
            if (member.getName().equals(name)) {
                throw new IllegalArgumentException(BOARD_EXISTS);
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
        Team team=new TeamImpl(name);
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
    public Bug createBugInBoard(String title, String description,Priority priority, SeverityBug severity,List<String> steps, String boardName) {
        Bug bug=new BugImpl(++Id,title,description,priority,severity, steps);
        Board board = findBoard(boardName);
        board.addTask(bug);
        this.bugs.add(bug);
        this.tasks.add(bug);
        return bug;
    }

    @Override
    public Story createStoryInBoard(String title, String description,Priority priority, SizeStory size, StatusStory status,String boardname) {
        Story story = new StoryImpl(++Id, title, description,priority, size, status);
        Board board = findBoard(boardname);
        board.addTask(story);
        this.stories.add(story);
        this.tasks.add(story);
        return story;
    }

    @Override
    public Feedback createFeedbackInBoard(String title, String description,int rating,String boardName) {
        Feedback feedback = new FeedbackImpl(++Id, title, description,rating);
        Board board = findBoard(boardName);
        board.addTask(feedback);
        this.feedbacks.add(feedback);
        this.tasks.add(feedback);
        return feedback;
    }

    @Override
    public Board createBoardInTeam(String name, String teamName) {
        Board board = createBoard(name);
        Team team = findTeam(teamName);
        team.addBoard(board);
        return board;
    }

    @Override
    public String showAllMembers() {
        return FormattingHelpers.showAll(members, MEMBERS_HEADER);
    }

    @Override
    public String showPersonActivity(String memberName) {
        Member member = findMember(memberName);
        return FormattingHelpers.showAll(member.getMemberActivity(), ACTIVITY_HEADER);
    }

    @Override
    public String showAllTeams() {
        return FormattingHelpers.showAll(teams, TEAMS_HEADER);
    }

    @Override
    public String showTeamActivity(String teamName) {
       Team team = findTeam(teamName);
       return FormattingHelpers.showAll(team.getTeamActivity(),ACTIVITY_HEADER);
    }


    @Override
    public void addMemberToTeam(String personName, String teamName) {
        Member member=findMember(personName);
        Team team=findTeam(teamName);
        team.addMember(member);
    }

    @Override
    public String showAllTeamMembers(String teamName) {
        Team team=findTeam(teamName);
        return FormattingHelpers.showAll(team.getMembers(),MEMBERS_HEADER);
    }

    @Override
    public String showAllTeamBoards(String teamName) {
        Team team=findTeam(teamName);
        return FormattingHelpers.showAll(team.getBoards(), BOARDS_HEADER);
    }

    @Override
    public String showBoardActivity(String boardName) {
        Board board = findBoard(boardName);
        return FormattingHelpers.showAll(board.getBoardActivity(),ACTIVITY_HEADER);
    }

    @Override
    public String showTaskActivity(int id) {
        Task task = findTaskByID(id);
        return FormattingHelpers.showAll(task.getTaskActivity(),ACTIVITY_HEADER);
    }

    @Override
    public void changeFeedbackRating(int newRating, int taskID) {
        Feedback feedback = findFeedbackByID(taskID);
        feedback.changeRating(newRating);

    }

    @Override
    public String assignTaskToMember(int taskID, String memberName) {
        Member member = findMember(memberName);
        Task task = findTaskByID(taskID);
        member.addTask(task);
        return String.format(TASK_ADDED_TO_MEMBER, taskID,memberName);

    }

    @Override
    public String unAssignTaskToMember(int taskID, String memberName) {
        Member member=findMember(memberName);
       member.removeTask(taskID);

       return String.format(TASK_REMOVED_TO_MEMBER,taskID,memberName);
    }

    @Override
    public String addCommentToTask(Comment comment, int taskID) {
        Task task = findTaskByID(taskID);
        task.addComment(comment);
        return String.format(COMMENT_ADDED_TO_TASK,taskID);

    }

    @Override
    public void changeBugStatus(int id, StatusBug statusBug) {
        Bug bug = findBugByID(id);
        bug.setStatus(statusBug);
    }

    @Override
    public void changeBugPriority(int id, Priority priorityBug) {
        Bug bug = findBugByID(id);
        bug.setPriority(priorityBug);
    }

    @Override
    public void changeBugSeverity(int id, SeverityBug severityBug) {
        Bug bug = findBugByID(id);
        bug.setSeverity(severityBug);
    }

    @Override
    public void changeStoryStatus(int id, StatusStory statusStory) {
        Story story = findStoryByID(id);
        story.setStatus(statusStory);
    }

    @Override
    public void changeStoryPriority(int id, Priority priorityStory) {
        Story story = findStoryByID(id);
        story.setPriority(priorityStory);
    }

    @Override
    public void changeStorySize(int id, SizeStory sizeStory) {
        Story story = findStoryByID(id);
        story.setSize(sizeStory);
    }


    @Override
    public Bug findBugByID(int id) {
        return bugs.stream()
                .filter(bug -> bug.getId()==id)
                .findAny().orElseThrow(()-> new IllegalArgumentException(String.format(BUG_NOT_EXIST, id)));
    }

    @Override
    public Story findStoryByID(int id) {
       return stories.stream()
               .filter(story -> story.getId()== story.getId())
               .findAny().orElseThrow(()-> new IllegalArgumentException(String.format(STORY_NOT_EXIST, id)));
    }

    @Override
    public Feedback findFeedbackByID(int id) {
        return feedbacks.stream()
                .filter(feedback -> feedback.getId()==id)
                .findAny().orElseThrow(()->new IllegalArgumentException(String.format(FEEDBACK_NOT_EXIST, id)));
    }

    @Override
    public Task findTaskByID(int id) {
        return tasks.stream()
                .filter(task -> task.getId()==id)
                .findAny().orElseThrow(() -> new IllegalArgumentException(String.format(TASK_NOT_EXIST, id)));
    }

    @Override
    public Member findMember(String memberName) {
        return members.stream()
                .filter(member->member.getName().equals(memberName))
                .findAny().orElseThrow(()-> new IllegalArgumentException(String.format(MEMBER_NOT_EXIST,memberName)));
    }

    @Override
    public Board findBoard(String boardName) {
        return boards.stream()
                .filter(board->board.getName().equals(boardName))
                .findAny().orElseThrow(()-> new IllegalArgumentException(BOARD_NOT_EXIST));
    }

    @Override
    public Team findTeam(String teamName) {
        return teams.stream()
                .filter(team -> team.getName().equals(teamName))
                .findAny().orElseThrow(() -> new IllegalArgumentException(TEAM_NOT_EXIST));

    }

    @Override
    public String listTasksByGivenCondition(List<Task> tasks) {
        return FormattingHelpers.listingFormatted(tasks, TASKS_HEADER);
    }

    @Override
    public String listBugsByGivenCondition(List<Bug> bugs) {
        return FormattingHelpers.listingFormatted(bugs, BUGS_HEADER);
    }

    @Override
    public String listStoriesByGivenCondition(List<Story> stories) {
        return FormattingHelpers.listingFormatted(stories, STORIES_HEADER);
    }
    @Override
    public String listFeedbackByGivenCondition(List<Feedback> feedbacks){
        return FormattingHelpers.listingFormatted(feedbacks, FEEDBACKS_HEADER);
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
    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    @Override
    public List<Feedback> getFeedbacks() {
        return new ArrayList<>(feedbacks);
    }

    @Override
    public void changeFeedbackStatus(int id,String command ) {
        Feedback feedback = findFeedbackByID(id);
        feedback.changeFeedbackStatus(feedback.getStatus(),command);
    }


}
