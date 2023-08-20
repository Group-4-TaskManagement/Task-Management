package commands;

import com.group4.www.commands.ChangeBugSeverity;
import com.group4.www.commands.ChangePriority;
import com.group4.www.commands.contracts.Command;
import com.group4.www.core.RepositoryImpl;
import com.group4.www.core.contacts.Repository;
import com.group4.www.models.enums.Priority;
import com.group4.www.models.enums.SeverityBug;
import com.group4.www.models.enums.SizeStory;
import com.group4.www.models.enums.StatusStory;
import com.group4.www.models.tasks.contracts.AssignableTask;
import com.group4.www.models.tasks.contracts.Bug;
import com.group4.www.models.tasks.contracts.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ChangePriorityTest {

    public static final String TITLE = "title name";
    public static final String DESCRIPTION = "description name";
    public static final String BOARD_NAME = "board name";

    private Repository repository;
    private Command changePriority;

    @BeforeEach
    public void beforeEach() {
        repository = new RepositoryImpl();
        changePriority = new ChangePriority(repository);
        repository.createBoard(BOARD_NAME);
    }

    @Test
    public void execute_When_ValidParameters(){
        AssignableTask assignableTask = repository.createStoryInBoard(TITLE,DESCRIPTION,Priority.LOW
                , SizeStory.SMALL, StatusStory.NOT_DONE,BOARD_NAME);

        changePriority.execute(List.of(Integer.toString(assignableTask.getId()),Priority.HIGH.toString()));


        Assertions.assertEquals(Priority.HIGH,assignableTask.getPriority());
    }

    @Test
    public void execute_ThrowException_When_InvalidNumberOfArguments(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> changePriority.execute(List.of()));
    }




}
