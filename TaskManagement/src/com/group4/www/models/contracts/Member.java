package com.group4.www.models.contracts;

import com.group4.www.models.tasks.contracts.Task;

import java.util.List;

public interface Member extends Printable {

    String getName();

    List<Task> getTasks();

    List<EventLog> getActivityHistory();

    void addTask(Task task);

    void removeTask(int id);

    void addActivityHistory(String message);

}
