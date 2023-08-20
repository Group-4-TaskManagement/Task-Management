package commands;

import com.group4.www.commands.contracts.Command;
import com.group4.www.commands.listings.SortStoriesBySize;
import com.group4.www.commands.listings.SortStoriesByTitle;
import com.group4.www.core.RepositoryImpl;
import com.group4.www.core.contacts.Repository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SortStoriesByTitleTest {
    Command sortStoriesByTitle;
    Repository repository;

    @BeforeEach
    public void beforeEach() {
        repository = new RepositoryImpl();
        sortStoriesByTitle = new SortStoriesByTitle(repository);

    }

    @Test
    public void execute_NotThrowException_When_ValidArguments(){
        Assertions.assertDoesNotThrow(() ->sortStoriesByTitle.execute(List.of()));
    }

    @Test
    public void execute_ThrowException_When_InvalidArgumentsCount(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () ->sortStoriesByTitle.execute(List.of("1")));
    }
}
