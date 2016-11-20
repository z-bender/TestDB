package ru.bender.testdb.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import ru.bender.testdb.interfaces.Author;
import ru.bender.testdb.interfaces.AuthorDAO;

import javax.sql.DataSource;

@Component
public class SQLiteAuthorDAO implements AuthorDAO {

    NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public int insert(Author author) {
        return 0;
    }

    @Override
    public int getAuthorIdByName(String name) {
        return 0;
    }
}
