package ru.bender.testdb.interfaces;

import ru.bender.testdb.objects.MP3;

import java.util.List;

public interface MP3Dao {

    int insert(MP3 mp3);

    void delete(MP3 mp3);

    void deleteByID(int ID);

    int[] insertMP3List(List<MP3> mp3s);

    MP3 getMP3ById(int id);

    List<MP3> getMP3ListByName(String name);

    List<MP3> getMP3ListByAuthor(String author);

}
