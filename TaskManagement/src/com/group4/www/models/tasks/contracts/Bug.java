package com.group4.www.models.tasks.contracts;

import com.group4.www.models.enums.Priority;
import com.group4.www.models.enums.SeverityBug;
import com.group4.www.models.enums.StatusBug;

import java.util.List;

public interface Bug extends Task {
    //TODO is it necessary for Bug Interface to extend Task interface???
    public Priority getPriority();

    public SeverityBug getSeverity();

    public StatusBug getStatus();

    public void advanceStatus();

    public void revertStatus();

    public void advancePriority();

    public void revertPriority();

    public void advanceSeverity();

    public void revertSeverity();

    public List<String> getSteps();

}
