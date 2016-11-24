package ru.bender.testdb.start;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.bender.testdb.impls.SQLiteAuthorDAO;
import ru.bender.testdb.impls.SQLiteSongDAO;
import ru.bender.testdb.interfaces.AuthorDAO;
import ru.bender.testdb.interfaces.SongDao;
import ru.bender.testdb.objects.SongImpl;

import java.util.ArrayList;
import java.util.List;

public class RunMe {
    public static void main(String[] args) {
//        ApplicationContext context = new ClassPathXmlApplicationContext("app-config.xml");
        ApplicationContext context = new AnnotationConfigApplicationContext(DBConfig.class);


//        mp3.setName("gh");
//        mp3.setAuthor("dh");

        SongDao songDao = (SQLiteSongDAO) context.getBean("songDAO");
        AuthorDAO authorDao = (SQLiteAuthorDAO) context.getBean("authorDAO");
//        songDao.delete(mp3);
//        int id = songDao.insert(mp3);
//        System.out.println(id);
//        mp3 = songDao.getMP3ById(3);
//        System.out.println(songDao.getMP3ListByName("Rape me"));
//        songDao.deleteByID(5);
//        System.out.println(Arrays.toString(songDao.insertSongList(generateMP3List(4))));

//        authorDao.insert(new AuthorImpl("Metallica"));
//        authorDao.insert(new AuthorImpl("Nirvana"));
//        authorDao.insert(new AuthorImpl("Red Hot Chilly Peppers"));

        songDao.insert(generateMP3());
        System.out.println("Its END...");
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
