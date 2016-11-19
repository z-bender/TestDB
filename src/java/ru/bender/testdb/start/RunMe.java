package ru.bender.testdb.start;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.bender.testdb.impls.SQLiteDAO;
import ru.bender.testdb.interfaces.MP3Dao;
import ru.bender.testdb.objects.MP3;

public class RunMe {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("app-config.xml");

        MP3 mp3 = new MP3();
        mp3.setName("name" + Math.random());
        mp3.setAuthor("name" + Math.random());
//        mp3.setName("gh");
//        mp3.setAuthor("dh");

        MP3Dao mp3Dao = (SQLiteDAO) context.getBean("sqliteDAO");
//        mp3Dao.delete(mp3);
        int id = mp3Dao.insert(mp3);
        System.out.println(id);
//        mp3 = mp3Dao.getMP3ById(3);
//        System.out.println(mp3Dao.getMP3ListByName("Rape me"));
//        mp3Dao.deleteByID(5);
    }
}
