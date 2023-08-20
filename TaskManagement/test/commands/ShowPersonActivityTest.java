package commands;

import com.group4.www.commands.contracts.Command;
import com.group4.www.commands.creations.CreatePerson;
import com.group4.www.commands.show.ShowPersonActivity;
import com.group4.www.core.RepositoryImpl;
import com.group4.www.core.contacts.Repository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ShowPersonActivityTest {
    public static final String MEMBER = "Member";
    public static final String INVALID_MEMBER = "Invalid";
    Command showPersonActivity;
    Repository repository;

    @BeforeEach
    public void beforeEach(){
        repository = new RepositoryImpl();
        showPersonActivity = new ShowPersonActivity(repository);
        repository.createPerson(MEMBER);
    }

    @Test
    public void execute_Should_NotThrowException_When_ValidArgumentsCount(){
        Assertions.assertDoesNotThrow(() -> showPersonActivity.execute(List.of(MEMBER)));
    }

    @Test
    public void execute_Should_ThrowException_When_InvalidArgument(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> showPersonActivity.execute(List.of(INVALID_MEMBER)));
    }

    @Test
    public void execute_Should_ThrowException_When_InvalidArgumentsCount(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> showPersonActivity.execute(List.of()));
    }

}
