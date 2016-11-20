package ru.bender.testdb.start;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.bender.testdb.impls.SQLiteDAO;
import ru.bender.testdb.interfaces.MP3Dao;
import ru.bender.testdb.objects.MP3;

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

    public static MP3 generateMP3(){
        MP3 mp3 = new MP3();
        mp3.setName("name" + (int)(Math.random() * 10));
        mp3.setAuthor("author" + (int)(Math.random() * 10));
        return mp3;
    }

    public static List<MP3> generateMP3List(int count) {
        List<MP3> mp3s = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            mp3s.add(generateMP3());
        }
        return mp3s;
    }

}
