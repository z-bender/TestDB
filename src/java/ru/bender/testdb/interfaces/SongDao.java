package ru.bender.testdb.interfaces;

import ru.bender.testdb.objects.SongImpl;

import java.util.List;

public interface SongDao {

    int insert(Song song);

    void delete(Song song);

    void deleteByID(int ID);

    int insertSongList(List<SongImpl> songs);

    SongImpl getMP3ById(int id);

    List<SongImpl> getMP3ListByName(String name);

    List<SongImpl> getMP3ListByAuthor(String author);

}
