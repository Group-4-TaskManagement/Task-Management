package commands;

import com.group4.www.commands.ChangeBugSeverity;
import com.group4.www.commands.contracts.Command;
import com.group4.www.core.RepositoryImpl;
import com.group4.www.core.contacts.Repository;
import com.group4.www.models.enums.Priority;
import com.group4.www.models.enums.SeverityBug;
import com.group4.www.models.tasks.contracts.Bug;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ChangeBugSeverityTest {
    public static final String BOARD_NAME = "boardName";
    public static final String TITLE = "title name";
    public static final String DESCRIPTION = "description name";
    public static final String PRIORITY = "High";
    public static final String SEVERITY = "Critical";
    public static final String STEPS = "Step1;Step2";



    private Repository repository;
    private Command changeBugSeverity;

    @BeforeEach
    public void beforeEach() {
        repository = new RepositoryImpl();
        changeBugSeverity = new ChangeBugSeverity(repository);
        repository.createBoard(BOARD_NAME);
    }

    @Test
    public void execute_When_ValidParameters(){
        Bug bug = repository.createBugInBoard(TITLE,DESCRIPTION,Priority.LOW,SeverityBug.MINOR,List.of(STEPS),BOARD_NAME);
        changeBugSeverity.execute(List.of(Integer.toString(bug.getId()),SeverityBug.CRITICAL.toString()));


        Assertions.assertEquals(SeverityBug.CRITICAL,bug.getSeverity());
    }

    @Test
    public void execute_ThrowException_When_InvalidNumberOfArguments(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> changeBugSeverity.execute(List.of()));
    }



    @Test
    public void parsing_ThrowException_When_InvalidSeverity(){
        Bug bug = repository.createBugInBoard(TITLE,DESCRIPTION,
                Priority.LOW,SeverityBug.MINOR,List.of(STEPS),BOARD_NAME);

        Assertions.assertThrows(IllegalArgumentException.class,()-> changeBugSeverity.execute(List.of(Integer.toString(bug.getId()),"hhh")));

    }
}
