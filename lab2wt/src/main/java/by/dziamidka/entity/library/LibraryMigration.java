package by.dziamidka.entity.library;

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

public class LibraryMigration implements MigrationService<Library> {

    private static class  MigrationSingleton
    {
        static final LibraryMigration Instance = new LibraryMigration();
    }
    public static LibraryMigration getInstance()
    {
        return LibraryMigration.MigrationSingleton.Instance;
    }

    private static final String TABLE = "Libraries";
    private static final String INSERT_QUERY = "INSERT INTO " + TABLE
            + " (LibraryId, Title, CreationDate) VALUES (?, ?, ?)";

    private static final Logger _logger = Logger.getLogger(LibraryMigration.class);
    private JDBCService jdbcService = JDBCService.getInstance();

    private LibraryMigration() {
    }

    @Override
    public int Migrate(List<Library> data) {
        AtomicInteger counter = new AtomicInteger();
        for (Library library : data) {
            try {
                SaveLibrary(library);
                counter.getAndIncrement();
            }
            catch (MigrationException | DatabaseException e) {
                _logger.debug(e);
            }
        }
        _logger.debug("Total libraries: " + data.size() + ", successful migrated: " + counter);
        System.out.println(counter + " libraries migrated");
        return counter.get();
    }

    private void SaveLibrary(Library library) throws MigrationException, DatabaseException {
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = jdbcService.getConnection().prepareStatement(INSERT_QUERY);
            preparedStatement.setInt(1, library.getId());
            preparedStatement.setString(2, library.getTitle());
            preparedStatement.setString(3, library.getCreationDate());
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
