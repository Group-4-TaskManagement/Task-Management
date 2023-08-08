package com.group4.www.core;

import com.group4.www.commands.Command;
import com.group4.www.core.contacts.CommandFactory;
import com.group4.www.core.contacts.Engine;
import com.group4.www.core.contacts.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EngineImpl implements Engine {
    private CommandFactory commandFactory;
    private Repository repository;

    public EngineImpl() {
        this.commandFactory = new CommandFactoryImpl();
        this.repository = new RepositoryImpl();
    }

    @Override
    public void start() {
        Scanner scn = new Scanner(System.in);
        while(true) {
            try {
                String input = scn.nextLine();
                if (input.isBlank()) {
                    System.out.println("The command can not be empty");
                    continue;
                }
                if (input.equalsIgnoreCase("exit")) {
                    break;
                }
                processCommand(input);
            } catch (Exception ex){
                if (ex.getMessage() != null && !ex.getMessage().isEmpty()) {
                    System.out.println(ex.getMessage());
                } else {
                    System.out.println(ex.toString());
                }
            }
        }
    }

    private void processCommand(String input){
        String commandName = input.split(" ")[0];
        Command command1 = commandFactory.createCommandFromCommandName(commandName,repository);
        List<String> parameters = extractCommandParameters(input);
        String executionResult = command1.execute(parameters);
        System.out.println(executionResult);
    }

    private List<String> extractCommandParameters(String inputLine) {
        String[] commandParts = inputLine.split(";");
        List<String> parameters = new ArrayList<>();
        for (int i = 1; i < commandParts.length; i++) {
            parameters.add(commandParts[i]);
        }
        return parameters;
    }

}
