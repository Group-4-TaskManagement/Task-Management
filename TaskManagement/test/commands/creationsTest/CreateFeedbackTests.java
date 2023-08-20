package commands.creationsTest;

import com.group4.www.commands.contracts.Command;
import com.group4.www.commands.creations.CreateFeedback;
import com.group4.www.commands.creations.CreateStory;
import com.group4.www.core.RepositoryImpl;
import com.group4.www.core.contacts.Repository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CreateFeedbackTests {
    public static final String TITLE = "valid title";
    public static final String DESCRIPTION = "valid description";
    public static final String BOARD_NAME = "boardName";
    public static final String VALID_RATING = "5";

    public Repository repository;
    public Command createFeedback;

    @BeforeEach
    public void beforeEach() {
        repository = new RepositoryImpl();
        createFeedback = new CreateFeedback(repository);
    }
    @Test
    public void execute_When_ArgumentsValid(){
        repository.createBoard(BOARD_NAME);
        createFeedback.execute(List.of(TITLE,DESCRIPTION, VALID_RATING,BOARD_NAME));
        Assertions.assertEquals(1, repository.getFeedbacks().size());
    }
    @Test
    public void execute_When_InvalidCountOfArguments() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> createFeedback.execute(List.of()));
    }
}
