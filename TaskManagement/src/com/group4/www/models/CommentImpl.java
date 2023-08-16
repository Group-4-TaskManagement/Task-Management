package com.group4.www.models;

import com.group4.www.models.contracts.Comment;
import com.group4.www.models.contracts.Identifiable;

import java.util.Objects;

public class CommentImpl implements Comment {

    private String author;
    private String message;


    public CommentImpl(String author, String message) {
        this.author = author;
        this.message = message;
    }


    private void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentImpl comment = (CommentImpl) o;
        return Objects.equals(author, comment.author) && Objects.equals(message, comment.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, message);
    }


    @Override
    public String getAsString() {
        return String.format("Author:%s \n %s",getAuthor(),getMessage());
    }
}
