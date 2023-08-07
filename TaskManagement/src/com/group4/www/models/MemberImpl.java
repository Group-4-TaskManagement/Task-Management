package com.group4.www.models;

import com.group4.www.models.contracts.EventLog;
import com.group4.www.models.contracts.Identifiable;
import com.group4.www.models.contracts.Member;
import com.group4.www.models.contracts.Task;
import com.group4.www.models.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

public class MemberImpl implements Member, Identifiable {
    public static final int NAME_MIN_LENGTH = 5;
    public static final int NAME_MAX_LENGTH = 15;
    public static final String DESCR_LENGTH_ERROR = String.format(
            "Name must be between %d and %d symbols", NAME_MIN_LENGTH, NAME_MAX_LENGTH);

    public static final String TASK_EXIST = "The Task really exist";
    public static final String TASK_NOT_EXIST = "The Task does not exist";
    public static final String HISTORY_NOT_EXIST = "Activity history does not exist";

    private  int id;
    private String name;
    private List<Task> tasks;
    private List<EventLog> activityHistory;


    public MemberImpl(int id,String name) {
        this.id = id;
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
            if(task.getTitle().equals(task1.getTitle())){
                throw  new IllegalArgumentException(TASK_EXIST);
            }

        }
        tasks.add(task);

    }

    public  void removeTask(String title) {
        boolean isRemove = false;
        for (Task task1 : tasks) {
            if (title.equals(task1.getTitle())) {
                tasks.remove(task1);
                isRemove =  true;
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
    public int getId() {
        return id;
    }
}

