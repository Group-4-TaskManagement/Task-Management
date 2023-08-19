import com.group4.www.core.RepositoryImpl;
import com.group4.www.core.contacts.Repository;
import com.group4.www.models.CommentImpl;
import com.group4.www.models.MemberImpl;
import com.group4.www.models.contracts.Comment;
import com.group4.www.models.contracts.Member;
import com.group4.www.models.enums.Priority;
import com.group4.www.models.enums.SeverityBug;
import com.group4.www.models.enums.SizeStory;
import com.group4.www.models.enums.StatusStory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class RepositoryImplTest {

    private Repository repository;

    @BeforeEach
    public void beforeEach() {
        repository = new RepositoryImpl();
    }


    @Test
    public void createComment() {
        Comment comment = repository.createComment("author", "message");
        Assertions.assertNotNull(comment);

    }

    @Test
    public void createPerson() {
        repository.createPerson("Member");
        Assertions.assertEquals(1, repository.getMembers().size());

    }

    @Test
    public void createPerson_Throw_Exception_WhenMemberExist() {
        repository.createPerson("Member");
        Assertions.assertThrows(IllegalArgumentException.class, () -> repository.createPerson("Member"));

    }

    @Test
    public void createTeam() {
        repository.createTeam("Teams");
        Assertions.assertEquals(1, repository.getTeams().size());

    }

    @Test
    public void createTeam_Throw_Exception_WhenTeamExist() {
        repository.createTeam("Teams");
        Assertions.assertThrows(IllegalArgumentException.class, () -> repository.createTeam("Teams"));

    }

    @Test
    public void createBoard() {
        repository.createBoard("Boards");
        Assertions.assertEquals(1, repository.getBoards().size());

    }
    @Test
    public void createBoard_Throw_Exception_WhenBoardExist() {
        repository.createBoard("Boards");
        Assertions.assertThrows(IllegalArgumentException.class, () -> repository.createBoard("Boards"));

    }
    @Test
    public  void createBugInBoard(){
        repository.createBoard("Boards");
        repository.createBugInBoard
                ("titleBoard", "descriptionBoard", Priority.LOW,
                        SeverityBug.MAJOR, List.of("steps"), "Boards");

        Assertions.assertEquals(1,repository.getBugs().size());
    }

    @Test
    public void createBugInBoard_Throw_Exception_WhenBoardNotExist() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> repository.createBugInBoard
                ("titleBoard", "descriptionBoard", Priority.LOW,
                        SeverityBug.MAJOR, List.of("steps"), "Boards"));

    }

    @Test
    public  void createStoryInBoard(){
        repository.createBoard("Boards");
        repository.createStoryInBoard
                ("titleBoard", "descriptionBoard", Priority.LOW,
                        SizeStory.SMALL, StatusStory.NOT_DONE,"Boards");

        Assertions.assertEquals(1,repository.getStories().size());
    }

    @Test
    public void createStoryInBoard_Throw_Exception_WhenBoardNotExist() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> repository.createStoryInBoard
                ("titleBoard", "descriptionBoard", Priority.LOW,
                        SizeStory.SMALL, StatusStory.NOT_DONE,"Boards"));

    }

    @Test
    public void createFeedbackInBoard(){
        repository.createBoard("Boards");
        repository.createFeedbackInBoard("titleFeedback", "descriptionFeedback",6,"Boards");
        Assertions.assertEquals(1,repository.getFeedbacks().size());
    }

    @Test
    public void createFeedbackInBoard_Throw_Exception_WhenBoardNotExist() {

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> repository.createFeedbackInBoard("titleFeedback", "descriptionFeedback",6,"Boards"));

    }
}
