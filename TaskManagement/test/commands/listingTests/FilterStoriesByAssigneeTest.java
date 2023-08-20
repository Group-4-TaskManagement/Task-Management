package commands.listingTests;

import com.group4.www.commands.contracts.Command;
import com.group4.www.commands.listings.FilterStoriesByAssignee;
import com.group4.www.core.RepositoryImpl;
import com.group4.www.core.contacts.Repository;
import com.group4.www.models.contracts.Member;
import com.group4.www.models.enums.Priority;
import com.group4.www.models.enums.SizeStory;
import com.group4.www.models.enums.StatusStory;
import com.group4.www.models.tasks.contracts.Story;
import com.group4.www.models.utils.ListingHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class FilterStoriesByAssigneeTest {
    public static final String TITLE = "title name";
    public static final String DESCRIPTION = "description name";
    public static final String BOARD_NAME = "boardName";
    public static final String MEMBER_NAME = "boardName";
    public static final String TASK_NOT_EXIST = "Task does not exist";
    public static final String VALID_TEAM = "Valid Team";

    public Repository repository;
    public Command filterStoriesByAssignee;

    @BeforeEach
    public void beforeEach(){
        repository = new RepositoryImpl();
        filterStoriesByAssignee = new  FilterStoriesByAssignee(repository);
    }



    @Test
    public void execute_When_ArgumentsValid(){
        repository.createTeam(VALID_TEAM);
        repository.createBoardInTeam(BOARD_NAME,VALID_TEAM);

       Story story  =  createStory();
       Story story1 =  createStory();
       repository.createPerson(MEMBER_NAME);
       repository.assignTaskToMember(story.getId(),MEMBER_NAME);

        Assertions.assertDoesNotThrow(()->filterStoriesByAssignee.execute(List.of
                (MEMBER_NAME)));
    }

    @Test
    public void execute_ThrowException_When_InvalidArgumentsCount(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> filterStoriesByAssignee.execute(List.of()));
    }


    private Story createStory(){
        return repository.createStoryInBoard(TITLE, DESCRIPTION, Priority.LOW, SizeStory.LARGE,
                StatusStory.NOT_DONE, BOARD_NAME);
    }


}
