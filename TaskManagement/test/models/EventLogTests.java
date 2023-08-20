package models;

import com.group4.www.models.EventLogImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EventLogTests {
    @Test
    public void constructor_Should_CreateEventLog_When_ParametersAreCorrect(){
        EventLogImpl log=new EventLogImpl("Hello World!");
        Assertions.assertNotNull(log);
    }
    @Test
    public void shouldThrow_when_DescriptionEmpty(){
        Assertions.assertThrows(IllegalArgumentException.class,
                ()->new EventLogImpl());
    }
}
