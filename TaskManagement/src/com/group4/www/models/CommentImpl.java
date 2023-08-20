package com.group4.www.models;

import com.group4.www.models.contracts.Comment;
import com.group4.www.models.contracts.Identifiable;
import com.group4.www.models.contracts.Member;

import java.util.Objects;

public class CommentImpl implements Comment {
    public static final String MESSAGE_ERROR = "Тhe message can not be empty";
    public static final String AUTHOR_ERROR = "Тhe author can not be empty";

    private String author;
    private String message;

    public CommentImpl(String author, String message) {
        setAuthor(author);
        setMessage(message);
    }

    public String getAuthor() {
        return author;
    }

    public String getMessage() {
        return message;
    }

    private void setAuthor(String author) {
        if(author.trim().isEmpty()){
            throw new IllegalArgumentException(AUTHOR_ERROR);
        }
        this.author = author;
    }

    private void setMessage(String message) {
        if(message.trim().isEmpty()){
            throw new IllegalArgumentException(MESSAGE_ERROR);
        }
        this.message = message;
    }

    @Override
    public String getAsString() {
        return String.format("Author:%s \n %s",getAuthor(),getMessage());
    }
}
