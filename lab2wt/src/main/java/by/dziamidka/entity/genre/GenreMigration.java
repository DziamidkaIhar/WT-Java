package by.dziamidka.entity.genre;


import by.dziamidka.exception.DatabaseException;
import by.dziamidka.exception.MigrationException;
import by.dziamidka.service.JDBCService;
import by.dziamidka.service.MigrationService;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class GenreMigration implements MigrationService<Genre> {

    private static class  MigrationSingleton
    {
        static final GenreMigration Instance = new GenreMigration();
    }
    public static GenreMigration getInstance()
    {
        return GenreMigration.MigrationSingleton.Instance;
    }

    private static final String TABLE = "Genres";
    private static final String INSERT_QUERY = "INSERT INTO " + TABLE
            + " (GenreId, title) VALUES (?, ?)";

    private static final Logger _logger = Logger.getLogger(GenreMigration.class);
    private JDBCService jdbcService = JDBCService.getInstance();

    private GenreMigration() {
    }

    @Override
    public int Migrate(List<Genre> data) {
        AtomicInteger counter = new AtomicInteger();
        for (Genre genre : data) {
            try {
                SaveGenre(genre);
                counter.getAndIncrement();
            }
            catch (MigrationException | DatabaseException e) {
                _logger.debug(e);
            }
        }
        _logger.debug("Total genres: " + data.size() + ", successful migrated: " + counter);
        System.out.println(counter + " genres migrated");
        return counter.get();
    }

    private void SaveGenre(Genre genre) throws MigrationException, DatabaseException {
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = jdbcService.getConnection().prepareStatement(INSERT_QUERY);
            preparedStatement.setInt(1, genre.getId());
            preparedStatement.setString(2, genre.getTitle());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            _logger.error(e);
            throw new DatabaseException(e.getMessage());
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException e) {
                _logger.error(e);
            }
        }
    }
}
