package models;

import com.group4.www.models.tasks.BugImpl;
import com.group4.www.models.tasks.FeedbackImpl;
import com.group4.www.models.tasks.contracts.Feedback;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FeedbackTests {

    public static final int VALID_ID = 1;
    public static final String VALID_TITLE = "Valid title!";
    public static final String VALID_DESCRIPTION = "Valid description!";
    public static final int VALID_RATING = 5;

    @Test
    public void constructor_Should_CreateNewFeedback_When_ParametersAreCorrect(){
        FeedbackImpl feedback=initializeTestFeedback();
        Assertions.assertAll(
                ()->Assertions.assertEquals(VALID_ID,feedback.getId()),
                ()->Assertions.assertEquals(VALID_TITLE,feedback.getTitle()),
                ()->Assertions.assertEquals(VALID_DESCRIPTION,feedback.getDescription()),
                ()->Assertions.assertEquals(VALID_RATING,feedback.getRating())
        );
    }

    @Test
    public void should_Change_Rating(){
        //Arrange
        FeedbackImpl feedback=initializeTestFeedback();
        //Act
        feedback.changeRating(8);
        // Assert
        Assertions.assertEquals(8,feedback.getRating());
    }
    @Test
    public void constructor_ShouldTrow_When_Rating_Out_Of_Bounds(){
    //Arrange
    int feedbackRating=11;
    //Act Assert
    Assertions.assertThrows
            (RuntimeException.class,()-> new FeedbackImpl
                    (VALID_ID, VALID_TITLE, VALID_DESCRIPTION,feedbackRating));
    }
    @Test
    public void constructor_ShouldTrow_When_TitleLength_IsOutOfBounds(){
        Assertions.assertThrows(RuntimeException.class,
                ()-> new FeedbackImpl(VALID_ID,"mini",VALID_DESCRIPTION,VALID_RATING));
    }
    @Test
    public void constructor_ShouldTrow_When_DescriptionLength_IsOutOfBounds(){
        Assertions.assertThrows(RuntimeException.class,
                ()-> new FeedbackImpl(VALID_ID,VALID_TITLE,"mini",VALID_RATING));
    }
    @Test
    public void constructor_ShouldTrow_When_Status_IsTheSame(){
        //Arrange
        FeedbackImpl feedback=initializeTestFeedback();
        //Act Assert
        Assertions.assertThrows(IllegalArgumentException.class,
                ()->feedback.changeStatus("New"));
    }
    @Test
    public void should_ThrowException_When_ChangeStatusInvalidInput(){
        //Arrange
        FeedbackImpl feedback=initializeTestFeedback();
        //Act Assert
        Assertions.assertThrows(IllegalArgumentException.class,
                ()->feedback.changeStatus("Xxx"));
    }
    @Test
    public void should_Return_Status_NEW_whenInitialized(){
        //Arrange
        FeedbackImpl feedback=initializeTestFeedback();
        //Act Assert
        Assertions.assertEquals("New",feedback.getStatus());
    }
    @Test
    public void should_Advance_Status(){
        //Arrange
        FeedbackImpl feedback=initializeTestFeedback();
        //Act
        feedback.changeStatus("Advance");
        // Assert
        Assertions.assertEquals("Unscheduled",feedback.getStatus());
    }
    @Test
    public void should_Revert_Status(){
        //Arrange
        FeedbackImpl feedback=initializeTestFeedback();
        //Act
        feedback.changeStatus("Advance");
        feedback.changeStatus("Revert");
        // Assert
        Assertions.assertEquals("New",feedback.getStatus());
    }
    @Test
    public void should_SetStatus(){
        //Arrange
        FeedbackImpl feedback=initializeTestFeedback();
        //Act
        feedback.changeStatus("Done");
        // Assert
        Assertions.assertEquals("Done",feedback.getStatus());
    }
    @Test
    public void should_AddLog_When_StatusChangeIsSuccessful(){
        //Arrange
        FeedbackImpl feedback = initializeTestFeedback();
        //Act
        feedback.changeStatus("Done");
        // Assert
        Assertions.assertEquals(1,feedback.getTaskActivity().size());
    }



    public static FeedbackImpl initializeTestFeedback(){
        return new FeedbackImpl(VALID_ID,
                VALID_TITLE,
                VALID_DESCRIPTION,
                VALID_RATING);
    }

}
