package ru.bender.testdb.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import ru.bender.testdb.interfaces.Author;
import ru.bender.testdb.interfaces.AuthorDAO;

import javax.sql.DataSource;

@Component
public class SQLiteAuthorDAO implements AuthorDAO {

    NamedParameterJdbcTemplate jdbcTemplate;
    SimpleJdbcInsert authorInsert;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        authorInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("author")
                .usingColumns("name")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public int insert(Author author) {
        int id = authorInsert.executeAndReturnKeyHolder(getMapSqlParameterSource(author))
                .getKey()
                .intValue();
        author.setID(id);
        return id;
    }

    @Override
    public int getAuthorIdByName(String name) {
        return 0;
    }

    private MapSqlParameterSource getMapSqlParameterSource(Author author) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", author.getID());
        params.addValue("name", author.getName());
        return params;
    }
}
