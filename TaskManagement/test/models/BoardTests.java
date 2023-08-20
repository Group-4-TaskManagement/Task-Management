package models;

import com.group4.www.models.BoardImpl;
import com.group4.www.models.EventLogImpl;
import com.group4.www.models.tasks.FeedbackImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BoardTests {
    public static final String VALID_NAME = "Board Name";
    public static final int VALID_TASK_ID = 1;
    public static final String VALID_TASK_TITLE = "Valid title!";
    public static final String VALID_TASK_DESCRIPTION = "Valid description!";
    public static final int VALID_RATING = 5;
    @Test
    public  void constructor_CreateNewBoard_When_ArgumentsIsValid(){
        BoardImpl board=new BoardImpl(VALID_NAME);
        Assertions.assertNotNull(board);
    }
    @Test
    public void constructor_ShouldTrow_When_NameLength_IsOutOfBounds(){
        Assertions.assertThrows(RuntimeException.class,
                ()-> new BoardImpl("Xxx"));
    }
    @Test
    public void should_AddTasks_When_Valid(){
        //Arrange
        BoardImpl board=new BoardImpl(VALID_NAME);
        //Act
        board.addTask(initializeTestTask());
        //Assert
        Assertions.assertEquals(1,board.getTasks().size());
    }
    @Test
    public void should_RemoveTasks_When_Valid(){
        //Arrange
        BoardImpl board=new BoardImpl(VALID_NAME);
        FeedbackImpl task=initializeTestTask();
        //Act
        board.addTask(task);
        board.removeTask(task);
        //Assert
        Assertions.assertEquals(0,board.getTasks().size());
    }
    @Test
    public void should_AddLog_When_TaskAdded(){
        //Arrange
        BoardImpl board=new BoardImpl(VALID_NAME);
        FeedbackImpl task=initializeTestTask();
        //Act
        board.addTask(task);
        // Assert
        Assertions.assertEquals(1,board.getBoardActivity().size());
    }
    @Test
    public void should_AddLog_When_TaskRemoved(){
        //Arrange
        BoardImpl board=new BoardImpl(VALID_NAME);
        FeedbackImpl task=initializeTestTask();
        //Act
        board.addTask(task);
        board.removeTask(task);
        // Assert
        Assertions.assertEquals(2,board.getBoardActivity().size());
    }
    @Test
    public void should_Throw_When_AddedTask_Already_Exists(){
        //Arrange
        BoardImpl board=new BoardImpl(VALID_NAME);
        FeedbackImpl task=initializeTestTask();
        //Act
        board.addTask(task);
        // Assert
        Assertions.assertThrows(IllegalArgumentException.class,
                ()->board.addTask(task));

    }
    @Test
    public void should_Throw_When_RemovedTask_DoesNot_Exist(){
        //Arrange
        BoardImpl board=new BoardImpl(VALID_NAME);
        FeedbackImpl task=initializeTestTask();

        // Act Assert
        Assertions.assertThrows(IllegalArgumentException.class,
                ()->board.removeTask(task));

    }
    @Test
    public void getTasks_Should_Return_Copy(){
        //Arrange
        BoardImpl board=new BoardImpl(VALID_NAME);

        // Act
        board.getTasks().add(initializeTestTask());

        // Assert
        Assertions.assertEquals(0, board.getTasks().size());

    }
    @Test
    public void getBoardActivity_Should_Return_Copy(){
        //Arrange
        BoardImpl board=new BoardImpl(VALID_NAME);

        // Act
        board.getTasks().add(initializeTestTask());

        // Assert
        Assertions.assertEquals(0, board.getBoardActivity().size());

    }
    @Test
    public void logEvent_Should_AddTo_Activity(){
        //Arrange
        BoardImpl board=new BoardImpl(VALID_NAME);


        // Act
        board.addTask(initializeTestTask());
        // Assert
        Assertions.assertEquals(1, board.getBoardActivity().size());
    }

    public static FeedbackImpl initializeTestTask(){
        return new FeedbackImpl(VALID_TASK_ID,
                VALID_TASK_TITLE,
                VALID_TASK_DESCRIPTION,
                VALID_RATING);
    }



}