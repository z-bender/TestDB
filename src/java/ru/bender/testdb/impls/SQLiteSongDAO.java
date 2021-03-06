package ru.bender.testdb.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.bender.testdb.annotations.Testpointcut;
import ru.bender.testdb.interfaces.AuthorDAO;
import ru.bender.testdb.interfaces.Song;
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
    private AuthorDAO authorDAO;
    private SQLiteSongDAO thisDAO;


/*    @Autowired
    @Qualifier("songDAO")
    public void setThisDAO(SQLiteSongDAO thisDAO) {
        this.thisDAO = thisDAO;
    }*/

    @Autowired
    public void setAuthorDAO(AuthorDAO authorDAO) {
        this.authorDAO = authorDAO;
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {

        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.insertSong = new SimpleJdbcInsert(dataSource).withTableName("song").usingColumns("name", "id_author").usingGeneratedKeyColumns("id");
        this.dataSource = dataSource;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
//    @Testpointcut
    public int insert(Song song) {
//        System.out.println("song_insert - " + TransactionSynchronizationManager.isActualTransactionActive());
        int id;
        int id_author = song.getAuthor().getID();

        if (id_author == 0) {
            id_author = authorDAO.getIdOrAddIfAuthorNotFound(song.getAuthorName());
            song.getAuthor().setID(id_author);
        }

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", song.getName());
        params.addValue("id_author", id_author);
        id = insertSong.executeAndReturnKeyHolder(params).getKey().intValue();
        song.setID(id);
        testAOP();
//        authorDAO.insert("test" + (Math.random() * 100));
        return id;
    }

    @Testpointcut
    public void testAOP() {
        System.out.println("test aop");
    }

    @Override
    public void delete(Song song) {
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
    public int insertSongList(List<SongImpl> songs) {
//        String sql = "INSERT INTO song (name, author) VALUES (:name, :author)";
//        SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(songs.toArray());
//        int[] updateCounts = jdbcTemplate.batchUpdate(sql, batch);
        songs.forEach(song -> insert(song));
        return songs.size();
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
