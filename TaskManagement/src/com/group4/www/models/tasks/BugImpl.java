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
    public static final String CHANGE_PRIORITY = "The priority of the bug was changed from %s to %s.";
    public static final String ADV_PRIORITY_ERR = "The priority of the bug can not be advanced, it is already at %s!";
    public static final String REV_PRIORITY_ERR = "The priority of the bug can not be reverted, it is already at %s!";
    public static final String CHANGE_SEVERITY = "The severity of the bug was changed from %s to %s.";
    public static final String ADV_SEVERITY_ERR = "The severity of the bug can not be advanced, it is already at %s!";
    public static final String REV_SEVERITY_ERR = "The severity of the bug can not be changed, it is already at %s!";

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

    @Override
    public void advanceStatus() {
        if (getStatus() != StatusBug.FIXED) {
            String currentStatus = getStatus().toString();
            status = StatusBug.values()[getStatus().ordinal() + 1];
            System.out.printf(CHANGE_STATUS,currentStatus,getStatus());
            addLogChanges(String.format(CHANGE_STATUS,currentStatus,getStatus()));
        } else{
            addLogChanges(String.format(ADV_STATUS_ERR, getStatus()));
            throw new IllegalArgumentException(String.format(ADV_STATUS_ERR,getStatus()));
        }

    }

    @Override
    public  void revertStatus() {
        if (getStatus() != StatusBug.ACTIVE) {
            String currentStatus = getStatus().toString();
            status = StatusBug.values()[getStatus().ordinal() - 1];
            System.out.printf(CHANGE_STATUS,currentStatus,getStatus());
            addLogChanges(String.format(CHANGE_STATUS,currentStatus,getStatus()));
        } else {
            addLogChanges(String.format(REV_STATUS_ERR, getStatus()));
            throw new IllegalArgumentException(String.format(REV_STATUS_ERR,getStatus()));
        }
    }

    @Override
    public void advancePriority() {
        if (getPriority() != Priority.HIGH) {
            String currentPriority = getPriority().toString();
            priority = Priority.values()[getPriority().ordinal() + 1];
            addLogChanges(String.format(CHANGE_PRIORITY,currentPriority,getPriority()));
        } else {
            addLogChanges(String.format(ADV_PRIORITY_ERR, getPriority()));
        }
    }

    @Override
    public void revertPriority() {
        if (getPriority() != Priority.LOW) {
            String currentPriority = getPriority().toString();
            priority = Priority.values()[getPriority().ordinal() - 1];
            addLogChanges(String.format(CHANGE_PRIORITY,currentPriority,getPriority()));
        } else {
            addLogChanges(String.format(REV_PRIORITY_ERR, getPriority()));
        }
    }

    @Override
    public void advanceSeverity() {
        if (getSeverity() != SeverityBug.CRITICAL) {
            String currentSeverity = getSeverity().toString();
            severity = SeverityBug.values()[getSeverity().ordinal() + 1];
            addLogChanges(String.format(CHANGE_SEVERITY,currentSeverity,getSeverity()));
        } else {
            addLogChanges(String.format(ADV_SEVERITY_ERR, getSeverity()));
        }
    }

    @Override
    public void revertSeverity() {
        if (getSeverity() != SeverityBug.MINOR) {
            String currentSeverity = getSeverity().toString();
            severity = SeverityBug.values()[getSeverity().ordinal() - 1];
            addLogChanges(String.format(CHANGE_SEVERITY,currentSeverity,getSeverity()));
        } else {
            addLogChanges(String.format(REV_SEVERITY_ERR, getSeverity()));
        }
    }

}
