package com.group4.www.models.contracts;

import java.util.List;

public interface Member  {

    String getName();

    List<Task> getTasks();

    List<EventLog> getActivityHistory();

    void addTask(Task task);

    void removeTask(String title);

    void addActivityHistory(String massage);


}
