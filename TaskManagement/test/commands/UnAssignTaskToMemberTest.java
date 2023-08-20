package commands;

import com.group4.www.commands.UnAssignTaskToMember;
import com.group4.www.commands.contracts.Command;
import com.group4.www.commands.creations.CreatePerson;
import com.group4.www.core.RepositoryImpl;
import com.group4.www.core.contacts.Repository;
import com.group4.www.models.contracts.Member;
import com.group4.www.models.enums.Priority;
import com.group4.www.models.enums.SeverityBug;
import com.group4.www.models.enums.SizeStory;
import com.group4.www.models.enums.StatusStory;
import com.group4.www.models.tasks.contracts.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class UnAssignTaskToMemberTest {
    public static final String VALID_BOARD = "Valid Board";
    public static final String VALID_TEAM = "Valid Team";
    public static final String VALID_STORY_TITLE = "Valid Story Title";
    public static final String VALID_STORY_DESCRIPTION = "Valid Story Description";
    public static final Priority PRIORITY = Priority.LOW;
    public static final SizeStory INITIAL_SIZE = SizeStory.SMALL;
    public static final StatusStory STATUS_STORY = StatusStory.DONE;
    public static final String VALID_BUG_TITLE = "Valid Bug Title";
    public static final String VALID_BUG_DESCRIPTION = "Valid Bug Description";
    public static final Priority VALID_BUG_PRIORITY = Priority.LOW;
    public static final SeverityBug VALID_BUG_SEVERITY = SeverityBug.CRITICAL;
    public static final List<String> VALID_BUG_STEPS = List.of("1;","2");
    public static final String MEMBER = "Member";
    public static final String INVALID_MEMBER = "InvalidMember";

    Command unAssignTaskToMember;
    Repository repository;
    Task task;
    Task task1;
    Member member;

    @BeforeEach
    public void beforeEach(){
        repository = new RepositoryImpl();
        unAssignTaskToMember = new UnAssignTaskToMember(repository);
        repository.createTeam(VALID_TEAM);
        repository.createBoardInTeam(VALID_BOARD,VALID_TEAM);
        task = repository.createStoryInBoard(VALID_STORY_TITLE,
                VALID_STORY_DESCRIPTION,
                PRIORITY,
                INITIAL_SIZE,
                STATUS_STORY,
                VALID_BOARD);
        task1 = repository.createBugInBoard(VALID_BUG_TITLE,
                VALID_BUG_DESCRIPTION,
                VALID_BUG_PRIORITY,
                VALID_BUG_SEVERITY,
                VALID_BUG_STEPS,
                VALID_BOARD );
        member = repository.createPerson(MEMBER);
        repository.assignTaskToMember(1,MEMBER);
    }

    @Test
    public void execute_Should_RemoveTaskFromMembersTaskList_When_ValidArguments(){
        unAssignTaskToMember.execute(List.of("1", MEMBER));

        Assertions.assertEquals(0,member.getTasks().size());
    }

    @Test
    public void execute_Should_ThrowException_When_InvalidIDArgument(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () ->  unAssignTaskToMember.execute(List.of("2", MEMBER)));
    }

    @Test
    public void execute_Should_ThrowException_When_InvalidMemberArgument(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () ->  unAssignTaskToMember.execute(List.of("1", INVALID_MEMBER)));
    }

    @Test
    public void execute_Should_ThrowException_When_IdIsNotNumber(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () ->  unAssignTaskToMember.execute(List.of("String", MEMBER)));
    }

    @Test
    public void execute_Should_ThrowException_When_InvalidArgumentCount(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> unAssignTaskToMember.execute(List.of()));
    }

}
