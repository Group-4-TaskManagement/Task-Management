package commands;

import com.group4.www.commands.contracts.Command;
import com.group4.www.commands.listings.SortBugsByTitle;
import com.group4.www.core.RepositoryImpl;
import com.group4.www.core.contacts.Repository;
import com.group4.www.models.enums.Priority;
import com.group4.www.models.enums.SeverityBug;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SortBugsByTitleTest {
    public static final String BUG_TITLE_B = "Bbbbbbbbbbbbbbb";
    public static final String BUG_TITLE_A = "AAAAAAAAAAAAAAAAAA";
    public static final String VALID_BUG_DESCRIPTION = "Valid Bug Description";
    public static final Priority VALID_BUG_PRIORITY = Priority.LOW;
    public static final SeverityBug VALID_BUG_SEVERITY = SeverityBug.CRITICAL;
    public static final List<String> VALID_BUG_STEPS = List.of("1;","2");
    public static final String VALID_BOARD = "Valid Board";
    public static final String VALID_TEAM = "Valid Team";
    Command sortBugsByTitle;
    Repository repository;

    @BeforeEach
    public void beforeEach(){
        repository = new RepositoryImpl();
        sortBugsByTitle = new SortBugsByTitle(repository);
        repository = new RepositoryImpl();
        repository.createTeam(VALID_TEAM);
        repository.createBoardInTeam(VALID_BOARD,VALID_TEAM);
        repository.createBugInBoard(BUG_TITLE_B,
                VALID_BUG_DESCRIPTION,
                VALID_BUG_PRIORITY,
                VALID_BUG_SEVERITY,
                VALID_BUG_STEPS,
                VALID_BOARD );
        repository.createBugInBoard(BUG_TITLE_A,
                VALID_BUG_DESCRIPTION,
                VALID_BUG_PRIORITY,
                VALID_BUG_SEVERITY,
                VALID_BUG_STEPS,
                VALID_BOARD );
    }

    @Test
    public void execute_Should_NotThrowException_When_ValidArguments(){
        Assertions.assertDoesNotThrow(() ->sortBugsByTitle.execute(List.of()));
    }

    @Test
    public void execute_Should_ThrowException_When_InvalidArgumentsCount(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> sortBugsByTitle.execute(List.of("1")));
    }

}
