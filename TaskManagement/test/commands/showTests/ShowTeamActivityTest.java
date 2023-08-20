package commands.showTests;

import com.group4.www.commands.contracts.Command;
import com.group4.www.commands.show.ShowAllTeamBoards;
import com.group4.www.commands.show.ShowTeamActivity;
import com.group4.www.core.RepositoryImpl;
import com.group4.www.core.contacts.Repository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ShowTeamActivityTest {
    public static final String VALID_TEAM_NAME = "Valid Team Name";
    public static final String INVALID_TEAM_NAME = "Invalid Team Name";
    Command showTeamActivity;
    Repository repository;

    @BeforeEach
    public void beforeEach(){
        repository = new RepositoryImpl();
        showTeamActivity = new ShowTeamActivity(repository);
        repository.createTeam(VALID_TEAM_NAME);
    }

    @Test
    public void execute_Should_NotThrowException_When_ValidArgumentsCount(){
        Assertions.assertDoesNotThrow(() -> showTeamActivity.execute(List.of(VALID_TEAM_NAME)));
    }

    @Test
    public void execute_Should_ThrowException_When_InvalidArgument(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> showTeamActivity.execute(List.of(INVALID_TEAM_NAME)));
    }

    @Test
    public void execute_Should_ThrowException_When_InvalidArgumentsCount(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> showTeamActivity.execute(List.of()));
    }
}
