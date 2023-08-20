package commands;

import com.group4.www.commands.contracts.Command;
import com.group4.www.commands.listings.FilterTasksByAssignee;
import com.group4.www.core.RepositoryImpl;
import com.group4.www.core.contacts.Repository;
import com.group4.www.models.enums.Priority;
import com.group4.www.models.enums.SeverityBug;
import com.group4.www.models.enums.SizeStory;
import com.group4.www.models.enums.StatusStory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;


public class FilterTasksByAssigneeTest {
    public static final String VALID_BUG_TITLE = "Valid Bug Title";
    public static final String VALID_BUG_DESCRIPTION = "Valid Bug Description";
    public static final Priority VALID_BUG_PRIORITY = Priority.LOW;
    public static final SeverityBug VALID_BUG_SEVERITY = SeverityBug.CRITICAL;
    public static final List<String> VALID_BUG_STEPS = List.of("1;","2");
    public static final String VALID_BOARD = "Valid Board";
    public static final String VALID_TEAM = "Valid Team";
    public static final String MEMBER = "Member";
    public static final String INVALID_MEMBER = "Xxx";
    public static final String VALID_STORY_TITLE = "VALID STORY_TITLE";
    public static final String VALID_STORY_DESCRIPTION = "Valid Story Description";
    public static final Priority VALID_STORY_PRIORITY = Priority.HIGH;
    public static final SizeStory VALID_STORY_SIZE = SizeStory.SMALL;
    public static final StatusStory VALID_STORY_STATUS = StatusStory.DONE;
    Command filterTasksByAssignee;
    Repository repository;

    @BeforeEach
    public void beforeEach(){
        repository = new RepositoryImpl();
        filterTasksByAssignee = new FilterTasksByAssignee(repository);
        repository.createTeam(VALID_TEAM);
        repository.createBoardInTeam(VALID_BOARD,VALID_TEAM);
        repository.createBugInBoard(VALID_BUG_TITLE,
                VALID_BUG_DESCRIPTION,
                VALID_BUG_PRIORITY,
                VALID_BUG_SEVERITY,
                VALID_BUG_STEPS,
                VALID_BOARD );
        repository.createStoryInBoard(VALID_STORY_TITLE,
                VALID_STORY_DESCRIPTION,
                VALID_STORY_PRIORITY,
                VALID_STORY_SIZE,
                VALID_STORY_STATUS,
                VALID_BOARD);
        repository.createPerson(MEMBER);
        repository.assignTaskToMember(1,MEMBER);
        repository.assignTaskToMember(2,MEMBER);
    }

    @Test
    public void execute_Should_NotThrowException_When_ValidArguments(){
        Assertions.assertDoesNotThrow(() ->filterTasksByAssignee.execute(List.of(MEMBER)));
    }

    @Test
    public void execute_Should_ThrowException_When_InvalidArgumentsCount(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> filterTasksByAssignee.execute(List.of()));
    }

    @Test
    public void execute_Should_ThrowException_When_InvalidArguments(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> filterTasksByAssignee.execute(List.of(INVALID_MEMBER)));
    }

}
