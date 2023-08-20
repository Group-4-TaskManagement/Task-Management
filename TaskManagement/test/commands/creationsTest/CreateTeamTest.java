package commands.creationsTest;

import com.group4.www.commands.contracts.Command;
import com.group4.www.commands.creations.CreateTeam;
import com.group4.www.core.RepositoryImpl;
import com.group4.www.core.contacts.Repository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CreateTeamTest {
    public static final String VALID_TEAM = "Valid Team";
    Command createTeamInRepository;
    Repository repository;

    @BeforeEach
    public void beforeEach() {
        repository = new RepositoryImpl();
        createTeamInRepository = new CreateTeam(repository);
        repository.createTeam(VALID_TEAM);
    }

    @Test
    public void should_AddTeamToRepository_When_ArgumentsValid(){
        Assertions.assertEquals(1,repository.getTeams().size());
    }

    @Test
    public void should_ThrowException_When_TeamWithSameNameExists(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> createTeamInRepository.execute(List.of(VALID_TEAM)));
    }

    @Test
    public void should_ThrowException_When_InvalidCountOfArguments(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> createTeamInRepository.execute(List.of()));
    }
}
