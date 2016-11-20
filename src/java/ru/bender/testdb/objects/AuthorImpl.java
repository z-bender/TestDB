package ru.bender.testdb.objects;

import ru.bender.testdb.interfaces.Author;

public class AuthorImpl implements Author {

    private int ID;

    private String name;

    public AuthorImpl(String name) {
        this.name = name;
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
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name.trim();
    }
}
