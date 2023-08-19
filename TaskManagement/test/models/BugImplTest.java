package models;

import com.group4.www.models.CommentImpl;
import com.group4.www.models.MemberImpl;
import com.group4.www.models.enums.Priority;
import com.group4.www.models.enums.SeverityBug;
import com.group4.www.models.tasks.BugImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;


public class BugImplTest {
    public static final int VALID_ID = 1;
    public static final String VALID_TITLE = "The system freezes";
    public static final String VALID_DESCRIPTION = "System freezes after initialization";
    public static final Priority VALID_PRIORITY = Priority.HIGH;
    public static final SeverityBug VALID_SEVERITY = SeverityBug.CRITICAL;
    @Test
    public void constructor_Should_CreateNewBug_When_ParametersAreCorrect() {
        BugImpl bug = initializeTestBug();

        Assertions.assertAll(
                () -> Assertions.assertEquals(VALID_ID, bug.getId()),
                () -> Assertions.assertEquals(VALID_TITLE, bug.getTitle()),
                () -> Assertions.assertEquals(VALID_DESCRIPTION, bug.getDescription()),
                () -> Assertions.assertEquals(VALID_PRIORITY, bug.getPriority()),
                () -> Assertions.assertEquals(VALID_SEVERITY, bug.getSeverity()),
                () -> Assertions.assertEquals(1, bug.getSteps().size())
        );
    }

    @Test
    public void getAssignee_Should_NotBeNull_WhenAssigneeIsAssigned(){
        BugImpl bug = initializeTestBug();
        MemberImpl member = new MemberImpl("Member");

        bug.addAssignee(member);

        Assertions.assertEquals(member,bug.getAssignee());
    }

    @Test
    public void should_AddComment_When_ValidArguments(){
        BugImpl bug = initializeTestBug();
        CommentImpl comment = new CommentImpl("Author","Message");

        Assertions.assertDoesNotThrow(() -> bug.addComment(comment));
    }

    @Test
    public void should_RemoveComment_When_ValidArguments(){
        BugImpl bug = initializeTestBug();
        CommentImpl comment = new CommentImpl("Author","Message");

        bug.addComment(comment);

        Assertions.assertDoesNotThrow(() -> bug.removeComment(comment));
    }

    @Test
    public void should_ReturnStatusActive_When_NewBugIsInitialized(){
        BugImpl bug = initializeTestBug();

        Assertions.assertEquals("Active", bug.getStatus());
    }
    @Test
    public void should_ReturnStatusDone_When_StatusChangeIsSuccessful(){
        BugImpl bug = initializeTestBug();

        bug.changeStatus("Fixed");

        Assertions.assertEquals("Fixed", bug.getStatus());
    }

    @Test
    public void should_AddLog_When_StatusChangeIsSuccessful(){
        BugImpl bug = initializeTestBug();

        bug.changeStatus("Fixed");

        Assertions.assertEquals(1,bug.getTaskActivity().size());
    }

    @Test
    public void should_ReturnPriorityLow_When_PriorityIsChanged(){
        BugImpl bug = initializeTestBug();

        bug.changePriority(Priority.LOW);

        Assertions.assertEquals(Priority.LOW, bug.getPriority());
    }

    @Test
    public void should_AddLog_When_PriorityChangeIsSuccessful(){
        BugImpl bug = initializeTestBug();

        bug.changePriority(Priority.LOW);

        Assertions.assertEquals(1, bug.getTaskActivity().size());
    }

    @Test
    public void should_ReturnSeverityMinor_When_SeverityChangeIsSuccessful(){
        BugImpl bug = initializeTestBug();

        bug.setSeverity(SeverityBug.MINOR);

        Assertions.assertEquals(SeverityBug.MINOR, bug.getSeverity());
    }

    @Test
    public void should_ThrowException_When_TitleLengthIsOutOfBound(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new BugImpl(VALID_ID,"Xxx",VALID_DESCRIPTION,VALID_PRIORITY,VALID_SEVERITY,List.of("")));
    }

    @Test
    public void should_ThrowException_When_DescriptionLengthIsOutOfBound(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new BugImpl(VALID_ID,VALID_TITLE,"Xxx",VALID_PRIORITY,VALID_SEVERITY,List.of("")));
    }

    @Test
    public void should_ThrowException_When_ChangeStatusInvalidInput(){
        BugImpl bug = initializeTestBug();

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> bug.changeStatus("Xxx"));
    }

    @Test
    public void should_ThrowException_When_ChangeStatusIsTheSame(){
        BugImpl bug = initializeTestBug();

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> bug.changeStatus("Active"));
    }

    @Test
    public void should_ThrowException_When_ChangePriorityIsTheSame(){
        BugImpl bug = initializeTestBug();

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> bug.changePriority(VALID_PRIORITY));
    }

    @Test
    public void should_ThrowException_When_ChangeSeverityIsTheSame(){
        BugImpl bug = initializeTestBug();

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> bug.setSeverity(VALID_SEVERITY));
    }

    @Test
    public void should_ThrowException_When_RemoveCommentIsInvalid(){
        BugImpl bug = initializeTestBug();
        CommentImpl comment = new CommentImpl("Author","Message");
        CommentImpl comment1 = new CommentImpl("Author1","Message1");

        bug.addComment(comment);

        Assertions.assertThrows(IllegalArgumentException.class,
        () -> bug.removeComment(comment1));
    }

    public static BugImpl initializeTestBug(){
        return new BugImpl(VALID_ID,
                VALID_TITLE,
                VALID_DESCRIPTION,
                VALID_PRIORITY,
                VALID_SEVERITY,
                List.of("test1"));
    }
}
