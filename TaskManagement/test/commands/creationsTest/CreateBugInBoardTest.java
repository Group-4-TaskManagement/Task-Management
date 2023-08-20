package commands.creationsTest;

import com.group4.www.commands.contracts.Command;
import com.group4.www.commands.creations.CreateBug;
import com.group4.www.core.RepositoryImpl;
import com.group4.www.core.contacts.Repository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CreateBugInBoardTest {
    public static final String VALID_BUG_TITLE = "Valid Bug Title";
    public static final String VALID_BUG_DESCRIPTION = "Valid Bug Description";
    public static final String VALID_BUG_PRIORITY = "High";
    public static final String VALID_BUG_SEVERITY = "Critical";
    public static final String VALID_BUG_STEPS = "Step1;Step2";
    public static final String VALID_BOARD = "Valid Board";
    public static final String VALID_TEAM = "Valid Team";
    Command createBugInBoardCommand;
    Repository repository;

    @BeforeEach
    public void beforeEach() {
        repository = new RepositoryImpl();
        createBugInBoardCommand = new CreateBug(repository);
        repository.createTeam(VALID_TEAM);
        repository.createBoardInTeam(VALID_BOARD,VALID_TEAM);
    }

    @Test
    public void execute_ShouldAddNewBugToRepository_When_ValidParameters(){
        createBugInBoardCommand.execute(List.of(VALID_BUG_TITLE,
                VALID_BUG_DESCRIPTION,
                VALID_BUG_PRIORITY,
                VALID_BUG_SEVERITY,
                VALID_BUG_STEPS,
                VALID_BOARD));

        Assertions.assertEquals(1,repository.getBugs().size());
    }

    @Test
    public void execute_Should_ThrowException_When_InvalidNumberOfArguments(){
        Assertions.assertThrows(IllegalArgumentException.class,
        () -> createBugInBoardCommand.execute(List.of()));
    }

    @Test
    public void parsing_Should_ThrowException_When_InvalidPriority(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> createBugInBoardCommand.execute(List.of(VALID_BUG_TITLE,
                        VALID_BUG_DESCRIPTION,
                        "Xxx",
                        VALID_BUG_SEVERITY,
                        VALID_BUG_STEPS,
                        VALID_BOARD)));
    }

    @Test
    public void parsing_Should_ThrowException_When_InvalidSeverity(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> createBugInBoardCommand.execute(List.of(VALID_BUG_TITLE,
                        VALID_BUG_DESCRIPTION,
                        VALID_BUG_PRIORITY,
                        "Xxx",
                        VALID_BUG_STEPS,
                        VALID_BOARD)));
    }
}
