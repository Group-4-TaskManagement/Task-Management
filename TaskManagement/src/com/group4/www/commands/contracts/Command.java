package com.group4.www.commands.contracts;

import java.util.List;

public interface Command {
    String execute(List<String> parameters);
}
