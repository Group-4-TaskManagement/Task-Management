package models;

import com.group4.www.models.CommentImpl;
import com.group4.www.models.contracts.Comment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CommentImplTest {

    @Test
    public  void constructor_CreateNewComment_When_ArgumentsIsValid(){
        Comment comment = new CommentImpl("author", "message");
        Assertions.assertNotNull(comment);
    }

    @Test
    public  void constructor_CreateNewComment_Throw_Exception_When_AuthorNotValid(){
        Assertions.assertThrows(IllegalArgumentException.class,()-> new CommentImpl("", "message"));
    }



    @Test
    public  void constructor_CreateNewComment_Throw_Exception_When_MessageNotValid(){
        Assertions.assertThrows(IllegalArgumentException.class,()-> new CommentImpl("author", ""));
    }

    @Test
    public void getMessage(){
        Comment comment = new CommentImpl("author", "message");
        Assertions.assertEquals("message",comment.getMessage());
    }

    @Test
    public void getAuthor(){
        Comment comment = new CommentImpl("author", "message");
        Assertions.assertEquals("author",comment.getAuthor());
    }
}
