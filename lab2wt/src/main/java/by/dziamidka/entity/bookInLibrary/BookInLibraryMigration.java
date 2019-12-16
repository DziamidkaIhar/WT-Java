package by.dziamidka.entity.bookInLibrary;

import by.dziamidka.entity.Periodicals;
import by.dziamidka.exception.DatabaseException;
import by.dziamidka.service.JDBCService;
import by.dziamidka.service.MigrationService;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class BookInLibraryMigration implements MigrationService<BookInLibrary> {

    private static class  MigrationSingleton
    {
        static final BookInLibraryMigration Instance = new BookInLibraryMigration();
    }
    public static BookInLibraryMigration getInstance()
    {
        return BookInLibraryMigration.MigrationSingleton.Instance;
    }

    private static final String TABLE = "BooksInLibrary";
    private static final String INSERT_QUERY = "INSERT INTO " + TABLE
            + " (BookId,LibraryId) VALUES (?, ?)";

    private static final Logger _logger = Logger.getLogger(BookInLibraryMigration.class);
    private JDBCService jdbcService = JDBCService.getInstance();

    private BookInLibraryMigration() {
    }

    @Override
    public int Migrate(List<BookInLibrary> data) {
        AtomicInteger counter = new AtomicInteger();
        for (BookInLibrary book : data) {
            try {
                SaveBookInLibrary(book);
                counter.getAndIncrement();
            }
            catch (DatabaseException e) {
                _logger.debug(e);
            }
        }
        _logger.debug("Total books in Libraries: " + data.size() + ", successful migrated: " + counter);
        System.out.println(counter + " books in Library migrated");
        return counter.get();
    }

    private void SaveBookInLibrary(BookInLibrary book) throws DatabaseException {
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = jdbcService.getConnection().prepareStatement(INSERT_QUERY);
            preparedStatement.setInt(1, book.getBookId());
            preparedStatement.setInt(2, book.getLibraryId());
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
