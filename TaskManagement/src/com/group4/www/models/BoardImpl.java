package com.group4.www.models;

import com.group4.www.models.contracts.Board;
import com.group4.www.models.contracts.EventLog;
import com.group4.www.models.contracts.Task;
import com.group4.www.models.utils.ValidationHelpers;


import java.util.ArrayList;
import java.util.List;

public class BoardImpl implements Board {
    public static final int MIN_BOARD_NAME = 5;
    public static final int MAX_BOARD_NAME = 15;
    public static final String NAME_LENGTH_ERROR = String.format("Board name must be between %d and %d symbols.", MAX_BOARD_NAME, MAX_BOARD_NAME);
    private String name;
    private  List<Task> tasks;
    private final List<EventLog> history = new ArrayList<>();

    public BoardImpl(String name, List<Task> tasks) {
        setName(name);
        this.tasks = tasks;
    }

    public void setName(String name) {
        ValidationHelpers.validateIntRange(name.length(), MIN_BOARD_NAME, MAX_BOARD_NAME, NAME_LENGTH_ERROR);
        this.name = name;
    }
}
