package com.group4.www.models.contracts;

import java.util.List;

public interface Team extends Printable{
    String getName();

    List<Member> getMembers();

    void addMember(Member member);

    void removeMember(Member member);

    void addBoard(Board board);

    void removeBoard(Board board);

    List<Board> getBoards();

    List<EventLog> getTeamActivity();

    void addActivityHistory(String massage);
}
