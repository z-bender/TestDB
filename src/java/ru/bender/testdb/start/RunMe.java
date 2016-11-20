package ru.bender.testdb.start;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.bender.testdb.impls.SQLiteDAO;
import ru.bender.testdb.interfaces.MP3Dao;
import ru.bender.testdb.objects.SongImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RunMe {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("app-config.xml");


//        mp3.setName("gh");
//        mp3.setAuthor("dh");

        MP3Dao mp3Dao = (SQLiteDAO) context.getBean("sqliteDAO");
//        mp3Dao.delete(mp3);
//        int id = mp3Dao.insert(mp3);
//        System.out.println(id);
//        mp3 = mp3Dao.getMP3ById(3);
//        System.out.println(mp3Dao.getMP3ListByName("Rape me"));
//        mp3Dao.deleteByID(5);
        System.out.println(Arrays.toString(mp3Dao.insertMP3List(generateMP3List(4))));
    }

    public static SongImpl generateMP3(){
        SongImpl song = new SongImpl(
                "name" + (int)(Math.random() * 10),
                "author" + (int)(Math.random() * 10)
        );
        return song;
    }

    public static List<SongImpl> generateMP3List(int count) {
        List<SongImpl> songs = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            songs.add(generateMP3());
        }
        return songs;
    }

}
