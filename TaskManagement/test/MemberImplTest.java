import com.group4.www.models.MemberImpl;
import com.group4.www.models.contracts.Member;
import com.group4.www.models.enums.Priority;
import com.group4.www.models.enums.SizeStory;
import com.group4.www.models.enums.StatusStory;
import com.group4.www.models.tasks.StoryImpl;
import com.group4.www.models.tasks.contracts.Story;
import com.group4.www.models.tasks.contracts.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberImplTest {

    @Test
    public  void constructor_CreateNewMember_When_ArgumentIsValid(){
        Member member = new MemberImpl("Member");
        Assertions.assertNotNull(member);
    }

    @Test
    public  void constructor_CreateNewMember_Throw_Exception_When_NameNotValid(){

        Assertions.assertThrows(IllegalArgumentException.class, ()-> new MemberImpl(""));
    }

    @Test
    public  void addTask(){
        Member member = new MemberImpl("Member");
        Task task = new StoryImpl(1, "storyTitle",
                "storyDescription", Priority.LOW, SizeStory.SMALL, StatusStory.NOT_DONE);

        member.addTask(task);

        Assertions.assertEquals(1,member.getTasks().size());
    }

    @Test
    public  void addTask_Throw_Exception_WhenTaskExist(){
        Member member = new MemberImpl("Member");
        Task task = new StoryImpl(1, "storyTitle",
                "storyDescription", Priority.LOW, SizeStory.SMALL, StatusStory.NOT_DONE);

        Task task1 = new StoryImpl(2, "storyTitle",
                "storyDescription", Priority.LOW, SizeStory.SMALL, StatusStory.NOT_DONE);

        member.addTask(task);
        member.addTask(task1);

        Assertions.assertThrows(IllegalArgumentException.class,()-> member.addTask(task1));
    }

    @Test
    public  void removeTask(){
        Member member = new MemberImpl("Member");
        Task task = new StoryImpl(1, "storyTitle",
                "storyDescription", Priority.LOW, SizeStory.SMALL, StatusStory.NOT_DONE);
        member.addTask(task);
        member.removeTask(task);

        Assertions.assertEquals(0,member.getTasks().size());
    }

    @Test
    public  void removeTask_Throw_Exception_WhenTaskNotExist(){
        Member member = new MemberImpl("Member");
        Task task = new StoryImpl(1, "storyTitle",
                "storyDescription", Priority.LOW, SizeStory.SMALL, StatusStory.NOT_DONE);
        Task task1 = new StoryImpl(2, "storyTitle",
                "storyDescription", Priority.LOW, SizeStory.SMALL, StatusStory.NOT_DONE);
        member.addTask(task);

        Assertions.assertThrows(IllegalArgumentException.class,()-> member.removeTask(task1));
    }

    @Test
    public  void addActivityHistory(){
        Member member = new MemberImpl("Member");
        member.addActivityHistory("ActivityHistory");

        Assertions.assertEquals(1,member.getMemberActivity().size());
    }
}
