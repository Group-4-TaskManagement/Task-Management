package com.group4.www.models;

import com.group4.www.models.contracts.Board;
import com.group4.www.models.contracts.Members;
import com.group4.www.models.contracts.Teams;
import com.group4.www.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

public class TeamsImpl implements Teams {
    public static final int TEAM_NAME_MIN_LENGTH = 5;
    public static final int TEAM_NAME_MAX_LENGTH = 15;
    public static final String TEAM_NAME_LENGTH_ERROR =
            String.format("Team name must be between %d and %d symbols.", TEAM_NAME_MIN_LENGTH, TEAM_NAME_MAX_LENGTH);
    public static final String MEMBER_ALREADY_IN_TEAM_MSG = "Member %s, is already part of team %s.";
    public static final String BOARD_ALREADY_ASSIGNED_MSG = "Board %s is already assigned to team %s.";
    public static final String MEMBER_NOT_PART_OF_TEAM_ERROR = "Member %s is not a part of this team.";
    public static final String BOARD_NOT_ASSIGNED_IN_TEAM_ERROR = "There is no such board assigned to this team.";

    private String name;
    private final List<Members> members;
    private final List<Board> boards;

    public TeamsImpl(String name) {
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
    public List<Members> getMembers() {
        return new ArrayList<>(members);
    }

    @Override
    public List<Board> showBoards() {
        return new ArrayList<>(boards);
    }

    public void addMember(Members member){
        if(members.contains(member)){
            throw new IllegalArgumentException
                    (String.format(MEMBER_ALREADY_IN_TEAM_MSG, member, this.name));
        }
        members.add(member);
    }

    public void removeMember(Members member){
        if(!members.contains(member)){
            throw new IllegalArgumentException
                    (String.format(MEMBER_NOT_PART_OF_TEAM_ERROR, member));
        }
        members.remove(member);
    }

    public void addBoard(Board board){
        if(boards.contains(board)){
            throw new IllegalArgumentException
                    (String.format(BOARD_ALREADY_ASSIGNED_MSG,board,this.name));
        }
        boards.add(board);
    }

    public void removeBoard(Board board){
        if(!boards.contains(board)){
            throw  new IllegalArgumentException(BOARD_NOT_ASSIGNED_IN_TEAM_ERROR);
        }
        boards.remove(board);
    }
}
