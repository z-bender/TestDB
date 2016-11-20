package ru.bender.testdb.objects;

public class Song {

    private int ID;

    private String name;

    private String author;

    public String getName() {
        return name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author.trim();
    }

    @Override
    public String toString() {
        return ID + " " + name + " - " + author;
    }
}
