package commands.creationsTest;

import com.group4.www.commands.contracts.Command;
import com.group4.www.commands.creations.CreateBoard;
import com.group4.www.commands.creations.CreateBoardInTeam;
import com.group4.www.core.RepositoryImpl;
import com.group4.www.core.contacts.Repository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CreateBoardInTeamTests {
    public static final String VALID_BOARD = "Valid Board";
    public static final String VALID_TEAM = "Valid Team";
    private Command createBoardInTeamCommand;
    private Repository repository;
    @BeforeEach
    public void beforeEach() {
        repository = new RepositoryImpl();
        createBoardInTeamCommand= new CreateBoardInTeam(repository);
        repository.createTeam(VALID_TEAM);
    }
    @Test
    public void ShouldAddNewBoardToRepository_When_ValidParameters(){
        createBoardInTeamCommand.execute(List.of(VALID_BOARD,VALID_TEAM));
        Assertions.assertEquals(1,repository.getBoards().size());
    }
    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        Assertions.assertThrows(IllegalArgumentException.class,
                ()->createBoardInTeamCommand.execute(List.of()));
    }

}
