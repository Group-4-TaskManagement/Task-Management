import com.group4.www.core.RepositoryImpl;
import com.group4.www.core.contacts.Repository;
import com.group4.www.models.CommentImpl;
import com.group4.www.models.MemberImpl;
import com.group4.www.models.contracts.Comment;
import com.group4.www.models.contracts.Member;
import com.group4.www.models.contracts.Team;
import com.group4.www.models.enums.Priority;
import com.group4.www.models.enums.SeverityBug;
import com.group4.www.models.enums.SizeStory;
import com.group4.www.models.enums.StatusStory;
import com.group4.www.models.tasks.contracts.Bug;
import com.group4.www.models.tasks.contracts.Feedback;
import com.group4.www.models.tasks.contracts.Story;
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
    @Test
    public void createBoardInTeam(){
        repository.createTeam("Teams");
        repository.createBoardInTeam("Boards","Teams");
        Assertions.assertEquals(1,repository.getBoards().size());
    }
    @Test
    public void createBoardInTeam_Throw_Exception_WhenTeamNotExist() {

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> repository.createBoardInTeam("Boards","Teams"));
    }

    @Test
    public void addMemberToTeam(){
       Team team =  repository.createTeam("Teams");
        repository.createPerson("Member");
        repository.addMemberToTeam("Member","Teams");
        Assertions.assertEquals(1,team.getMembers().size());
    }

    @Test
    public void addMemberToTeam_Throw_Exception_WhenTeamNotExist() {
        repository.createPerson("Member");

        Assertions.assertThrows(IllegalArgumentException.class
                , () -> repository.addMemberToTeam("Member","Teams"));

    }

    @Test
    public void addMemberToTeam_Throw_Exception_WhenMemberNotExist() {
        repository.createTeam("Teams");

        Assertions.assertThrows(IllegalArgumentException.class
                , () -> repository.addMemberToTeam("Member","Teams"));

    }

    @Test
    public void changeFeedbackRating(){
        repository.createBoard("Boards");
        Feedback feedback = repository.createFeedbackInBoard("titleFeedback", "descriptionFeedback",6,"Boards");
        repository.changeFeedbackRating(5, feedback.getId());
        Assertions.assertEquals(5, feedback.getRating());
    }

    @Test
    public void changeFeedbackRating_Throw_Exception_WhenTaskNotExist() {

        Assertions.assertThrows(IllegalArgumentException.class
                , () -> repository.changeFeedbackRating(5,5));

    }

    @Test
    public void assignTaskToMember(){
        repository.createBoard("Boards");
      Story story = repository.createStoryInBoard
                ("titleBoard", "descriptionBoard", Priority.LOW,
                        SizeStory.SMALL, StatusStory.NOT_DONE,"Boards");
        Member member=repository.createPerson("Member");
        repository.assignTaskToMember(story.getId(),"Member");
        Assertions.assertEquals(1,member.getTasks().size());

    }

    @Test
    public void assignTaskToMember_Throw_Exception_WhenTaskNotExist() {
        Member member = repository.createPerson("Member");

        Assertions.assertThrows(IllegalArgumentException.class
                , () -> repository.assignTaskToMember(1,member.getName()));

    }

    @Test
    public void assignTaskToMember_Throw_Exception_WhenMemberNotExist() {
        repository.createBoard("Boards");
        Story story = repository.createStoryInBoard
                ("titleBoard", "descriptionBoard", Priority.LOW,
                        SizeStory.SMALL, StatusStory.NOT_DONE,"Boards");


        Assertions.assertThrows(IllegalArgumentException.class
                , () -> repository.assignTaskToMember(story.getId(),"Member"));

    }

    @Test
    public void unAssignTaskToMember(){
        repository.createBoard("Boards");
        Story story = repository.createStoryInBoard
                ("titleStory", "descriptionStory", Priority.LOW,
                        SizeStory.SMALL, StatusStory.NOT_DONE,"Boards");
        Story story1 = repository.createStoryInBoard
                ("titleBoard", "descriptionBoard", Priority.LOW,
                        SizeStory.SMALL, StatusStory.NOT_DONE,"Boards");
       Member member =  repository.createPerson("Member");
        repository.assignTaskToMember(story.getId(),member.getName());
        repository.assignTaskToMember(story1.getId(),member.getName());
        repository.unAssignTaskToMember(story.getId(),member.getName());
        Assertions.assertEquals(1,member.getTasks().size());

    }
@Test
    public void unAssignTaskToMember_Throw_Exception_WhenMemberNotExist() {
        repository.createBoard("Boards");
        Story story = repository.createStoryInBoard
                ("titleBoard", "descriptionBoard", Priority.LOW,
                        SizeStory.SMALL, StatusStory.NOT_DONE,"Boards");


        Assertions.assertThrows(IllegalArgumentException.class
                , () -> repository.unAssignTaskToMember(story.getId(),"Member"));

    }
    @Test
    public void addCommentToTask(){
        repository.createBoard("Boards");
        Story story = repository.createStoryInBoard
                ("titleBoard", "descriptionBoard", Priority.LOW,
                        SizeStory.SMALL, StatusStory.NOT_DONE,"Boards");
        Comment comment = new CommentImpl("author","message");
        repository.addCommentToTask(comment,story.getId());

        Assertions.assertEquals(1,story.getComments().size());
    }
@Test
    public void addCommentToTask_Throw_Exception_WhenTaskNotExist() {
    Comment comment = new CommentImpl("author","message");


        Assertions.assertThrows(IllegalArgumentException.class
                , () -> repository.addCommentToTask(comment,1));

    }
    @Test
    public void changeBugSeverity(){
        repository.createBoard("Boards");
       Bug bug = repository.createBugInBoard
                ("titleBoard", "descriptionBoard", Priority.LOW,
                        SeverityBug.MAJOR, List.of("steps"), "Boards");

       repository.changeBugSeverity(bug.getId(),SeverityBug.MINOR);
       Assertions.assertEquals(SeverityBug.MINOR,bug.getSeverity());



    }

    @Test
    public void changeBugSeverity_Throw_Exception_WhenTaskNotExist() {



        Assertions.assertThrows(IllegalArgumentException.class
                , () -> repository.changeBugSeverity(1,SeverityBug.MINOR));

    }

    @Test public void changeStorySize(){
        repository.createBoard("Boards");
      Story story =   repository.createStoryInBoard
                ("titleBoard", "descriptionBoard", Priority.LOW,
                        SizeStory.SMALL, StatusStory.NOT_DONE,"Boards");

        repository.changeStorySize(story.getId(),SizeStory.LARGE);
        Assertions.assertEquals(SizeStory.LARGE,story.getSize());
    }

    @Test
    public void changeStorySize_Throw_Exception_WhenTaskNotExist() {



        Assertions.assertThrows(IllegalArgumentException.class
                , () -> repository.changeStorySize(1,SizeStory.MEDIUM));

    }
}
