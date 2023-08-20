package commands.listingTests.sortTests;

import com.group4.www.commands.contracts.Command;
import com.group4.www.commands.listings.SortFeedbackByTitle;
import com.group4.www.core.RepositoryImpl;
import com.group4.www.core.contacts.Repository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SortFeedbacksByTitleTest {
    private Command sortFeedbacksByTitle;
    private Repository repository;
    @BeforeEach
    public void beforeEach() {
        repository = new RepositoryImpl();
        sortFeedbacksByTitle = new SortFeedbackByTitle(repository);
    }
    @Test
    public void execute_NotThrowException_When_ValidArguments(){
        Assertions.assertDoesNotThrow(() ->sortFeedbacksByTitle.execute(List.of()));
    }
    @Test
    public void execute_ThrowException_When_InvalidArgumentsCount(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () ->sortFeedbacksByTitle.execute(List.of("1")));
    }
}
