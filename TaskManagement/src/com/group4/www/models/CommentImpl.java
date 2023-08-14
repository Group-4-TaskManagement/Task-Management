package com.group4.www.models;

import com.group4.www.models.contracts.Comment;
import com.group4.www.models.contracts.Identifiable;

import java.util.Objects;

public class CommentImpl implements Comment, Identifiable {

    private int id;
    private String author;
    private String message;


    public CommentImpl(int id,String author, String message) {
        //TODO check if author is a member
        this.id = id;
        this.author = author;
        this.message = message;
    }


    private void setAuthor(String author) {

        // TODO проверка за съществуващ автор в репозиторито;
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public String getMessage() {
        return message;
    }
    @Override
    public int getId() {
        return id;
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
