package com.group4.www.models.tasks;

import com.group4.www.models.tasks.contracts.Bug;
import com.group4.www.models.contracts.Member;
import com.group4.www.models.enums.Priority;
import com.group4.www.models.enums.SeverityBug;
import com.group4.www.models.enums.StatusBug;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BugImpl extends TaskBase implements Bug {
    public static final String REPRODUCE_STEPS = "Please enter steps, separated by ';', for the reproduction of the bug: \n";
    public static final String CHANGE_STATUS = "The status of the bug was changed from %s to %s. ";
    public static final String ADV_STATUS_ERR = "The status of the bug can not be advanced, it is already at %s!";
    public static final String REV_STATUS_ERR = "The status of the bug can not be reverted, it is already at %s!";
    public static final String BUG_CHANGE_PRIORITY_MESS = "The priority of bug with ID:%d was changed from %s to %s.";
    public static final String ADV_PRIORITY_ERR = "The priority of the bug can not be advanced, it is already at %s!";
    public static final String REV_PRIORITY_ERR = "The priority of the bug can not be reverted, it is already at %s!";
    public static final String CHANGE_SEVERITY = "The severity of the bug was changed from %s to %s.";
    public static final String ADV_SEVERITY_ERR = "The severity of the bug can not be advanced, it is already at %s!";
    public static final String REV_SEVERITY_ERR = "The severity of the bug can not be changed, it is already at %s!";
    public static final String BUG_CHANGE_STATUS_ERR = "Can not change the status of the bug. It is already at %s.";
    public static final String BUG_CHANGE_STATUS_MESS = "The status of bug with ID:%d was changed from %s to %s.";
    public static final String BUG_CHANGE_PRIORITY_ERR = "Can not change the priority of the bug. It is already at %s.";
    public static final String BUG_CHANGE_SEVERITY_ERR = "Can not change the severity of the bug. It is already at %s.";
    public static final String BUG_CHANGE_SEVERITY_MESS = "The severity of bug with ID:%d was changed from %s to %s. ";

    private List<String> steps;
    private Priority priority;
    private SeverityBug severity;
    private StatusBug status;



    public BugImpl(int id,String title, String description, Member assignee, Priority priority, SeverityBug severity) {
        super(id,title, description, assignee);
        this.priority = priority;
        this.severity = severity;
        addStepsToReproduce();
        this.status = StatusBug.ACTIVE;
    }


    @Override
    public Priority getPriority() {
        return priority;
    }

    @Override
    public SeverityBug getSeverity() {
        return severity;
    }

    @Override
    public StatusBug getStatus() {
        return status;
    }

    @Override
    public void setStatus(StatusBug statusBug) {
        if(statusBug==getStatus()) {
            throw new IllegalArgumentException(String.format(BUG_CHANGE_STATUS_ERR, getStatus()));
        }else {
            System.out.printf(BUG_CHANGE_STATUS_MESS,getId(), getStatus(), statusBug);
            addLogChanges(String.format(BUG_CHANGE_STATUS_MESS,getId(),getStatus(),statusBug));
            this.status = statusBug;
        }
    }

    @Override
    public void setPriority(Priority priorityBug) {
        if(priorityBug==getPriority()) {
            throw new IllegalArgumentException(String.format(BUG_CHANGE_PRIORITY_ERR, getPriority()));
        }else {
            System.out.printf(BUG_CHANGE_PRIORITY_MESS,getId(), getPriority(), priorityBug);
            addLogChanges(String.format(BUG_CHANGE_PRIORITY_MESS,getId(),getPriority(),priorityBug));
            this.priority = priorityBug;
        }
    }

    @Override
    public void setSeverity(SeverityBug severityBug) {
        if(severityBug==getSeverity()) {
            throw new IllegalArgumentException(String.format(BUG_CHANGE_SEVERITY_ERR, getSeverity()));
        }else {
            System.out.printf(BUG_CHANGE_SEVERITY_MESS,getId(), getSeverity(), severityBug);
            addLogChanges(String.format(BUG_CHANGE_SEVERITY_MESS,getId(),getSeverity(),severityBug));
            this.severity = severityBug;
        }
    }

    @Override
    public List<String> getSteps() {
        return new ArrayList<>(steps);
    }

    private void addStepsToReproduce() {
        steps = new ArrayList<>();
        System.out.print(REPRODUCE_STEPS);
        Scanner scn = new Scanner(System.in);
        String steps = scn.nextLine();
        this.steps = Arrays.asList(steps.split(";"));
    }

}
