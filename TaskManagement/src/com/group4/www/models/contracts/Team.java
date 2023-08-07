package com.group4.www.models.contracts;

import java.util.List;

public interface Team {
    public String getName();

    public List<Member> getMembers();

    public List<Board> showBoards();

}
