package models;

import com.group4.www.models.enums.Priority;
import com.group4.www.models.enums.SizeStory;
import com.group4.www.models.enums.StatusStory;
import com.group4.www.models.tasks.StoryImpl;
import com.group4.www.models.tasks.contracts.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StoryImplTest {

    public static final String TITLE = "title name";
    public static final String DESCRIPTION = "description name";
    private static int id = 0;



    @Test
    public  void constructor_CreateNewStory_When_ArgumentsIsValid(){
        Story story = createStory();
        Assertions.assertNotNull(story);
    }


    @Test
    public  void constructor_CreateNewStory_Throw_Exception_When_TitleNotValid(){


        Assertions.assertThrows(IllegalArgumentException.class, () ->  new StoryImpl(1
                , ""
                , DESCRIPTION
                , Priority.LOW
                , SizeStory.SMALL
                , StatusStory.NOT_DONE));
    }

    @Test
    public  void constructor_CreateNewStory_Throw_Exception_When_DescriptionNotValid(){


        Assertions.assertThrows(IllegalArgumentException.class, () ->  new StoryImpl(1
                , TITLE
                , ""
                , Priority.LOW
                , SizeStory.SMALL
                , StatusStory.NOT_DONE));
    }

    @Test
    public void changeStatus_When_StatusIsValid(){
        Story story = createStory();
        story.changeStatus("IN_PROGRESS");
        Assertions.assertEquals(StatusStory.IN_PROGRESS.toString(),story.getStatus());

    }

    @Test
    public void changeStatus_Throw_Exception_StatusNotValid(){
        Story story = createStory();
        Assertions.assertThrows(IllegalArgumentException.class,()-> story.changeStatus("In progress") );
    }

    @Test
    public void changeStatus_Throw_Exception_StatusIsTheSame(){
        Story story = createStory();
        Assertions.assertThrows(IllegalArgumentException.class,()-> story.changeStatus("NOT_DONE") );
    }

    @Test
    public void changeSize_When_SizeIsValid(){
        Story story = createStory();
        story.changeSize(SizeStory.MEDIUM);
        Assertions.assertEquals(SizeStory.MEDIUM,story.getSize());

    }

    @Test
    public void changeSize_Throw_Exception_When_SizeIsTheSame(){
        Story story = createStory();
        Assertions.assertThrows(IllegalArgumentException.class,()-> story.changeSize(SizeStory.SMALL) );

    }

    private  Story createStory(){

        return   new StoryImpl(++id, TITLE,
                DESCRIPTION, Priority.LOW, SizeStory.SMALL, StatusStory.NOT_DONE);
    }





}
