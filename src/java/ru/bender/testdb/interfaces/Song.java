package ru.bender.testdb.interfaces;

public interface Song {
    String getName();

    int getID();

    void setID(int ID);

    void setName(String name);

    Author getAuthor();

    void setAuthor(Author author);

    String getAuthorName();
}
