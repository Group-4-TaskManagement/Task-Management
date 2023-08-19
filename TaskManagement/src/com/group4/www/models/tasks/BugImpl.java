package com.group4.www.models.tasks;

import com.group4.www.models.contracts.EventLog;
import com.group4.www.models.enums.StatusStory;
import com.group4.www.models.tasks.contracts.Bug;
import com.group4.www.models.contracts.Member;
import com.group4.www.models.enums.Priority;
import com.group4.www.models.enums.SeverityBug;
import com.group4.www.models.enums.StatusBug;
import com.group4.www.models.utils.ParsingHelpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BugImpl extends AssignableTaskBase implements Bug {
    public static final String BUG_CHANGE_PRIORITY_MESS = "The priority of bug with ID:%d was changed from %s to %s.";
    public static final String BUG_CHANGE_STATUS_ERR = "Can not change the status of the bug. It is already at %s.";
    public static final String BUG_CHANGE_STATUS_MESS = "The status of bug with ID:%d was changed from %s to %s.";
    public static final String BUG_PARSE_ERROR = "Status of bug can be Active or Fixed!";
    public static final String BUG_CHANGE_PRIORITY_ERR = "Can not change the priority of the bug. It is already at %s.";
    public static final String BUG_CHANGE_SEVERITY_ERR = "Can not change the severity of the bug. It is already at %s.";
    public static final String BUG_CHANGE_SEVERITY_MESS = "The severity of bug with ID:%d was changed from %s to %s. ";

    private final List<String> steps;
    private Priority priority;
    private SeverityBug severity;
    private StatusBug status;

    public BugImpl(int id,String title, String description,Priority priority,SeverityBug severity, List<String> steps) {
        super(id,title, description);
        this.priority = priority;
        this.severity = severity;
        this.status = StatusBug.ACTIVE;
        this.steps = new ArrayList<>();
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
    public String getStatus() {
        return status.toString();
    }

    @Override
    public void changeStatus( String statusChange) {
        StatusBug statusBug = ParsingHelpers.tryParseEnum(statusChange,StatusBug.class,BUG_PARSE_ERROR);
        if(statusBug==status) {
            throw new IllegalArgumentException(String.format(BUG_CHANGE_STATUS_ERR, getStatus()));
        }else {
            System.out.printf(BUG_CHANGE_STATUS_MESS,getId(), getStatus(), statusBug);
            super.addLogChanges(String.format(BUG_CHANGE_STATUS_MESS,getId(),getStatus(),statusBug));
            this.status = statusBug;
        }

    }

    @Override
    public List<EventLog> getTaskActivity() {
        return super.getTaskActivity();
    }

    @Override
    public void setPriority(Priority priorityBug) {
        if(priorityBug==getPriority()) {
            throw new IllegalArgumentException(String.format(BUG_CHANGE_PRIORITY_ERR, getPriority()));
        }else {
            System.out.printf(BUG_CHANGE_PRIORITY_MESS,getId(), getPriority(), priorityBug);
            super.addLogChanges(String.format(BUG_CHANGE_PRIORITY_MESS,getId(),getPriority(),priorityBug));
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

    @Override
    public String getAsString() {
        return String.format("%s" +
                "PRIORITY:%s\n" +
                "SEVERITY:%s\n",super.getAsString(),getPriority(),getSeverity());
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
