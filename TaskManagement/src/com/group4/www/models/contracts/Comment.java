package com.group4.www.models.contracts;

public interface Comment extends Printable{

    String getAuthor();

    String getMessage();

    int getId();

}
