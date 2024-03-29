package com.group4.www.commands.creations;

import com.group4.www.commands.contracts.Command;
import com.group4.www.core.contacts.Repository;
import com.group4.www.models.enums.Priority;
import com.group4.www.models.enums.SeverityBug;
import com.group4.www.models.tasks.contracts.Bug;
import com.group4.www.models.utils.ParsingHelpers;
import com.group4.www.models.utils.ValidationHelpers;

import java.util.Arrays;
import java.util.List;

public class CreateBug implements Command {
    public static final int EXPECTED_NUMBER_OF_PARAMETERS = 6;
    public static final String PARSE_PRIORITY_ERR = "Priority should be Low, Medium or High!";
    public static final String PARSE_SEVERITY_ERR = "Severity should be Minor, Major or Critical!";
    public static final String BUG_CREATED = "Bug with ID:%d was created";
    private final Repository repository;
    private Priority priority;
    private SeverityBug severityBug;
    private String board;

    public CreateBug(Repository repository) { this.repository = repository; }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_PARAMETERS);
        parseParameters(parameters);
        List <String> steps = Arrays.asList(parameters.get(4).split(";"));
        Bug bug = repository.createBugInBoard(parameters.get(0), parameters.get(1),priority,severityBug,steps,parameters.get(5));

        return String.format(BUG_CREATED,bug.getId());
    }
    private void parseParameters(List<String> parameters) {
       priority = ParsingHelpers.tryParseEnum(
                parameters.get(2),
                Priority.class,
                PARSE_PRIORITY_ERR);
       severityBug = ParsingHelpers.tryParseEnum(
                parameters.get(3),
                SeverityBug.class,
                PARSE_SEVERITY_ERR);

    }
}
