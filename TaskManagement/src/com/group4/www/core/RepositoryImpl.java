package com.group4.www.core;

import com.group4.www.core.contacts.Repository;
import com.group4.www.models.BoardImpl;
import com.group4.www.models.CommentImpl;
import com.group4.www.models.MemberImpl;
import com.group4.www.models.TeamImpl;
import com.group4.www.models.contracts.*;
import com.group4.www.models.enums.Priority;
import com.group4.www.models.enums.SeverityBug;
import com.group4.www.models.enums.SizeStory;
import com.group4.www.models.enums.StatusStory;
import com.group4.www.models.tasks.BugImpl;
import com.group4.www.models.tasks.FeedbackImpl;
import com.group4.www.models.tasks.StoryImpl;
import com.group4.www.models.tasks.contracts.Bug;
import com.group4.www.models.tasks.contracts.Feedback;
import com.group4.www.models.tasks.contracts.Story;

import java.util.ArrayList;
import java.util.List;

public class RepositoryImpl implements Repository {
    private static final String MEMBER_NOT_EXIST = "A member with name %s does not exist";
    private static final String BOARD_NOT_EXIST = "The board does not exist";
    private static final String TEAM_NOT_EXIST = "The team does not exist";
    public static final String TASK_ADDED_TO_MEMBER = "Task with id %d has been added to %s task list";
    public static final String TASK_REMOVED_TO_MEMBER = "Task with id %d has been removed from %s's task list";
    public static final String COMMENT_ADDED_TO_TASK = "A comment with id %d has been added to the task with id %d";
    private int Id;
    private List<Team> teams = new ArrayList<>();
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
    public Bug createBug(String title, String description,Member assignee, Priority priority, SeverityBug severity) {
        Bug bug=new BugImpl(++Id,title,description,assignee,priority,severity);
        this.bugs.add(bug);
        return bug;
    }

    @Override
    public Story createStory(String title, String description, Member assignee, Priority priority, SizeStory size, StatusStory status) {
        Story story = new StoryImpl(++Id, title, description, assignee, priority, size, status);
        this.stories.add(story);
        return story;
    }

    @Override
    public Feedback createFeedback(String title, String description, Member assignee, int rating) {
        Feedback feedback = new FeedbackImpl(++Id, title, description, assignee, rating);
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
        StringBuilder builder = new StringBuilder();
        builder.append("------MEMBERS------\n");
        builder.append(showAll(members,"members"));
        builder.append("-------------------");
        return builder.toString().trim();
    }

    @Override
    public String showPersonActivity(String memberName) {
        Member member = findMember(memberName);
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("------%s------\n",member.getName()));
        builder.append(showAll(member.getActivityHistory(),"activity"));
        builder.append("------------------");
        return builder.toString();
    }

    @Override
    public String showAllTeams() {
        StringBuilder builder = new StringBuilder();
        builder.append("------TEAMS------\n");
        builder.append(showAll(teams,"teams"));
        builder.append("-------------------");
        return builder.toString().trim();
    }

    @Override
    public String showTeamActivity(String teamName) {
       Team team = findTeam(teamName);
       StringBuilder builder = new StringBuilder();
       builder.append(String.format("------%s------\n",team.getName()));
       builder.append(showAll(team.getTeamActivity(),"activity"));
       builder.append("--------------------");
       return builder.toString();
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
        return team.getMembers().toString();
    }

    @Override
    public String showAllTeamBoards(String teamName) {
        Team team=findTeam(teamName);
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("----- Boards in %s -----\n",team.getName()));
        builder.append(showAll(team.getBoards(),"boards"));
        builder.append("------------------------");
        return builder.toString();
    }

    @Override
    public String showBoardActivity(String boardName) {
        Board board=findBoard(boardName);
        return board.getHistory().toString();
    }

    @Override
    public void advanceBugStatus(int bugID) {
        Bug bug = findBugByID(bugID);
        bug.advanceStatus();
    }

    @Override
    public void revertBugStatus(int bugID) {
        Bug bug = findBugByID(bugID);
        bug.revertStatus();

    }

    @Override
    public void advancePriorityStory(int taskID) {
        Story story = findStoryByID(taskID);
        story.advancePriority();

    }

    @Override
    public void advancePriorityBug(int taskID) {
        Bug bug = findBugByID(taskID);
        bug.advancePriority();

    }

    @Override
    public void revertPriorityStory(int taskID) {
        Story story = findStoryByID(taskID);
        story.revertPriority();

    }

    @Override
    public void revertPriorityBug(int taskID) {
        Bug bug = findBugByID(taskID);
        bug.revertPriority();

    }


    @Override
    public void advanceBugSeverity(int bugID) {
        Bug bug = findBugByID(bugID);
        bug.advanceSeverity();
    }

    @Override
    public void revertBugSeverity(int bugID) {
        Bug bug = findBugByID(bugID);
        bug.advanceSeverity();
    }

    @Override
    public void advanceStoryStatus(int storyID) {
        Story story = findStoryByID(storyID);
        story.advanceStatus();
    }

    @Override
    public void revertStoryStatus(int storyID) {
        Story story = findStoryByID(storyID);
        story.revertStatus();
    }

    @Override
    public void advanceStorySize(int storyID) {
        Story story = findStoryByID(storyID);
        story.advanceSize();
    }

    @Override
    public void revertStorySize(int storyID) {
        Story story = findStoryByID(storyID);
        story.revertSize();
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
    public String assignTaskToMember(int taskID, String memberName) {
        Member member=findMember(memberName);
        for (Bug b:bugs) {
            if(b.getId()==taskID){
                member.addTask(b);
            }
        }
        for (Feedback b:feedbacks) {
            if(b.getId()==taskID){
                member.addTask(b);
            }
        }
        for (Story b:stories) {
            if(b.getId()==taskID){
                member.addTask(b);
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
            throw new IllegalArgumentException(String.format("There is no таск with ID:%d", taskID));
        }
        return String.format(COMMENT_ADDED_TO_TASK,comment.getId(),taskID);

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

    public <T extends Printable> String showAll(List<T> elements,String typeName){
        StringBuilder builder = new StringBuilder();
        if(elements.size()==0){
            builder.append(String.format("There are no %s added yet.\n",typeName));
            return builder.toString();
        }
        for(T element: elements){
            builder.append(pad(element.getAsString(),19, ' '));
            builder.append("\n");
        }
        return builder.toString();
    }
    public static String pad(String source, int length, char paddingSymbol) {
        String[] input = source.split("");
        if (input.length >= length) return source;

        ArrayList<String> output = new ArrayList<>();
        for (String st :
                input) {
            output.add(st);
        }

        while (output.size() < length) {
            output.add(0, String.valueOf(paddingSymbol));
            output.add(String.valueOf(paddingSymbol));
        }

        String result = "";
        for (String st :
                output) {
            result += st;
        }

        return result;
    }
}
