package commands.listingTests.sortTests;

import com.group4.www.commands.contracts.Command;
import com.group4.www.commands.listings.SortTasksByTitle;
import com.group4.www.core.RepositoryImpl;
import com.group4.www.core.contacts.Repository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SortTasksByTitleTest {
    Command sortTasksByTitle;
    Repository repository;

    @BeforeEach
    public void beforeEach(){
        repository = new RepositoryImpl();
        sortTasksByTitle = new SortTasksByTitle(repository);
    }

    @Test
    public void execute_Should_NotThrowException_When_ValidArguments(){
        Assertions.assertDoesNotThrow(() ->sortTasksByTitle.execute(List.of()));
    }

    @Test
    public void execute_Should_ThrowException_When_InvalidArgumentsCount(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> sortTasksByTitle.execute(List.of("1")));
    }
}
