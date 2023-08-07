package com.group4.www.models.contracts;

import com.group4.www.models.EventLogImpl;

import java.util.ArrayList;
import java.util.List;

public interface Board {

    void addTask(Task task);

    void removeTask(Task task);

    void logEvent(String event);

    public String getName();

    public List<Task> getTasks();

    public List<EventLog> getHistory();
}
