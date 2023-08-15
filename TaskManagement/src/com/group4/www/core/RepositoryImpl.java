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
    public static final String COMMENT_ADDED_TO_TASK = "A comment with id %d has been added to the task with id %d";
    public static final String TASKS_HEADER = "TASKS";
    public static final String BUGS_HEADER = "BUGS";
    private static int Id;
    private  List<Team> teams = new ArrayList<>();
    private List<Member> members = new ArrayList<>();
    private List<Board> boards = new ArrayList<>();
    private List<Bug> bugs = new ArrayList<>();
    private List<Story> stories = new ArrayList<>();
    private List<Feedback> feedbacks = new ArrayList<>();

    public RepositoryImpl(){ Id = 0;}

    @Override
    public Comment createComment(String author, String message) {
        Comment comment = new CommentImpl(++Id,author,message);
        return comment;
    }

    @Override
    public Member createPerson(String name) {
        Member member = new MemberImpl(name);
        for(Member b : members){
            if(b.getName().equals(name)){
                throw new IllegalArgumentException("Member with this name, already exist.");
            }
        }
        this.members.add(member);
        return member;
    }

    @Override
    public Team createTeam(String name) {
        for(Team b : teams){
            if(b.getName().equals(name)){
                throw new IllegalArgumentException("Team with this name, already exist.");
            }
        }
        Team team=new TeamImpl(name);
        this.teams.add(team);
        return team;
    }

    @Override
    public Board createBoard(String name) {
        for(Board b : boards){
            if(b.getName().equals(name)){
                throw new IllegalArgumentException("Board with this name, already exist.");
            }
        }
        Board board = new BoardImpl(name);
        this.boards.add(board);
        return board;
    }

    @Override
    public Bug createBugInBoard(String title, String description,Priority priority, SeverityBug severity,String boardName) {
        Bug bug=new BugImpl(++Id,title,description,priority,severity);
        Board board = findBoard(boardName);
        board.addTask(bug);
        this.bugs.add(bug);
        return bug;
    }

    @Override
    public Story createStoryInBoard(String title, String description,Priority priority, SizeStory size, StatusStory status,String boardname) {
        Story story = new StoryImpl(++Id, title, description,priority, size, status);
        Board board = findBoard(boardname);
        board.addTask(story);
        this.stories.add(story);
        return story;
    }

    @Override
    public Feedback createFeedbackInBoard(String title, String description,int rating,String boardName) {
        Feedback feedback = new FeedbackImpl(++Id, title, description,rating);
        Board board = findBoard(boardName);
        board.addTask(feedback);
        this.feedbacks.add(feedback);
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
        return FormattingHelpers.showAll(members,"members");
    }

    @Override
    public String showPersonActivity(String memberName) {
        Member member = findMember(memberName);
        return FormattingHelpers.showAll(member.getActivityHistory(),"activity");
    }

    @Override
    public String showAllTeams() {
        return FormattingHelpers.showAll(teams,"teams");
    }

    @Override
    public String showTeamActivity(String teamName) {
       Team team = findTeam(teamName);
       return FormattingHelpers.showAll(team.getTeamActivity(),"activity");
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
        return FormattingHelpers.showAll(team.getMembers(),"members");
    }

    @Override
    public String showAllTeamBoards(String teamName) {
        Team team=findTeam(teamName);
        return FormattingHelpers.showAll(team.getBoards(),"boards");
    }

    @Override
    public String showBoardActivity(String boardName) {
        Board board = findBoard(boardName);
        return FormattingHelpers.showAll(board.getHistory(),"activity");
    }

    @Override
    public void changeFeedbackRating(int newRating, int taskID) {
        Feedback feedback = findFeedbackByID(taskID);
        feedback.changeRating(newRating);

    }

    @Override
    public String assignTaskToMember(int taskID, String memberName) {
        Member member=findMember(memberName);
        for (Bug b:bugs) {
            if(b.getId()==taskID){
                member.addTask(b);
                b.addAssignee(member);
            }
        }
        for (Feedback b:feedbacks) {
            if(b.getId()==taskID){
                member.addTask(b);
                b.addAssignee(member);
            }
        }
        for (Story b:stories) {
            if(b.getId()==taskID){
                member.addTask(b);
                b.addAssignee(member);
            }
        }

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

        boolean addComment = false;
        for (Bug b:bugs) {
            if(b.getId()==taskID){
                b.addComment(comment);
                addComment = true;
            }
        }
        for (Feedback b:feedbacks) {
            if(b.getId()==taskID){
                b.addComment(comment);
                addComment = true;
            }
        }
        for (Story b:stories) {
            if(b.getId()==taskID){
                b.addComment(comment);
                addComment = true;
            }
        }

        if(!addComment){
            throw new IllegalArgumentException(String.format("There is no task with ID:%d", taskID));
        }
        return String.format(COMMENT_ADDED_TO_TASK,comment.getId(),taskID);

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

    @Override
    public String filterTasksByTitle(List<Task> tasks) {
        return FormattingHelpers.listingFormatted(tasks, TASKS_HEADER);
    }

    @Override
    public String sortTasksByTitle(List<Task> tasks) {
        return FormattingHelpers.listingFormatted(tasks, TASKS_HEADER);
    }

    @Override
    public String filterTaskByStatus(List<Task> tasks) {
        return FormattingHelpers.listingFormatted(tasks, TASKS_HEADER);
    }

    @Override
    public String filterTaskByAssignee(List<Task> tasks) {
        return FormattingHelpers.listingFormatted(tasks, TASKS_HEADER);
    }

    @Override
    public String filterTaskByStatusAndAssignee(List<Task> tasks) {
        return FormattingHelpers.listingFormatted(tasks, TASKS_HEADER);
    }

    @Override
    public String sortAssignedTaskByTitle(List<Task> tasks) {
        return FormattingHelpers.listingFormatted(tasks, TASKS_HEADER);
    }

    @Override
    public String filterBugsByStatus(List<Bug> bugs) {
        return FormattingHelpers.listingFormatted(bugs, BUGS_HEADER);
    }

    @Override
    public String filterBugsByAssignee(List<Bug> bugs) {
        return FormattingHelpers.listingFormatted(bugs, BUGS_HEADER);
    }

    @Override
    public String filterBugsByStatusAndAssignee(List<Bug> bugs) {
        return FormattingHelpers.listingFormatted(bugs, BUGS_HEADER);
    }

    @Override
    public String sortBugsByTitle(List<Bug> bugs) {
        return FormattingHelpers.listingFormatted(bugs, BUGS_HEADER);
    }

    @Override
    public String sortBugsByPriority(List<Bug> bugs) {
        return FormattingHelpers.listingFormatted(bugs, BUGS_HEADER);
    }

    @Override
    public String sortBugsBySeverity(List<Bug> bugs) {
        return FormattingHelpers.listingFormatted(bugs, BUGS_HEADER);
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
    public String changeFeedbackStatus(String command, int taskID) {
        Feedback feedback = findFeedbackByID(taskID);
        switch (command) {
            case "NEW":
                feedback.setStatusFeedback(StatusFeedback.NEW);
                break;
            case "UNSCHEDULED":
                feedback.setStatusFeedback(StatusFeedback.UNSCHEDULED);
                break;
            case "SCHEDULED":
                feedback.setStatusFeedback(StatusFeedback.SCHEDULED);
                break;
            case "DONE":
                feedback.setStatusFeedback(StatusFeedback.DONE);
                break;
            case "Advance":
                feedback.advanceStatus();
                break;
            case "Revert":
                feedback.revertStatusFeedback();
                break;
            default:
                throw new IllegalArgumentException("Invalid command or status name!");
        }
        return String.format(
                "Feedback status changed to %s!\n",feedback.getStatus());
    }


}
