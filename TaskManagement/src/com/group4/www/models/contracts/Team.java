package com.group4.www.models.contracts;

import java.util.List;

public interface Team extends Printable{
    public String getName();

    public List<Member> getMembers();

    public List<Board> showBoards();

    void addMember(Member member);

    void removeMember(Member member);
    void addBoard(Board board);
    void removeBoard(Board board);

    List<Board> getBoards();

    List<EventLog> getTeamActivity();
    void addActivityHistory(String massage);
}
