package com.group4.www.core.contacts;

import com.group4.www.commands.contracts.Command;

public interface CommandFactory {
    Command createCommandFromCommandName(String commandTypeAsString, Repository repository);
}
