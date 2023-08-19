package models;

import com.group4.www.models.BoardImpl;
import com.group4.www.models.MemberImpl;
import com.group4.www.models.TeamImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TeamImplTest {
    public static final String TEAM_NAME = "Team name";

    @Test
    public void constructor_Should_CreateNewTeam_When_ParametersAreCorrect(){
        TeamImpl team = initializeTestTeam();

        Assertions.assertEquals(TEAM_NAME,team.getName());
    }

    @Test
    public void should_AddMember_When_ValidParameters(){
        TeamImpl team = initializeTestTeam();
        MemberImpl member = new MemberImpl("Member");

        team.addMember(member);

        Assertions.assertEquals(1,team.getMembers().size());
    }

    @Test
    public void should_LogActivity_When_MemberIsAdded(){
        TeamImpl team = initializeTestTeam();
        MemberImpl member = new MemberImpl("Member");

        team.addMember(member);

        Assertions.assertEquals(1,team.getTeamActivity().size());
    }

    @Test
    public void should_RemoveMember_When_ValidParameters(){
        TeamImpl team = initializeTestTeam();
        MemberImpl member = new MemberImpl("Member");

        team.addMember(member);
        team.removeMember(member);

        Assertions.assertEquals(0,team.getMembers().size());
    }

    @Test
    public void should_LogActivity_When_MemberIsRemoved(){
        TeamImpl team = initializeTestTeam();
        MemberImpl member = new MemberImpl("Member");

        team.addMember(member);
        team.removeMember(member);

        Assertions.assertEquals(2,team.getTeamActivity().size());
    }

    @Test
    public void should_AddBoard_When_ValidParameters(){
        TeamImpl team = initializeTestTeam();
        BoardImpl board = new BoardImpl("Board");

        team.addBoard(board);

        Assertions.assertEquals(1,team.getBoards().size());
    }

    @Test
    public void should_LogActivity_When_BoardIsAdded(){
        TeamImpl team = initializeTestTeam();
        BoardImpl board = new BoardImpl("Board");

        team.addBoard(board);

        Assertions.assertEquals(1,team.getTeamActivity().size());
    }

    @Test
    public void should_RemoveBoard_When_ValidParameters(){
        TeamImpl team = initializeTestTeam();
        BoardImpl board = new BoardImpl("Board");

        team.addBoard(board);
        team.removeBoard(board);

        Assertions.assertEquals(0,team.getBoards().size());
    }

    @Test
    public void should_LogActivity_When_BoardIsRemoved(){
        TeamImpl team = initializeTestTeam();
        BoardImpl board = new BoardImpl("Board");

        team.addBoard(board);
        team.removeBoard(board);

        Assertions.assertEquals(2,team.getTeamActivity().size());
    }

    @Test
    public void should_ThrowException_When_NameLengthIsOutOfBounds(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new TeamImpl("Xxx"));
    }

    @Test
    public void shouldThrowException_When_MemberIsAlreadyAdded(){
        TeamImpl team = initializeTestTeam();
        MemberImpl member = new MemberImpl("Member");

        team.addMember(member);

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> team.addMember(member));
    }

    @Test
    public void shouldThrowException_When_MemberThatIsRemovedIsNotInTheList(){
        TeamImpl team = initializeTestTeam();
        MemberImpl member = new MemberImpl("Member");
        MemberImpl member1 = new MemberImpl("Member1");

        team.addMember(member);

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> team.removeMember(member1));
    }

    @Test
    public void shouldThrowException_When_BoardIsAlreadyAdded(){
        TeamImpl team = initializeTestTeam();
        BoardImpl board = new BoardImpl("Board");

        team.addBoard(board);

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> team.addBoard(board));
    }

    @Test
    public void shouldThrowException_When_BoardThatIsRemovedIsNotInTheList(){
        TeamImpl team = initializeTestTeam();
        BoardImpl board = new BoardImpl("Board");
        BoardImpl board1 = new BoardImpl("Board1");

        team.addBoard(board);

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> team.removeBoard(board1));
    }

    public static TeamImpl initializeTestTeam(){
        return new TeamImpl(TEAM_NAME);
    }
}
