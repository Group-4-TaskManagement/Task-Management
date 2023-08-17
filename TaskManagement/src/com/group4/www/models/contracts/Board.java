package com.group4.www.models.contracts;

import com.group4.www.models.tasks.contracts.Task;

import java.util.List;

public interface Board extends Printable{

    void addTask(Task task);

    void removeTask(Task task);

    void logEvent(String event);

    public String getName();

    public List<Task> getTasks();

    public List<EventLog> getBoardActivity();
}
