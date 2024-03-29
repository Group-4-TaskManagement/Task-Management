package com.group4.www.models;

import com.group4.www.models.contracts.EventLog;
import com.group4.www.models.contracts.Member;
import com.group4.www.models.tasks.contracts.Task;
import com.group4.www.models.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

public class MemberImpl implements Member {
    public static final int NAME_MIN_LENGTH = 5;
    public static final int NAME_MAX_LENGTH = 15;
    public static final String DESCR_LENGTH_ERROR = String.format(
            "Name must be between %d and %d symbols", NAME_MIN_LENGTH, NAME_MAX_LENGTH);
    public static final String TASK_EXIST = "The Task really exist";
    public static final String TASK_NOT_EXIST = "The Task not exist";
    private static final String TASK_ADD = "Task with ID:%d was assigned to %s.";
    private static final String TASK_REMOVE = "The task was removed";

    private String name;
    private final List<Task> tasks;
    private final List<EventLog> memberActivity;

    public MemberImpl(String name) {
        setName(name);
        this.tasks = new ArrayList<>();
        this.memberActivity = new ArrayList<>();
    }

    private void setName(String name) {
        ValidationHelpers.validateIntRange(name.length(),NAME_MIN_LENGTH, NAME_MAX_LENGTH,DESCR_LENGTH_ERROR);
        this.name = name;
    }

    public void addTask(Task task){
        for (Task task1:tasks) {
            if(task1.getId()== task.getId()){
                throw  new IllegalArgumentException(TASK_EXIST);
            }

        }
        tasks.add(task);
        addActivityHistory(String.format(TASK_ADD,task.getId(),getName()));
    }

    public  void removeTask(Task task) {
        for (Task task1:tasks) {
            if(task1.getId()== task.getId()){
                tasks.remove(task1);
                addActivityHistory(String.format(TASK_REMOVE,task.getId(),getName()));
                return;
            }
        }
        throw  new IllegalArgumentException(TASK_NOT_EXIST);
    }

    public void addActivityHistory(String massage){
        EventLog eventLog = new EventLogImpl(massage);
        memberActivity.add(eventLog);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    @Override
    public List<EventLog> getMemberActivity() {
        return new ArrayList<>(memberActivity);
    }

    @Override
    public String getAsString() {
        return getName();
    }
}

