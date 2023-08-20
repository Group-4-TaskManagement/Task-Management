package commands.creationsTest;

import com.group4.www.commands.contracts.Command;
import com.group4.www.commands.creations.CreateBoard;
import com.group4.www.commands.creations.CreateTeam;
import com.group4.www.core.RepositoryImpl;
import com.group4.www.core.contacts.Repository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CreateBoardTests {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    public static final String VALID_NAME = "Valid_name";

    private Command createBoardCommand;
    private Repository repository;
    @BeforeEach
    public void beforeEach() {
        repository = new RepositoryImpl();
        createBoardCommand= new CreateBoard(repository);
        repository.createBoard(VALID_NAME);
    }
    @Test
    public void should_AddBoardToRepository_When_ArgumentsValid(){
        Assertions.assertEquals(1,repository.getBoards().size());
    }
    @Test
    public void should_ThrowException_When_BoardWithSameNameExists(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> createBoardCommand.execute(List.of(VALID_NAME)));
    }
    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        Assertions.assertThrows(IllegalArgumentException.class,
                ()->createBoardCommand.execute(List.of()));
    }

}
