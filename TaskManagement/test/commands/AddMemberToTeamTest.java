package commands;

import com.group4.www.commands.AddCommentToTask;
import com.group4.www.commands.AddMemberToTeam;
import com.group4.www.commands.contracts.Command;
import com.group4.www.core.RepositoryImpl;
import com.group4.www.core.contacts.Repository;
import com.group4.www.models.contracts.Team;
import com.group4.www.models.enums.Priority;
import com.group4.www.models.enums.SizeStory;
import com.group4.www.models.enums.StatusStory;
import com.group4.www.models.tasks.contracts.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AddMemberToTeamTest {

    public static final String MEMBER = "Member";
    public static final String TEAM_NAME = "Team Name";
    private Repository repository;
    private Command addMemberToTeam;

    @BeforeEach
    public  void beforeEach(){
        repository = new RepositoryImpl();
        addMemberToTeam = new AddMemberToTeam(repository);
        repository.createPerson(MEMBER);

    }

    @Test
    public  void execute_When_ValidArguments(){
        Team team = repository.createTeam(TEAM_NAME);


      addMemberToTeam.execute(List.of(MEMBER, team.getName()));
      Assertions.assertEquals(1,team.getMembers().size());


    }

    @Test
    public void execute_ThrowException_When_InvalidArgumentsCount(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> addMemberToTeam.execute(List.of()));
    }




}
