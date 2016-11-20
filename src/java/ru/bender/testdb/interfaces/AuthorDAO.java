package ru.bender.testdb.interfaces;

public interface AuthorDAO {

    int insert(Author author);

    int insert(String name);

    /**
     *
     * @param name
     * @return -1 if author not found
     */
    int getAuthorIdByName(String name);

    int getAuthorIdOrAdd(String name);

}
