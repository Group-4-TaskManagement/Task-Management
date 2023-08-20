package commands.listingTests.sortTests;

import com.group4.www.commands.contracts.Command;
import com.group4.www.commands.listings.SortFeedbackByRating;
import com.group4.www.core.RepositoryImpl;
import com.group4.www.core.contacts.Repository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SortFeedbacksByRatingTest {
    private Command sortFeedbacksByRating;
    private Repository repository;
    @BeforeEach
    public void beforeEach() {
        repository = new RepositoryImpl();
        sortFeedbacksByRating = new SortFeedbackByRating(repository);
    }
    @Test
    public void execute_NotThrowException_When_ValidArguments(){
        Assertions.assertDoesNotThrow(() ->sortFeedbacksByRating.execute(List.of()));
    }
    @Test
    public void execute_ThrowException_When_InvalidArgumentsCount(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () ->sortFeedbacksByRating.execute(List.of("1")));
    }
}
