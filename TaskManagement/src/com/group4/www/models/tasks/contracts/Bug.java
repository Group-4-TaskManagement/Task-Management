package com.group4.www.models.tasks.contracts;

import com.group4.www.models.contracts.Identifiable;
import com.group4.www.models.enums.Priority;
import com.group4.www.models.enums.SeverityBug;
import com.group4.www.models.enums.StatusBug;

import java.util.List;

public interface Bug extends Task {

    public Priority getPriority();

    public SeverityBug getSeverity();

    void setStatus(StatusBug statusBug);

    void setPriority(Priority priorityBug);

    void setSeverity(SeverityBug severityBug);

    public List<String> getSteps();

}
