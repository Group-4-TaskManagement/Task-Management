package commands.showTests;

import com.group4.www.commands.contracts.Command;
import com.group4.www.commands.show.ShowBoardActivity;
import com.group4.www.core.RepositoryImpl;
import com.group4.www.core.contacts.Repository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static commands.showTests.ShowAllTeamMembersTest.VALID_TEAM_NAME;

public class ShowBoardActivityTest {
    public static final String VALID_BOARD_NAME = "Board Name";
    public static final String INVALID_BOARD_NAME = "Invalid Board Name";
    Command showBoardActivity;
    Repository repository;

    @BeforeEach
    public void beforeEach(){
        repository = new RepositoryImpl();
        showBoardActivity = new ShowBoardActivity(repository);
        repository.createTeam(VALID_TEAM_NAME);
        repository.createBoardInTeam(VALID_BOARD_NAME,VALID_TEAM_NAME);
    }

    @Test
    public void execute_Should_NotThrowException_When_ValidArgumentsCount(){
        Assertions.assertDoesNotThrow(() -> showBoardActivity.execute(List.of(VALID_BOARD_NAME)));
    }

    @Test
    public void execute_Should_ThrowException_When_InvalidArgument(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> showBoardActivity.execute(List.of(INVALID_BOARD_NAME)));
    }

    @Test
    public void execute_Should_ThrowException_When_InvalidArgumentsCount(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> showBoardActivity.execute(List.of()));
    }

}
