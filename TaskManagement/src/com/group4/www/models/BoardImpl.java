package com.group4.www.models;

import com.group4.www.models.contracts.Board;
import com.group4.www.models.contracts.EventLog;
import com.group4.www.models.tasks.contracts.Task;
import com.group4.www.models.utils.ValidationHelpers;


import java.util.ArrayList;
import java.util.List;

public class BoardImpl implements Board {
    public static final int MIN_BOARD_NAME = 5;
    public static final int MAX_BOARD_NAME = 15;
    public static final String NAME_LENGTH_ERROR = String.format("Board name must be between %d and %d symbols.", MAX_BOARD_NAME, MAX_BOARD_NAME);
    public static final String TASK_ADDED_TO_BOARD_MESS = "Task with ID:%d was added to board %s";
    public static final String TASK_REMOVED_FROM_BOARD_MESS = "Task with ID:%d was removed from board %s";
    private String name;
    private final List<Task> tasks;
    private final List<EventLog> history;

    public BoardImpl(String name) {
        setName(name);
        this.tasks = new ArrayList<>();
        this.history = new ArrayList<>();
    }

    public void setName(String name) {
        ValidationHelpers.validateIntRange(name.length(), MIN_BOARD_NAME, MAX_BOARD_NAME, NAME_LENGTH_ERROR);
        this.name = name;
    }
    @Override
    public void addTask(Task task) {
        if (tasks.contains(task)) {
            throw new IllegalArgumentException("Task already in the list");
        }
        logEvent(String.format(TASK_ADDED_TO_BOARD_MESS,task.getId(),getName()));
        tasks.add(task);
    }
    @Override
    public void removeTask(Task task) {
        if (!tasks.contains(task)) {
            throw new IllegalArgumentException("Task not in the list");
        }
        logEvent(String.format(TASK_REMOVED_FROM_BOARD_MESS,task.getId(),getName()));
        tasks.remove(task);
    }
    @Override
    public void logEvent(String event) {
        history.add(new EventLogImpl(event));
    }

    @Override
    public String getName() {
        return name;
    }
    @Override
    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }
    @Override
    public List<EventLog> getHistory() {
        return new ArrayList<>(history);
    }

    @Override
    public String getAsString() {
        return getName();
    }
}
