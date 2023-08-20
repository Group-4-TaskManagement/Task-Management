package commands;

import com.group4.www.commands.ChangeStorySize;
import com.group4.www.commands.contracts.Command;
import com.group4.www.core.RepositoryImpl;
import com.group4.www.core.contacts.Repository;
import com.group4.www.models.enums.Priority;
import com.group4.www.models.enums.SizeStory;
import com.group4.www.models.enums.StatusStory;
import com.group4.www.models.tasks.contracts.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ChangeStorySizeTest {
    public static final String VALID_BOARD = "Valid Board";
    public static final String VALID_TEAM = "Valid Team";
    public static final String VALID_STORY_TITLE = "Valid Story Title";
    public static final String VALID_STORY_DESCRIPTION = "Valid Story Description";
    public static final Priority PRIORITY = Priority.LOW;
    public static final SizeStory INITIAL_SIZE = SizeStory.SMALL;
    public static final StatusStory STATUS_STORY = StatusStory.DONE;
    public static final String VALID_ID = "1";
    public static final String NEW_SIZE = "Large";
    public static final String INVALID_SIZE = "Invalid Size";
    public static final String LOW = "Low";
    public static final String INVALID_ID = "2";
    Command changeStorySize;
    Repository repository;

    Story story;

    @BeforeEach
    public void beforeEach(){
        repository = new RepositoryImpl();
        changeStorySize = new ChangeStorySize(repository);
        repository.createTeam(VALID_TEAM);
        repository.createBoardInTeam(VALID_BOARD,VALID_TEAM);
        story = repository.createStoryInBoard(VALID_STORY_TITLE,
                VALID_STORY_DESCRIPTION,
                PRIORITY,
                INITIAL_SIZE,
                STATUS_STORY,
                VALID_BOARD);
    }

    @Test
    public void execute_Should_ChangeStorySize_WhenValidArguments(){
        changeStorySize.execute(List.of(VALID_ID, NEW_SIZE));

        Assertions.assertEquals(NEW_SIZE, story.getSize().toString());
    }

    @Test
    public void execute_Should_ThrowException_When_SizeArgumentSameAsCurrentSize(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> changeStorySize.execute(List.of(VALID_ID, LOW)));
    }

    @Test
    public void execute_Should_ThrowException_When_StatusIsNotEnum(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> changeStorySize.execute(List.of(VALID_ID, INVALID_SIZE)));
    }

    @Test
    public void execute_Should_ThrowException_When_StoryDoesNotExist(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> changeStorySize.execute(List.of(INVALID_ID,NEW_SIZE)));
    }

    @Test
    public void execute_Should_ThrowException_When_InvalidArgumentCount(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> changeStorySize.execute(List.of()));
    }
}
