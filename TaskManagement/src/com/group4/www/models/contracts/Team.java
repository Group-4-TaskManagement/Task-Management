package com.group4.www.models.contracts;

import java.util.List;

public interface Team {
    public String getName();

    public List<Member> getMembers();

    public List<Board> showBoards();

    void addMember(Member member);

    void removeMember(Member member);
    void addBoard(Board board);
    void removeBoard(Board board);

}
