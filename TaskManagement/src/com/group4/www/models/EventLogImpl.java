package com.group4.www.models;

import com.group4.www.models.contracts.EventLog;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventLogImpl implements EventLog {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy HH:mm:ss");

    private final String description;
    private final LocalDateTime timestamp;

    public EventLogImpl() {
        throw new IllegalArgumentException("Description cannot be empty");
    }

    public EventLogImpl(String description) {
        if (description.isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty");
        }

        this.description = description;
        this.timestamp = LocalDateTime.now();
    }
    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String viewInfo() {
        return String.format("[%s] %s", timestamp.format(formatter), description);
    }
}
