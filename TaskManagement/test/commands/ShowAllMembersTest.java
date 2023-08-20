package commands;

import com.group4.www.commands.contracts.Command;
import com.group4.www.commands.show.ShowAllMembers;
import com.group4.www.commands.show.ShowAllTeam;
import com.group4.www.core.RepositoryImpl;
import com.group4.www.core.contacts.Repository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ShowAllMembersTest {
    Command showAllMembers;
    Repository repository;

    @BeforeEach
    public void beforeEach(){
        repository = new RepositoryImpl();
        showAllMembers = new ShowAllMembers(repository);
    }

    @Test
    public void execute_Should_NotThrowException_When_ValidArgumentsCount(){
        Assertions.assertDoesNotThrow(() -> showAllMembers.execute(List.of()));
    }

    @Test
    public void execute_Should_ThrowException_When_InvalidArgumentsCount(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> showAllMembers.execute(List.of("1")));
    }
}
