package ru.bender.testdb.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import ru.bender.testdb.interfaces.SongDao;
import ru.bender.testdb.objects.SongImpl;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component("songDAO")
public class SQLiteSongDAO implements SongDao {

    private NamedParameterJdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert insertSong;
    private DataSource dataSource;

    @Autowired
    public void setDataSource(DataSource dataSource) {

        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.insertSong = new SimpleJdbcInsert(dataSource).withTableName("song").usingColumns("name", "id_author").usingGeneratedKeyColumns("id");
        this.dataSource = dataSource;
    }

    @Override
    public int insert(SongImpl song) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", song.getName());
        params.addValue("author", song.getAuthor());
        song.setID(insertSong.executeAndReturnKeyHolder(params).getKey().intValue());
        return song.getID();
//        return insertSong.executeAndReturnKeyHolder(params).getKey().intValue();
    }

    @Override
    public void delete(SongImpl song) {
        String sql = "DELETE FROM song WHERE name = :name AND  author = :author;";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("name", song.getName());
        parameterSource.addValue("author", song.getAuthor());
        jdbcTemplate.update(sql, parameterSource);
    }

    @Override
    public void deleteByID(int ID) {
//        String sql = "DELETE FROM song WHERE id = ?;";
//        jdbcTemplate.update(sql, new Object[]{ID});
    }

    @Override
    public int[] insertMP3List(List<SongImpl> songs) {
        String sql = "INSERT INTO song (name, author) VALUES (:name, :author)";
        SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(songs.toArray());
        int[] updateCounts = jdbcTemplate.batchUpdate(sql, batch);
        return updateCounts;
    }


    @Override
    public SongImpl getMP3ById(int id) {
//        String sql = "SELECT id, name, author FROM song WHERE id = ?;";
//        SongImpl mp3 = (SongImpl)jdbcTemplate.queryForObject(sql, new Object[]{id}, new MP3RowMapper());
        return null;
    }

    @Override
    public List<SongImpl> getMP3ListByName(String name) {
        String sql = "SELECT id, name, author FROM song WHERE name = :name;";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("name", name);
        List<SongImpl> songs = jdbcTemplate.query(sql, parameters, new MP3RowMapper());
        return songs;
    }

    @Override
    public List<SongImpl> getMP3ListByAuthor(String author) {
//        String sql = "SELECT id, name, author FROM song WHERE author = ?;";
//        List<SongImpl> mp3s = jdbcTemplate.query(sql, new Object[]{author}, new MP3RowMapper());
//        List<SongImpl> mp3s = jdbcTemplate.query(sql, new Object[]{author}, new MP3RowMapper());
        return null;
    }

    private static final class MP3RowMapper implements RowMapper<SongImpl> {
        @Override
        public SongImpl mapRow(ResultSet resultSet, int i) throws SQLException {
//            SongImpl song = new SongImpl();
//            song.setID(resultSet.getInt("id"));
//            song.setName(resultSet.getString("name"));
//            song.setAuthor(resultSet.getString("author"));
//            return song;
            return null;
        }
    }
}
