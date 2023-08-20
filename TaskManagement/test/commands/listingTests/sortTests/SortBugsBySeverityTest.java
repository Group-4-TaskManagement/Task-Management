package commands.listingTests.sortTests;

import com.group4.www.commands.contracts.Command;
import com.group4.www.commands.listings.SortBugsBySeverity;
import com.group4.www.core.RepositoryImpl;
import com.group4.www.core.contacts.Repository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SortBugsBySeverityTest {
    Command sortBugsBySeverity;
    Repository repository;

    @BeforeEach
    public void beforeEach() {
        repository = new RepositoryImpl();
        sortBugsBySeverity = new SortBugsBySeverity(repository);
    }

    @Test
    public void execute_Should_NotThrowException_When_ValidArguments(){
        Assertions.assertDoesNotThrow(() -> sortBugsBySeverity.execute(List.of()));
    }

    @Test
    public void execute_Should_ThrowException_When_InvalidArgumentsCount(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> sortBugsBySeverity.execute(List.of("1")));
    }
}
