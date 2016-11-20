package ru.bender.testdb.objects;

import ru.bender.testdb.interfaces.Author;
import ru.bender.testdb.interfaces.Song;

public class SongImpl implements Song {

    private int ID;

    private String name;

    private Author author;

    public SongImpl() {

    }

    public SongImpl(String name, Author author) {
        this.name = name;
        this.author = author;
    }

    public SongImpl(String name, String  author_name) {
        this.name = name;
        this.author = new AuthorImpl(author_name);
    }



    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public void setName(String name) {
        this.name = name.trim();
    }

    @Override
    public Author getAuthor() {
        return author;
    }

    @Override
    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String getAuthorName() {
        return author.getName();
    }

    @Override
    public String toString() {
        return ID + " " + name + " - " + getAuthorName();
    }
}
