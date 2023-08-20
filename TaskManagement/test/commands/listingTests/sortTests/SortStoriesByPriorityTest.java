package commands.listingTests.sortTests;

import com.group4.www.commands.contracts.Command;
import com.group4.www.commands.listings.SortBugsByPriority;
import com.group4.www.commands.listings.SortStoriesByPriority;
import com.group4.www.core.RepositoryImpl;
import com.group4.www.core.contacts.Repository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SortStoriesByPriorityTest {
    Command sortStoriesByPriority;
    Repository repository;

    @BeforeEach
    public void beforeEach() {
        repository = new RepositoryImpl();
        sortStoriesByPriority= new SortStoriesByPriority(repository);
    }

    @Test
    public void execute_NotThrowException_When_ValidArguments(){
        Assertions.assertDoesNotThrow(() ->sortStoriesByPriority.execute(List.of()));
    }

    @Test
    public void execute_ThrowException_When_InvalidArgumentsCount(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> sortStoriesByPriority.execute(List.of("1")));
    }



}
