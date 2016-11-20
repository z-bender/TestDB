package ru.bender.testdb.interfaces;

import ru.bender.testdb.objects.Song;

import java.util.List;

public interface MP3Dao {

    int insert(Song song);

    void delete(Song song);

    void deleteByID(int ID);

    int[] insertMP3List(List<Song> songs);

    Song getMP3ById(int id);

    List<Song> getMP3ListByName(String name);

    List<Song> getMP3ListByAuthor(String author);

}
