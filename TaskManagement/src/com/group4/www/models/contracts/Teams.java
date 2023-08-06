package com.group4.www.models.contracts;

import java.util.List;

public interface Teams {
    public String getName();

    public List<Members> getMembers();

    public List<Board> showBoards();

}
