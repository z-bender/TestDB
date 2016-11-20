package ru.bender.testdb.interfaces;

public interface AuthorDAO {

    int insert(Author author);

    /**
     *
     * @param name
     * @return -1 if author not found
     */
    int getAuthorIdByName(String name);

}
