package com.group4.www.commands;

import java.util.List;

public interface Command {
    String execute(List<String> parameters);
}
