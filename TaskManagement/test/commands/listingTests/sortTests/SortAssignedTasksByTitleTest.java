package commands.listingTests.sortTests;

import com.group4.www.commands.contracts.Command;
import com.group4.www.commands.listings.SortAssignedTasksByTitle;
import com.group4.www.core.RepositoryImpl;
import com.group4.www.core.contacts.Repository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SortAssignedTasksByTitleTest {
    Command sortAssignedTasksByTitle;
    Repository repository;

    @BeforeEach
    public void beforeEach(){
        repository = new RepositoryImpl();
        sortAssignedTasksByTitle = new SortAssignedTasksByTitle(repository);
    }

    @Test
    public void execute_Should_NotThrowException_When_ValidArguments(){
        Assertions.assertDoesNotThrow(() ->sortAssignedTasksByTitle.execute(List.of()));
    }

    @Test
    public void execute_Should_ThrowException_When_InvalidArgumentsCount(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> sortAssignedTasksByTitle.execute(List.of("1")));
    }
}
