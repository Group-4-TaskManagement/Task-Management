package commands;

import com.group4.www.commands.ChangeBugSeverity;
import com.group4.www.commands.ChangeFeedbackRating;
import com.group4.www.commands.contracts.Command;
import com.group4.www.core.RepositoryImpl;
import com.group4.www.core.contacts.Repository;
import com.group4.www.models.contracts.Team;
import com.group4.www.models.tasks.contracts.Feedback;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ChangeFeedbackRatingTest {
    public static final String BOARD_NAME = "board name";
    public static final String TITLE = "title name";
    public static final String DESCRIPTION = "description name";


    private Repository repository;
    private Command changeFeedbackRating;

    @BeforeEach
    public void beforeEach() {
        repository = new RepositoryImpl();
        changeFeedbackRating = new ChangeFeedbackRating(repository);
        repository.createBoard(BOARD_NAME);
    }

    @Test
    public  void execute_When_ValidArguments(){
      Feedback feedback =  repository.createFeedbackInBoard(TITLE,DESCRIPTION,7,BOARD_NAME);
      changeFeedbackRating.execute(List.of("10",Integer.toString(feedback.getId())));

      Assertions.assertEquals(10,feedback.getRating());




    }

    @Test
    public void execute_ThrowException_When_InvalidArgumentsCount(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> changeFeedbackRating.execute(List.of()));
    }



}
