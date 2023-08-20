package commands;

import com.group4.www.commands.ChangeStatus;
import com.group4.www.commands.contracts.Command;
import com.group4.www.commands.listings.FilterBugsByAssignee;
import com.group4.www.core.RepositoryImpl;
import com.group4.www.core.contacts.Repository;
import com.group4.www.models.enums.Priority;
import com.group4.www.models.enums.SeverityBug;
import com.group4.www.models.tasks.BugImpl;
import com.group4.www.models.tasks.contracts.AssignableTask;
import com.group4.www.models.tasks.contracts.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ChangeStatusTest {
    public static final String VALID_BUG_TITLE = "Valid Bug Title";
    public static final String VALID_BUG_DESCRIPTION = "Valid Bug Description";
    public static final Priority VALID_BUG_PRIORITY = Priority.LOW;
    public static final SeverityBug VALID_BUG_SEVERITY = SeverityBug.CRITICAL;
    public static final List<String> VALID_BUG_STEPS = List.of("1;","2");
    public static final String VALID_BOARD = "Valid Board";
    public static final String VALID_TEAM = "Valid Team";
    public static final String NEW_STATUS = "Fixed";
    public static final String VALID_ID = "1";
    public static final String CURRENT_STATUS = "Active";
    public static final String INVALID_STATUS = "Invalid Status";
    public static final String INVALID_ID = "2";
    Command changeStatus;
    Repository repository;
    Task task;

    @BeforeEach
    public void beforeEach(){
        repository = new RepositoryImpl();
        changeStatus = new ChangeStatus(repository);
        repository.createTeam(VALID_TEAM);
        repository.createBoardInTeam(VALID_BOARD,VALID_TEAM);
        task = repository.createBugInBoard(VALID_BUG_TITLE,
                VALID_BUG_DESCRIPTION,
                VALID_BUG_PRIORITY,
                VALID_BUG_SEVERITY,
                VALID_BUG_STEPS,
                VALID_BOARD );
    }

    @Test
    public void execute_Should_ChangeTaskStatus_WhenValidArguments(){
        changeStatus.execute(List.of(VALID_ID,NEW_STATUS));

        Assertions.assertEquals(NEW_STATUS, task.getStatus());
    }

    @Test
    public void execute_Should_ThrowException_When_StatusAsArgumentSameAsCurrentStatus(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> changeStatus.execute(List.of(VALID_ID, CURRENT_STATUS)));
    }

    @Test
    public void execute_Should_ThrowException_When_StatusIsNotEnum(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> changeStatus.execute(List.of(VALID_ID,INVALID_STATUS)));
    }

    @Test
    public void execute_Should_ThrowException_When_TaskWithThisIdDoesNotExist(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> changeStatus.execute(List.of(INVALID_ID,NEW_STATUS)));
    }

    @Test
    public void execute_Should_ThrowException_When_InvalidArgumentCount(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> changeStatus.execute(List.of()));
    }
}
