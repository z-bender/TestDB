package ru.bender.testdb.interfaces;

import ru.bender.testdb.objects.SongImpl;

import java.util.List;

public interface MP3Dao {

    int insert(SongImpl song);

    void delete(SongImpl song);

    void deleteByID(int ID);

    int[] insertMP3List(List<SongImpl> songs);

    SongImpl getMP3ById(int id);

    List<SongImpl> getMP3ListByName(String name);

    List<SongImpl> getMP3ListByAuthor(String author);

}
