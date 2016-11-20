package ru.bender.testdb.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import ru.bender.testdb.interfaces.MP3Dao;
import ru.bender.testdb.objects.MP3;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component("sqliteDAO")
public class SQLiteDAO implements MP3Dao{

    private NamedParameterJdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert insertMP3;
    private DataSource dataSource;

    @Autowired
    public void setDataSource(DataSource dataSource) {

        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.insertMP3 = new SimpleJdbcInsert(dataSource).withTableName("music").usingColumns("name", "author").usingGeneratedKeyColumns("id");
        this.dataSource = dataSource;
    }

    @Override
    public int insert(MP3 mp3) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", mp3.getName());
        params.addValue("author", mp3.getAuthor());
        return insertMP3.executeAndReturnKeyHolder(params).getKey().intValue();
//        return insertMP3.executeAndReturnKeyHolder(params).getKey().intValue();
    }

    @Override
    public void delete(MP3 mp3) {
        String sql = "DELETE FROM music WHERE name = :name AND  author = :author;";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("name", mp3.getName());
        parameterSource.addValue("author", mp3.getAuthor());
        jdbcTemplate.update(sql, parameterSource);
    }

    @Override
    public void deleteByID(int ID) {
//        String sql = "DELETE FROM music WHERE id = ?;";
//        jdbcTemplate.update(sql, new Object[]{ID});
    }

    @Override
    public int[] insertMP3List(List<MP3> mp3s) {
        String sql = "INSERT INTO music (name, author) VALUES (:name, :author)";
        SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(mp3s.toArray());
        int[] updateCounts = jdbcTemplate.batchUpdate(sql, batch);
        return updateCounts;
    }


    @Override
    public MP3 getMP3ById(int id) {
//        String sql = "SELECT id, name, author FROM music WHERE id = ?;";
//        MP3 mp3 = (MP3)jdbcTemplate.queryForObject(sql, new Object[]{id}, new MP3RowMapper());
        return null;
    }

    @Override
    public List<MP3> getMP3ListByName(String name) {
        String sql = "SELECT id, name, author FROM music WHERE name = :name;";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("name", name);
        List<MP3> mp3s = jdbcTemplate.query(sql, parameters, new MP3RowMapper());
        return mp3s;
    }

    @Override
    public List<MP3> getMP3ListByAuthor(String author) {
//        String sql = "SELECT id, name, author FROM music WHERE author = ?;";
//        List<MP3> mp3s = jdbcTemplate.query(sql, new Object[]{author}, new MP3RowMapper());
//        List<MP3> mp3s = jdbcTemplate.query(sql, new Object[]{author}, new MP3RowMapper());
        return null;
    }

    private static final class MP3RowMapper implements RowMapper<MP3> {
        @Override
        public MP3 mapRow(ResultSet resultSet, int i) throws SQLException {
            MP3 mp3 = new MP3();
            mp3.setID(resultSet.getInt("id"));
            mp3.setName(resultSet.getString("name"));
            mp3.setAuthor(resultSet.getString("author"));
            return mp3;
        }
    }
}
