package by.dziamidka.entity.author;

import by.dziamidka.exception.DatabaseException;
import by.dziamidka.service.JDBCService;
import by.dziamidka.service.MigrationService;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class AuthorsMigration implements MigrationService<Author> {
    private static class  MigrationSingleton
    {
        static final AuthorsMigration Instance = new AuthorsMigration();
    }
    public static AuthorsMigration getInstance()
    {
        return AuthorsMigration.MigrationSingleton.Instance;
    }

    private static final String TABLE = "Authors";
    private static final String INSERT_QUERY = "INSERT INTO " + TABLE
            + " (AuthorId, name, surname) VALUES (?, ?, ?)";

    private static final Logger _logger = Logger.getLogger(AuthorsMigration.class);
    private JDBCService jdbcService = JDBCService.getInstance();

    private AuthorsMigration() {
    }

    @Override
    public int Migrate(List<Author> data) {
        AtomicInteger counter = new AtomicInteger();
        for (Author author : data) {
            try {
                SaveAuthor(author);
                counter.getAndIncrement();
            }
            catch (DatabaseException e) {
                _logger.debug(e);
            }
        }
        _logger.debug("Total authors: " + data.size() + ", successful migrated: " + counter);
        System.out.println(counter + " authors migrated");
        return counter.get();
    }

    private void SaveAuthor(Author author) throws DatabaseException {
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = jdbcService.getConnection().prepareStatement(INSERT_QUERY);
            preparedStatement.setInt(1, author.getId());
            preparedStatement.setString(2, author.getName());
            preparedStatement.setString(3, author.getSurname());
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
