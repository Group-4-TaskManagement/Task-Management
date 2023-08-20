package commands.showTests;

import com.group4.www.commands.contracts.Command;
import com.group4.www.commands.show.ShowAllTeam;
import com.group4.www.core.RepositoryImpl;
import com.group4.www.core.contacts.Repository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ShowAllTeamsTest {
    Command showAllTeams;
    Repository repository;

    @BeforeEach
    public void beforeEach(){
        repository = new RepositoryImpl();
        showAllTeams = new ShowAllTeam(repository);
    }

    @Test
    public void execute_Should_NotThrowException_When_ValidArgumentsCount(){
        Assertions.assertDoesNotThrow(() -> showAllTeams.execute(List.of()));
    }

    @Test
    public void execute_Should_ThrowException_When_InvalidArgumentsCount(){
        Assertions.assertDoesNotThrow(() -> showAllTeams.execute(List.of("1")));
    }
}
