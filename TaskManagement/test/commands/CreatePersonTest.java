package commands;

import com.group4.www.commands.contracts.Command;
import com.group4.www.commands.creations.CreatePerson;
import com.group4.www.core.RepositoryImpl;
import com.group4.www.core.contacts.Repository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.List;

public class CreatePersonTest {

    public  static final String MEMBER_NAME = "memberName";

    public Command createPerson;
    Repository repository;

    @BeforeEach
    public void beforeEach(){
        repository = new RepositoryImpl();
        createPerson = new CreatePerson(repository);

    }

    @Test
    public void execute_When_ArgumentsValid(){
        createPerson.execute(List.of(MEMBER_NAME));
        Assertions.assertEquals(1,repository.getMembers().size());
    }

    @Test
    public void execute_When_InvalidCountOfArguments(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> createPerson.execute(List.of()));
    }


}
