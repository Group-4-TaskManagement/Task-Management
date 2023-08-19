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

    public static final String BUG_CHANGE_STATUS_ERR = "Can not change the status of the bug. It is already at %s.";
    public static final String BUG_CHANGE_STATUS_MESS = "The status of item with ID:%d switched from %s to %s.";
    public static final String BUG_PARSE_ERROR = "Status of bug can be Active or Fixed!";

    public static final String BUG_CHANGE_SEVERITY_ERR = "Can not change the severity of the bug. It is already at %s.";
    public static final String BUG_CHANGE_SEVERITY_MESS = "The severity of bug with ID:%d was changed from %s to %s. ";

    private final List<String> steps;

    private SeverityBug severity;
    private StatusBug status;

    public BugImpl(int id,String title, String description,Priority priority,SeverityBug severity, List<String> steps) {
        super(id,title, description,priority);
        this.severity = severity;
        this.status = StatusBug.ACTIVE;
        this.steps = new ArrayList<>();
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
    public String changeStatus( String statusChange) {
        StatusBug statusBug = ParsingHelpers.tryParseEnum(statusChange,StatusBug.class,BUG_PARSE_ERROR);
        StatusBug currentStatus = status;



        if(statusBug==status) {
            throw new IllegalArgumentException(String.format(BUG_CHANGE_STATUS_ERR, getStatus()));
        }else {
            super.addLogChanges(String.format(BUG_CHANGE_STATUS_MESS,getId(),getStatus(),statusBug));
            this.status = statusBug;
        }
        return String.format(BUG_CHANGE_STATUS_MESS,getId(),currentStatus,status);
    }

    @Override
    public List<EventLog> getTaskActivity() {
        return super.getTaskActivity();
    }




    @Override
    public String setSeverity(SeverityBug severityBug) {
        String currentSeverity = this.severity.toString();
        if(severityBug==getSeverity()) {
            throw new IllegalArgumentException(String.format(BUG_CHANGE_SEVERITY_ERR, getSeverity()));
        }else {
            addLogChanges(String.format(BUG_CHANGE_SEVERITY_MESS,getId(),getSeverity(),severityBug));
            this.severity = severityBug;
            return String.format(BUG_CHANGE_SEVERITY_MESS,getId(), currentSeverity, severityBug);
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
