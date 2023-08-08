package com.group4.www.core;

import com.group4.www.core.contacts.Repository;
import com.group4.www.models.BoardImpl;
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
import com.group4.www.models.tasks.contracts.Task;

import java.util.ArrayList;
import java.util.List;

public class RepositoryImpl implements Repository {
    private static final String MEMBER_NOT_EXIST = "A member with named %s does not exist";
    private static final String BOARD_NOT_EXIST = "The board does not exist";
    private static final String TEAM_NOT_EXIST = "The team does not exist";
    private int Id;
    private List<Team> teams = new ArrayList<>();
    private List<Member> members = new ArrayList<>();
    private List<Board> boards = new ArrayList<>();
    private List<Bug> bugs = new ArrayList<>();
    private List<Story> stories = new ArrayList<>();
    private List<Feedback> feedbacks = new ArrayList<>();

    public RepositoryImpl(){ Id = 0;}

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
        Board board = createBoard(name);
        Team team = findTeam(teamName);
        team.addBoard(board);
        return board;
    }

    @Override
    public String showAllMembers() {
        StringBuilder builder = new StringBuilder();
        for(Member member: members) {
            builder.append(member);
        }
        return builder.toString();
    }

    @Override
    public String showPersonActivity(String memberName) {
        for (Member member : members) {
            if (member.getName().equals(memberName)) {
                return member.getActivityHistory().toString();
            }

        }
        throw new IllegalArgumentException(String.format(MEMBER_NOT_EXIST, memberName));
    }

    @Override
    public String showAllTeams() {
        return new ArrayList<>(teams).toString();
    }

    @Override
    public String showTeamActivity(String teamName) {
        StringBuilder sb=new StringBuilder();
        Team team = findTeam(teamName);
                for (Member member : team.getMembers()) {
                     sb.append(member.getActivityHistory());
                }
                if(sb.isEmpty()){
                    throw new IllegalArgumentException(
                            "There is no history to be displayed in this team.");
                }
                return sb.toString();
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
        return team.showBoards().toString();
    }

    @Override
    public String showBoardActivity(String boardName) {
        Board board=findBoard(boardName);
        return board.getHistory().toString();
    }

    @Override
    public void advanceBugStatus(int bugID) {
        Bug bug=findBugByID(bugID);
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
    public void assignTaskToMember(int taskID, String memberName) {
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

    }

    @Override
    public void unAssignTaskToMember(int taskID, String memberName) {
        Member member=findMember(memberName);
       member.removeTask(taskID);
    }

    @Override
    public void addCommentToTask(Comment comment, int taskID) {
        for (Bug b:bugs) {
            if(b.getId()==taskID){
                b.addComment(comment);
            }
        }
        for (Feedback b:feedbacks) {
            if(b.getId()==taskID){
                b.addComment(comment);
            }
        }
        for (Story b:stories) {
            if(b.getId()==taskID){
                b.addComment(comment);
            }
        }

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
