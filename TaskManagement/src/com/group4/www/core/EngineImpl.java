package com.group4.www.core;

import com.group4.www.commands.contracts.Command;
import com.group4.www.core.contacts.CommandFactory;
import com.group4.www.core.contacts.Engine;
import com.group4.www.core.contacts.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class EngineImpl implements Engine {
    public static final String EMPTY_COMMAND_ERR = "The command can not be empty";
    public static final String EXIT_COMMAND = "exit";
    public static final String MAIN_SPLIT = " ";
    public static final String SPLITTTER = "{{";
    public static final String END_SPLITTER = "}}";
    private CommandFactory commandFactory;
    private Repository repository;

    public EngineImpl() {
        this.commandFactory = new CommandFactoryImpl();
        this.repository = new RepositoryImpl();
    }

    @Override
    public void start() {
        Scanner scn = new Scanner(System.in);
        while (true) {
            try {
                String input = scn.nextLine();
                if (input.isBlank()) {
                    System.out.println(EMPTY_COMMAND_ERR);
                    continue;
                }
                if (input.equalsIgnoreCase(EXIT_COMMAND)) {
                    break;
                }
                processCommand(input);
            } catch (Exception ex) {
                if (ex.getMessage() != null && !ex.getMessage().isEmpty()) {
                    System.out.println(ex.getMessage());
                } else {
                    System.out.println(ex.toString());
                }
            }
        }
    }

    private void processCommand(String input) {
        String commandName = input.split(MAIN_SPLIT)[0];
        Command command1 = commandFactory.createCommandFromCommandName(commandName, repository);
        List<String> parameters = extractCommandParameters(input);
        String executionResult = command1.execute(parameters);
        System.out.println(executionResult);
    }

    private List<String> extractCommandParameters(String inputLine) {
        if (inputLine.contains(SPLITTTER)) {
            return extractParameters(inputLine);
        }
        String[] commandParts = inputLine.split(MAIN_SPLIT);
        List<String> parameters = new ArrayList<>();
        for (int i = 1; i < commandParts.length; i++) {
            parameters.add(commandParts[i]);
        }
        return parameters;
    }

    public List<String> extractParameters(String fullCommand) {
        int indexOfFirstSeparator = fullCommand.indexOf(MAIN_SPLIT);
        int indexOfOpenTitle = fullCommand.indexOf(SPLITTTER);
        int indexOfCloseTitle = fullCommand.indexOf(END_SPLITTER);
        int indexOfOpenDescription = fullCommand.lastIndexOf(SPLITTTER);
        int indexOfCloseDescription = fullCommand.lastIndexOf(END_SPLITTER);
        List<String> parameters = new ArrayList<>();
        if (indexOfOpenTitle >= 0) {
            parameters.add(fullCommand.substring(indexOfOpenTitle + SPLITTTER.length(), indexOfCloseTitle));
            parameters.add(fullCommand.substring(indexOfOpenDescription + SPLITTTER.length(), indexOfCloseDescription));
            fullCommand = fullCommand.replaceAll("\\{\\{.+(?=}})}}", "");
        }

        List<String> result = new ArrayList<>(Arrays.asList(fullCommand.substring(indexOfFirstSeparator + 1)
                .split(MAIN_SPLIT)));
        result.removeAll(Arrays.asList(" ", "", null));
        parameters.addAll(result);
        return parameters;
    }

}
