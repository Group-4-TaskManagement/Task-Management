package commands.listingTests;

import com.group4.www.commands.contracts.Command;
import com.group4.www.commands.listings.FilterStoriesByStatus;
import com.group4.www.commands.listings.FilterStoriesByStatusAndAssignee;
import com.group4.www.core.RepositoryImpl;
import com.group4.www.core.contacts.Repository;
import com.group4.www.models.contracts.Member;
import com.group4.www.models.enums.Priority;
import com.group4.www.models.enums.SizeStory;
import com.group4.www.models.enums.StatusStory;
import com.group4.www.models.tasks.contracts.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class FilterStoriesByStatusTest {

    public static final String TITLE = "title name";
    public static final String DESCRIPTION = "description name";
    public static final String BOARD_NAME = "boardName";
    public static final String MEMBER_NAME = "boardName";
    public static final String TASK_NOT_EXIST = "Task does not exist";
    public Repository repository;
    public Command filterStoriesByStatus;

    @BeforeEach
    public void beforeEach(){
        repository = new RepositoryImpl();
        filterStoriesByStatus = new FilterStoriesByStatus(repository);
    }

    @Test
    public void execute_When_ArgumentsValid(){
        repository.createBoard(BOARD_NAME);
        repository.createStoryInBoard(TITLE, DESCRIPTION, Priority.LOW, SizeStory.LARGE,
                StatusStory.NOT_DONE, BOARD_NAME);
        repository.createStoryInBoard(TITLE, DESCRIPTION, Priority.LOW, SizeStory.LARGE,
                StatusStory.IN_PROGRESS, BOARD_NAME);

        Assertions.assertDoesNotThrow(()->filterStoriesByStatus.execute(List.of(StatusStory.NOT_DONE.toString())));
    }

    @Test
    public void execute_ThrowException_When_InvalidArgumentsCount(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> filterStoriesByStatus.execute(List.of()));
    }


}
