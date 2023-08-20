package commands;

import com.group4.www.commands.contracts.Command;
import com.group4.www.commands.listings.FilterBugsByStatusAndAssignee;
import com.group4.www.core.RepositoryImpl;
import com.group4.www.core.contacts.Repository;
import com.group4.www.models.enums.Priority;
import com.group4.www.models.enums.SeverityBug;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class FilterBugsByStatusAndAssigneeTest {
    public static final String VALID_BUG_TITLE = "Valid Bug Title";
    public static final String VALID_BUG_DESCRIPTION = "Valid Bug Description";
    public static final Priority VALID_BUG_PRIORITY = Priority.LOW;
    public static final SeverityBug VALID_BUG_SEVERITY = SeverityBug.CRITICAL;
    public static final List<String> VALID_BUG_STEPS = List.of("1;","2");
    public static final String VALID_BOARD = "Valid Board";
    public static final String VALID_TEAM = "Valid Team";
    public static final String VALID_STATUS = "Active";
    public static final String INVALID_STATUS = "Invalid Status";
    public static final String MEMBER = "Member";
    public static final String INVALID_MEMBER = "Xxx";
    Command filterBugsByStatusAndAssignee;
    Repository repository;

    @BeforeEach
    public void beforeEach(){
        repository = new RepositoryImpl();
        filterBugsByStatusAndAssignee = new FilterBugsByStatusAndAssignee(repository);
        repository.createTeam(VALID_TEAM);
        repository.createBoardInTeam(VALID_BOARD,VALID_TEAM);
        repository.createBugInBoard(VALID_BUG_TITLE,
                VALID_BUG_DESCRIPTION,
                VALID_BUG_PRIORITY,
                VALID_BUG_SEVERITY,
                VALID_BUG_STEPS,
                VALID_BOARD );
        repository.createPerson(MEMBER);
        repository.assignTaskToMember(1,MEMBER);
    }

    @Test
    public void execute_Should_NotThrowException_When_ValidArguments(){
        Assertions.assertDoesNotThrow(() ->filterBugsByStatusAndAssignee.execute(List.of(VALID_STATUS,MEMBER)));
    }

    @Test
    public void execute_Should_ThrowException_When_InvalidArgumentsCount(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> filterBugsByStatusAndAssignee.execute(List.of()));
    }

    @Test
    public void execute_Should_ThrowException_When_InvalidStatus(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> filterBugsByStatusAndAssignee.execute(List.of(INVALID_STATUS,MEMBER)));
    }

    @Test
    public void execute_Should_ThrowException_When_InvalidMember(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> filterBugsByStatusAndAssignee.execute(List.of(VALID_STATUS,INVALID_MEMBER)));
    }
}
