package com.group4.www.commands.creations;

import com.group4.www.commands.Command;
import com.group4.www.core.contacts.Repository;
import com.group4.www.models.enums.SeverityBug;
import com.group4.www.models.utils.ParsingHelpers;
import com.group4.www.models.utils.ValidationHelpers;

import java.util.List;

public class ChangeBugSeverity implements Command {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    public static final String SEVERITY_PARSE_ERROR = "Severity of bug can be Minor, Major or Critical!";
    public static final String BUG_SEVERITY_CHANGED = "Severity of bug was changed successfully";
    private final Repository repository;
    private int id;
    private SeverityBug severityBug;

    public ChangeBugSeverity(Repository repository) {this.repository = repository;}

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        parseParameters(parameters);

        repository.changeBugSeverity(id, severityBug);
        return BUG_SEVERITY_CHANGED;
    }

    private void parseParameters(List<String> parameters){
        id = ParsingHelpers.tryParseInteger(parameters.get(0), "id of bug");
        severityBug = ParsingHelpers.tryParseEnum(parameters.get(1),SeverityBug.class, SEVERITY_PARSE_ERROR);
    }
}
