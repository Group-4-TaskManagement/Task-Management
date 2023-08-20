package commands;

import com.group4.www.commands.contracts.Command;
import com.group4.www.commands.creations.CreateStory;
import com.group4.www.core.RepositoryImpl;
import com.group4.www.core.contacts.Repository;
import com.group4.www.models.contracts.Team;
import com.group4.www.models.enums.Priority;
import com.group4.www.models.enums.SizeStory;
import com.group4.www.models.enums.StatusStory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

;

public class CreateStoryTest {
    public static final String TITLE = "title name";
    public static final String DESCRIPTION = "description name";
    public static final String BOARD_NAME = "board name";

    private Repository repository;
    private Command createStory;


    @BeforeEach
    public void beforeEach() {
        repository = new RepositoryImpl();
        createStory = new CreateStory(repository);
    }

    @Test
    public void execute_When_ArgumentsValid() {
        repository.createBoard(BOARD_NAME);
        createStory.execute(List.of(TITLE,DESCRIPTION,Priority.LOW.toString(),SizeStory.LARGE.toString(),StatusStory.NOT_DONE.toString(),BOARD_NAME));
        Assertions.assertEquals(1, repository.getStories().size());
    }

    @Test
    public void execute_When_InvalidCountOfArguments() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> createStory.execute(List.of()));
    }
}
