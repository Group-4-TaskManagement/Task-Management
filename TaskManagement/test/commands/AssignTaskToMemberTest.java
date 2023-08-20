package commands;

import com.group4.www.commands.AddCommentToTask;
import com.group4.www.commands.AssignTaskToMember;
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

public class AssignTaskToMemberTest {
    public static final String MEMBER = "Member";

    public static final String BOARD_NAME = "boardName";
    public static final String TITLE = "title name";
    public static final String DESCRIPTION = "description name";

    private Repository repository;
    private Command assignTaskToMember;

    @BeforeEach
    public  void beforeEach(){
        repository = new RepositoryImpl();
        assignTaskToMember = new AssignTaskToMember(repository);
    }

    @Test
    public  void execute_When_ValidArguments(){

        repository.createBoard(BOARD_NAME);
        repository.createPerson(MEMBER);
        Story story = repository.createStoryInBoard(TITLE, DESCRIPTION, Priority.LOW, SizeStory.LARGE,
                StatusStory.NOT_DONE, BOARD_NAME);
        assignTaskToMember.execute(List.of(Integer.toString(story.getId()),MEMBER));
        Assertions.assertEquals(MEMBER,story.getAssignee().getName());


    }

    @Test
    public void execute_ThrowException_When_InvalidArgumentsCount(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> assignTaskToMember.execute(List.of()));
    }
}
