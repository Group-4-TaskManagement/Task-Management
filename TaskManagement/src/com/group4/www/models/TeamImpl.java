package com.group4.www.models;

import com.group4.www.models.contracts.Board;
import com.group4.www.models.contracts.Member;
import com.group4.www.models.contracts.Team;
import com.group4.www.models.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

public class TeamImpl implements Team {
    public static final int TEAM_NAME_MIN_LENGTH = 5;
    public static final int TEAM_NAME_MAX_LENGTH = 15;
    public static final String TEAM_NAME_LENGTH_ERROR =
            String.format("Team name must be between %d and %d symbols.", TEAM_NAME_MIN_LENGTH, TEAM_NAME_MAX_LENGTH);
    public static final String MEMBER_ALREADY_IN_TEAM_MSG = "Member %s, is already part of team %s.";
    public static final String BOARD_ALREADY_ASSIGNED_MSG = "Board %s is already assigned to team %s.";
    public static final String MEMBER_NOT_PART_OF_TEAM_ERROR = "Member %s is not a part of this team.";
    public static final String BOARD_NOT_ASSIGNED_IN_TEAM_ERROR = "There is no such board assigned to this team.";

    private String name;
    private final List<Member> members;
    private List<Board> boards;


    public TeamImpl(String name) {
        setName(name);
        this.members = new ArrayList<>();
        this.boards = new ArrayList<>();
    }

    private void setName(String name) {
        ValidationHelpers.validateIntRange(name.length(),
                TEAM_NAME_MIN_LENGTH,
                TEAM_NAME_MAX_LENGTH,
                TEAM_NAME_LENGTH_ERROR);
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Member> getMembers() {
        return new ArrayList<>(members);
    }

    @Override
    public List<Board> showBoards() {
        return new ArrayList<>(boards);
    }

    @Override
    public void addMember(Member member){
        if(members.contains(member)){
            throw new IllegalArgumentException
                    (String.format(MEMBER_ALREADY_IN_TEAM_MSG, member, this.name));
        }
        members.add(member);
    }
    @Override
    public void removeMember(Member member){
        if(!members.contains(member)){
            throw new IllegalArgumentException
                    (String.format(MEMBER_NOT_PART_OF_TEAM_ERROR, member));
        }
        members.remove(member);
    }

    @Override
    public void addBoard(Board board){
        if(boards.contains(board)){
            throw new IllegalArgumentException
                    (String.format(BOARD_ALREADY_ASSIGNED_MSG,board,this.name));
        }
        boards.add(board);
    }

    @Override
    public void removeBoard(Board board){
        if(!boards.contains(board)){
            throw  new IllegalArgumentException(BOARD_NOT_ASSIGNED_IN_TEAM_ERROR);
        }
        boards.remove(board);
    }

    @Override
    public List<Board> getBoards() {
        return new ArrayList<>(boards);
    }

    @Override
    public String getAsString() {
        return String.format("      %s\n",getName());
    }
}
