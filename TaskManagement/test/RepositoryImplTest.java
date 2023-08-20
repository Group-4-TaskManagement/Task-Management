import com.group4.www.core.RepositoryImpl;
import com.group4.www.core.contacts.Repository;
import com.group4.www.models.CommentImpl;
import com.group4.www.models.contracts.Comment;
import com.group4.www.models.contracts.Member;
import com.group4.www.models.contracts.Team;
import com.group4.www.models.enums.Priority;
import com.group4.www.models.enums.SeverityBug;
import com.group4.www.models.enums.SizeStory;
import com.group4.www.models.enums.StatusStory;
import com.group4.www.models.tasks.StoryImpl;
import com.group4.www.models.tasks.contracts.Bug;
import com.group4.www.models.tasks.contracts.Feedback;
import com.group4.www.models.tasks.contracts.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class RepositoryImplTest {
    public static final String TITLE = "title name";
    public static final String DESCRIPTION = "description name";

    public static final String BUG_STEPS = "Step1;Step2";

    public static final String BOARD_NAME = "boardName";
    public static final String TEAM_NAME = "teamName";

    public  static final String MEMBER_NAME = "memberName";
    public  static final String AUTHOR_NAME = "author";
    public  static final String MESSAGE = "message";
    private static int id;

    private Repository repository;

    @BeforeEach
    public void beforeEach() {
        repository = new RepositoryImpl();
    }


    @Test
    public void createComment() {
        Comment comment = repository.createComment(AUTHOR_NAME, MESSAGE);
        Assertions.assertNotNull(comment);

    }

    @Test
    public void createPerson() {
        repository.createPerson(MEMBER_NAME);
        Assertions.assertEquals(1, repository.getMembers().size());

    }

    @Test
    public void createPerson_Throw_Exception_WhenMemberExist() {
        repository.createPerson(MEMBER_NAME);
        Assertions.assertThrows(IllegalArgumentException.class, () -> repository.createPerson(MEMBER_NAME));

    }

    @Test
    public void createTeam() {
        repository.createTeam(TEAM_NAME);
        Assertions.assertEquals(1, repository.getTeams().size());

    }

    @Test
    public void createTeam_Throw_Exception_WhenTeamExist() {
        repository.createTeam(TEAM_NAME);
        Assertions.assertThrows(IllegalArgumentException.class, () -> repository.createTeam(TEAM_NAME));

    }

    @Test
    public void createBoard() {
        repository.createBoard(BOARD_NAME);
        Assertions.assertEquals(1, repository.getBoards().size());

    }

    @Test
    public void createBoard_Throw_Exception_WhenBoardExist() {
        repository.createBoard(BOARD_NAME);
        Assertions.assertThrows(IllegalArgumentException.class, () -> repository.createBoard(BOARD_NAME));

    }

    @Test
    public void createBugInBoard() {
        repository.createBoard(BOARD_NAME);
        repository.createBugInBoard
                (TITLE, DESCRIPTION, Priority.LOW,
                        SeverityBug.MAJOR, List.of(BUG_STEPS), BOARD_NAME);

        Assertions.assertEquals(1, repository.getBugs().size());
    }

    @Test
    public void createBugInBoard_Throw_Exception_WhenBoardNotExist() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> repository.createBugInBoard
                (TITLE, DESCRIPTION, Priority.LOW,
                        SeverityBug.MAJOR, List.of(BUG_STEPS), BOARD_NAME));

    }

    @Test
    public void createStoryInBoard() {
        repository.createBoard(BOARD_NAME);
        repository.createStoryInBoard
                (TITLE, DESCRIPTION, Priority.LOW,
                        SizeStory.SMALL, StatusStory.NOT_DONE, BOARD_NAME);

        Assertions.assertEquals(1, repository.getStories().size());
    }

    @Test
    public void createStoryInBoard_Throw_Exception_WhenBoardNotExist() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> repository.createStoryInBoard
                (TITLE, DESCRIPTION, Priority.LOW,
                        SizeStory.SMALL, StatusStory.NOT_DONE, BOARD_NAME));

    }

    @Test
    public void createFeedbackInBoard() {
        repository.createBoard(BOARD_NAME);
        repository.createFeedbackInBoard(TITLE, DESCRIPTION, 6, BOARD_NAME);
        Assertions.assertEquals(1, repository.getFeedbacks().size());
    }

    @Test
    public void createFeedbackInBoard_Throw_Exception_WhenBoardNotExist() {

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> repository.createFeedbackInBoard(TITLE, DESCRIPTION, 6, BOARD_NAME));

    }

    @Test
    public void createBoardInTeam() {
        repository.createTeam(TEAM_NAME);
        repository.createBoardInTeam(BOARD_NAME, TEAM_NAME);
        Assertions.assertEquals(1, repository.getBoards().size());
    }

    @Test
    public void createBoardInTeam_Throw_Exception_WhenTeamNotExist() {

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> repository.createBoardInTeam(BOARD_NAME, TEAM_NAME));
    }

    @Test
    public void addMemberToTeam() {
        Team team = repository.createTeam(TEAM_NAME);
        repository.createPerson(MEMBER_NAME);
        repository.addMemberToTeam(MEMBER_NAME, TEAM_NAME);
        Assertions.assertEquals(1, team.getMembers().size());
    }

    @Test
    public void addMemberToTeam_Throw_Exception_WhenTeamNotExist() {
        repository.createPerson(MEMBER_NAME);

        Assertions.assertThrows(IllegalArgumentException.class
                , () -> repository.addMemberToTeam(MEMBER_NAME, TEAM_NAME));

    }

    @Test
    public void addMemberToTeam_Throw_Exception_WhenMemberNotExist() {
        repository.createTeam(TEAM_NAME);

        Assertions.assertThrows(IllegalArgumentException.class
                , () -> repository.addMemberToTeam(MEMBER_NAME, TEAM_NAME));

    }

    @Test
    public void changeFeedbackRating() {
        repository.createBoard(BOARD_NAME);
        Feedback feedback = repository.createFeedbackInBoard(TITLE, DESCRIPTION, 6, BOARD_NAME);
        repository.changeFeedbackRating(5, feedback.getId());
        Assertions.assertEquals(5, feedback.getRating());
    }

    @Test
    public void changeFeedbackRating_Throw_Exception_WhenTaskNotExist() {

        Assertions.assertThrows(IllegalArgumentException.class
                , () -> repository.changeFeedbackRating(5, 5));

    }

    @Test
    public void assignTaskToMember() {
        repository.createBoard(BOARD_NAME);
        Story story = repository.createStoryInBoard
                (TITLE, DESCRIPTION, Priority.LOW,
                        SizeStory.SMALL, StatusStory.NOT_DONE, BOARD_NAME);
        Member member = repository.createPerson(MEMBER_NAME);
        repository.assignTaskToMember(story.getId(), MEMBER_NAME);
        Assertions.assertEquals(1, member.getTasks().size());

    }

    @Test
    public void assignTaskToMember_Throw_Exception_WhenTaskNotExist() {
        Member member = repository.createPerson(MEMBER_NAME);

        Assertions.assertThrows(IllegalArgumentException.class
                , () -> repository.assignTaskToMember(1, member.getName()));

    }

    @Test
    public void assignTaskToMember_Throw_Exception_WhenMemberNotExist() {
        repository.createBoard(BOARD_NAME);
        Story story = repository.createStoryInBoard
                (TITLE, DESCRIPTION, Priority.LOW,
                        SizeStory.SMALL, StatusStory.NOT_DONE, BOARD_NAME);


        Assertions.assertThrows(IllegalArgumentException.class
                , () -> repository.assignTaskToMember(story.getId(), MEMBER_NAME));

    }

    @Test
    public void unAssignTaskToMember() {
        repository.createBoard(BOARD_NAME);
        Story story = repository.createStoryInBoard
                (TITLE, DESCRIPTION, Priority.LOW,
                        SizeStory.SMALL, StatusStory.NOT_DONE, BOARD_NAME);
        Story story1 = repository.createStoryInBoard
                (TITLE, DESCRIPTION, Priority.LOW,
                        SizeStory.SMALL, StatusStory.NOT_DONE, BOARD_NAME);
        Member member = repository.createPerson(MEMBER_NAME);
        repository.assignTaskToMember(story.getId(), member.getName());
        repository.assignTaskToMember(story1.getId(), member.getName());
        repository.unAssignTaskToMember(story.getId(), member.getName());
        Assertions.assertEquals(1, member.getTasks().size());

    }

    @Test
    public void unAssignTaskToMember_Throw_Exception_WhenMemberNotExist() {
        repository.createBoard(BOARD_NAME);
        Story story = repository.createStoryInBoard
                (TITLE, DESCRIPTION, Priority.LOW,
                        SizeStory.SMALL, StatusStory.NOT_DONE, BOARD_NAME);


        Assertions.assertThrows(IllegalArgumentException.class
                , () -> repository.unAssignTaskToMember(story.getId(), MEMBER_NAME));

    }

    @Test
    public void addCommentToTask() {
        repository.createBoard(BOARD_NAME);
        Story story = repository.createStoryInBoard
                (TITLE, DESCRIPTION, Priority.LOW,
                        SizeStory.SMALL, StatusStory.NOT_DONE, BOARD_NAME);
        Comment comment = new CommentImpl(AUTHOR_NAME, MESSAGE);
        repository.addCommentToTask(comment, story.getId());

        Assertions.assertEquals(1, story.getComments().size());
    }

    @Test
    public void addCommentToTask_Throw_Exception_WhenTaskNotExist() {
        Comment comment = new CommentImpl(AUTHOR_NAME, MESSAGE);


        Assertions.assertThrows(IllegalArgumentException.class
                , () -> repository.addCommentToTask(comment, 1));

    }

    @Test
    public void changeBugSeverity() {
        repository.createBoard(BOARD_NAME);
        Bug bug = repository.createBugInBoard
                (TITLE, DESCRIPTION, Priority.LOW,
                        SeverityBug.MAJOR, List.of(BUG_STEPS), BOARD_NAME);

        repository.changeBugSeverity(bug.getId(), SeverityBug.MINOR);
        Assertions.assertEquals(SeverityBug.MINOR, bug.getSeverity());


    }

    @Test
    public void changeBugSeverity_Throw_Exception_WhenTaskNotExist() {

        Assertions.assertThrows(IllegalArgumentException.class
                , () -> repository.changeBugSeverity(1, SeverityBug.MINOR));

    }

    @Test
    public void changeStorySize() {
        repository.createBoard(BOARD_NAME);
        Story story = repository.createStoryInBoard
                (TITLE, DESCRIPTION, Priority.LOW,
                        SizeStory.SMALL, StatusStory.NOT_DONE, BOARD_NAME);

        repository.changeStorySize(story.getId(), SizeStory.LARGE);
        Assertions.assertEquals(SizeStory.LARGE, story.getSize());
    }

    @Test
    public void changeStorySize_Throw_Exception_WhenTaskNotExist() {

        Assertions.assertThrows(IllegalArgumentException.class
                , () -> repository.changeStorySize(1, SizeStory.MEDIUM));

    }


}
