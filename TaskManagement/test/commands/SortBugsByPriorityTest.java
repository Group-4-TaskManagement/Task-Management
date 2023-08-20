package commands;

import com.group4.www.commands.contracts.Command;
import com.group4.www.commands.listings.SortBugsByPriority;
import com.group4.www.core.RepositoryImpl;
import com.group4.www.core.contacts.Repository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SortBugsByPriorityTest {
    Command sortBugsByPriority;
    Repository repository;

    @BeforeEach
    public void beforeEach() {
        repository = new RepositoryImpl();
        sortBugsByPriority = new SortBugsByPriority(repository);
    }

    @Test
    public void execute_Should_NotThrowException_When_ValidArguments(){
        Assertions.assertDoesNotThrow(() ->sortBugsByPriority.execute(List.of()));
    }

    @Test
    public void execute_Should_ThrowException_When_InvalidArgumentsCount(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> sortBugsByPriority.execute(List.of("1")));
    }
}
