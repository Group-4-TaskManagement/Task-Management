package com.group4.www.models;

import com.group4.www.models.contracts.EventLog;
import com.group4.www.models.contracts.Member;
import com.group4.www.models.tasks.contracts.Task;
import com.group4.www.models.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MemberImpl implements Member {
    public static final int NAME_MIN_LENGTH = 5;
    public static final int NAME_MAX_LENGTH = 15;
    public static final String DESCR_LENGTH_ERROR = String.format(
            "Name must be between %d and %d symbols", NAME_MIN_LENGTH, NAME_MAX_LENGTH);

    public static final String TASK_EXIST = "The Task really exist";
    public static final String TASK_NOT_EXIST = "The Task does not exist";

    private static final String TASK_ADD = "Task with ID:%d was assigned to %s.";
    private static final String TASK_REMOVE = "The task was removed";




    private String name;
    private final List<Task> tasks;
    private final List<EventLog> activityHistory;


    public MemberImpl(String name) {
        setName(name);
        this.tasks = new ArrayList<>();
        this.activityHistory = new ArrayList<>();
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

    public  void removeTask(int id) {
        boolean isRemove = false;
        for (Task task : tasks) {
            if (id== task.getId()) {
                tasks.remove(task);
                isRemove =  true;
                addActivityHistory(TASK_REMOVE);
            }
        }
        if(!isRemove){
            throw new IllegalArgumentException(TASK_NOT_EXIST);
        }
    }

    public void addActivityHistory(String massage){
        EventLog eventLog = new EventLogImpl(massage);
        activityHistory.add(eventLog);



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
    public List<EventLog> getActivityHistory() {
        return new ArrayList<>(activityHistory);
    }

    @Override
    public String getAsString() {
        return getName();
    }
}

