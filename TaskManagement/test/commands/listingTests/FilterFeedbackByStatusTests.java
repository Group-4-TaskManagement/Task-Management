package commands.listingTests;

import com.group4.www.commands.contracts.Command;
import com.group4.www.commands.listings.FilterFeedbacksByStatus;
import com.group4.www.commands.listings.FilterStoriesByStatus;
import com.group4.www.core.RepositoryImpl;
import com.group4.www.core.contacts.Repository;
import com.group4.www.models.enums.StatusFeedback;
import com.group4.www.models.enums.StatusStory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class FilterFeedbackByStatusTests {
    public static final String TITLE = "valid title";
    public static final String DESCRIPTION = "valid description";
    public static final String BOARD_NAME = "boardName";
    public static final int VALID_RATING = 5;
    public Repository repository;
    public Command filterFeedbacksByStatus;
    @BeforeEach
    public void beforeEach(){
        repository = new RepositoryImpl();
        filterFeedbacksByStatus = new FilterFeedbacksByStatus(repository);
    }
    @Test
    public void execute_When_ArgumentsValid(){
        repository.createBoard(BOARD_NAME);
        repository.createFeedbackInBoard(TITLE,DESCRIPTION,VALID_RATING,BOARD_NAME).advanceStatus();
        repository.createFeedbackInBoard(TITLE,DESCRIPTION,VALID_RATING,BOARD_NAME);
        Assertions.assertDoesNotThrow(()->filterFeedbacksByStatus.execute(List.of(StatusFeedback.UNSCHEDULED.toString())));
    }
    @Test
    public void execute_ThrowException_When_InvalidArgumentsCount(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> filterFeedbacksByStatus.execute(List.of()));
    }

}
