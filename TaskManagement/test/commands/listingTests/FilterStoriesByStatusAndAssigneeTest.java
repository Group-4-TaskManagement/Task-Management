package commands.listingTests;

import com.group4.www.commands.contracts.Command;
import com.group4.www.commands.listings.FilterStoriesByAssignee;
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

public class FilterStoriesByStatusAndAssigneeTest {
    public static final String TITLE = "title name";
    public static final String DESCRIPTION = "description name";
    public static final String BOARD_NAME = "boardName";
    public static final String MEMBER_NAME = "boardName";
    public static final String TASK_NOT_EXIST = "Task does not exist";
    public static final String INVALID_STATUS = "Invalid Status";
    public static final String INVALID_MEMBER = "Xxx";
    public static final String VALID_TEAM = "Valid Team";

    public Repository repository;
    public Command filterStoriesByStatusAndAssignee;

    @BeforeEach
    public void beforeEach(){
        repository = new RepositoryImpl();
        filterStoriesByStatusAndAssignee = new FilterStoriesByStatusAndAssignee(repository);
        repository.createTeam(VALID_TEAM);
        repository.createBoardInTeam(BOARD_NAME,VALID_TEAM);
        Story story  =  createStory();
        createStory();
        repository.createPerson(MEMBER_NAME);
        repository.assignTaskToMember(story.getId(),MEMBER_NAME);
    }

    @Test
    public void execute_When_ArgumentsValid(){

        Assertions.assertDoesNotThrow(()->filterStoriesByStatusAndAssignee
                .execute(List.of(StatusStory.NOT_DONE.toString(),MEMBER_NAME)));
    }

    @Test
    public void execute_ThrowException_When_InvalidArgumentsCount(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> filterStoriesByStatusAndAssignee.execute(List.of()));
    }

    @Test
    public void execute_ThrowException_When_InvalidStatus(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> filterStoriesByStatusAndAssignee.execute(List.of(INVALID_STATUS,MEMBER_NAME)));
    }

    @Test
    public void execute_ThrowException_When_InvalidMember(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> filterStoriesByStatusAndAssignee.execute(List.of(StatusStory.NOT_DONE.toString(),INVALID_MEMBER)));
    }





    private Story createStory(){
        return repository.createStoryInBoard(TITLE, DESCRIPTION, Priority.LOW, SizeStory.LARGE,
                StatusStory.NOT_DONE, BOARD_NAME);
    }


}
