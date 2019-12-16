package by.dziamidka.entity.publisher;

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

public class PublisherMigration implements MigrationService<Publisher> {

    private static class  MigrationSingleton
    {
        static final PublisherMigration Instance = new PublisherMigration();
    }
    public static PublisherMigration getInstance()
    {
        return PublisherMigration.MigrationSingleton.Instance;
    }

    private static final String TABLE = "Publishers";
    private static final String INSERT_QUERY = "INSERT INTO " + TABLE
            + " (PublisherId, title) VALUES (?, ?)";

    private static final Logger _logger = Logger.getLogger(PublisherMigration.class);
    private JDBCService jdbcService = JDBCService.getInstance();

    private PublisherMigration() {
    }

    @Override
    public int Migrate(List<Publisher> data) {
        AtomicInteger counter = new AtomicInteger();
        for (Publisher publisher : data) {
            try {
                SavePublisher(publisher);
                counter.getAndIncrement();
            }
            catch (DatabaseException e) {
                _logger.debug(e);
            }
        }
        _logger.debug("Total publishers: " + data.size() + ", successful migrated: " + counter);
        System.out.println(counter + " publishers migrated");
        return counter.get();
    }

    private void SavePublisher(Publisher publisher) throws DatabaseException {
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = jdbcService.getConnection().prepareStatement(INSERT_QUERY);
            preparedStatement.setInt(1, publisher.getId());
            preparedStatement.setString(2, publisher.getTitle());
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
