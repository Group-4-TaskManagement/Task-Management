import com.group4.www.models.enums.Priority;
import com.group4.www.models.enums.SizeStory;
import com.group4.www.models.enums.StatusStory;
import com.group4.www.models.tasks.StoryImpl;
import com.group4.www.models.tasks.contracts.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StoryImplTest {



    @Test
    public  void constructor_CreateNewStory_When_ArgumentsIsValid(){
        Story story =
                new StoryImpl(1, "storyTitle",
                        "storyDescription", Priority.LOW, SizeStory.SMALL, StatusStory.NOT_DONE);

        Assertions.assertNotNull(story);
    }


    @Test
    public  void constructor_CreateNewStory_Throw_Exception_When_TitleNotValid(){


        Assertions.assertThrows(IllegalArgumentException.class, () ->  new StoryImpl(1
                , ""
                , "storyDescription"
                , Priority.LOW
                , SizeStory.SMALL
                , StatusStory.NOT_DONE));
    }

    @Test
    public  void constructor_CreateNewStory_Throw_Exception_When_DescriptionNotValid(){


        Assertions.assertThrows(IllegalArgumentException.class, () ->  new StoryImpl(1
                , "storyTitle"
                , ""
                , Priority.LOW
                , SizeStory.SMALL
                , StatusStory.NOT_DONE));
    }

    @Test
    public void changeStatus_When_StatusIsValid(){
        Story story =
                new StoryImpl(1, "storyTitle",
                        "storyDescription", Priority.LOW, SizeStory.SMALL, StatusStory.NOT_DONE);
        story.changeStatus("IN_PROGRESS");
        Assertions.assertEquals(StatusStory.IN_PROGRESS.toString(),story.getStatus());

    }

    @Test
    public void changeStatus_Throw_Exception_StatusNotValid(){
        Story story =
                new StoryImpl(1, "storyTitle",
                        "storyDescription", Priority.LOW, SizeStory.SMALL, StatusStory.NOT_DONE);
        Assertions.assertThrows(IllegalArgumentException.class,()-> story.changeStatus("In progress") );
    }

    @Test
    public void changeStatus_Throw_Exception_StatusIsTheSame(){
        Story story =
                new StoryImpl(1, "storyTitle",
                        "storyDescription", Priority.LOW, SizeStory.SMALL, StatusStory.NOT_DONE);
        Assertions.assertThrows(IllegalArgumentException.class,()-> story.changeStatus("NOT_DONE") );
    }

    @Test
    public void changeSize_When_SizeIsValid(){
        Story story =
                new StoryImpl(1, "storyTitle",
                        "storyDescription", Priority.LOW, SizeStory.SMALL, StatusStory.NOT_DONE);
        story.changeSize(SizeStory.MEDIUM);
        Assertions.assertEquals(SizeStory.MEDIUM,story.getSize());

    }

    @Test
    public void changeSize_Throw_Exception_When_SizeIsTheSame(){
        Story story =
                new StoryImpl(1, "storyTitle",
                        "storyDescription", Priority.LOW, SizeStory.SMALL, StatusStory.NOT_DONE);

        Assertions.assertThrows(IllegalArgumentException.class,()-> story.changeSize(SizeStory.SMALL) );

    }





}
