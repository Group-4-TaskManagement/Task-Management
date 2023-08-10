package com.group4.www.commands.creations;

import com.group4.www.commands.Command;
import com.group4.www.core.contacts.Repository;
import com.group4.www.models.enums.Priority;
import com.group4.www.models.utils.ParsingHelpers;
import com.group4.www.models.utils.ValidationHelpers;

import java.util.List;

    public class ChangeBugPriority implements Command {
        public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
        public static final String PRIORITY_PARSE_ERROR = "Priority of bug can be Low, Medium or High!";
        public static final String BUG_PRIORITY_CHANGED = " Priority of bug was changed successfully";
        private final Repository repository;
        private int id;
        private Priority priorityBug;

        public ChangeBugPriority(Repository repository) {this.repository = repository;}

        @Override
        public String execute(List<String> parameters) {
            ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

            parseParameters(parameters);

            repository.changeBugPriority(id, priorityBug);
            return BUG_PRIORITY_CHANGED;
        }

        private void parseParameters(List<String> parameters){
            id = ParsingHelpers.tryParseInteger(parameters.get(0), "id of bug");
            priorityBug = ParsingHelpers.tryParseEnum(parameters.get(1),Priority.class, PRIORITY_PARSE_ERROR);
        }
}
