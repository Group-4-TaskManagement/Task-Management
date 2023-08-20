package models;

import com.group4.www.models.CommentImpl;
import com.group4.www.models.contracts.Comment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CommentImplTest {

    public  static final String AUTHOR_NAME = "author";
    public  static final String MESSAGE = "message";

    @Test
    public  void constructor_CreateNewComment_When_ArgumentsIsValid(){
        Comment comment = new CommentImpl(AUTHOR_NAME, MESSAGE);
        Assertions.assertNotNull(comment);
    }

    @Test
    public  void constructor_CreateNewComment_Throw_Exception_When_AuthorNotValid(){
        Assertions.assertThrows(IllegalArgumentException.class,()-> new CommentImpl("", MESSAGE));
    }



    @Test
    public  void constructor_CreateNewComment_Throw_Exception_When_MessageNotValid(){
        Assertions.assertThrows(IllegalArgumentException.class,()-> new CommentImpl(AUTHOR_NAME, ""));
    }

    @Test
    public void getMessage(){
        Comment comment = new CommentImpl(AUTHOR_NAME, MESSAGE);
        Assertions.assertEquals(MESSAGE,comment.getMessage());
    }

    @Test
    public void getAuthor(){
        Comment comment = new CommentImpl(AUTHOR_NAME, MESSAGE);
        Assertions.assertEquals(AUTHOR_NAME,comment.getAuthor());
    }
}
