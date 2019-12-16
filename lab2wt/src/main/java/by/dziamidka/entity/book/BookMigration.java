package by.dziamidka.entity.book;

import by.dziamidka.exception.DatabaseException;
import by.dziamidka.service.JDBCService;
import by.dziamidka.service.MigrationService;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class BookMigration implements MigrationService<Book> {

    private static class  MigrationSingleton
    {
        static final BookMigration Instance = new BookMigration();
    }
    public static BookMigration getInstance()
    {
        return BookMigration.MigrationSingleton.Instance;
    }

    private static final String TABLE = "Books";
    private static final String INSERT_QUERY = "INSERT INTO " + TABLE
            + " (BookId, AuthorId, YearOfPublication, PublisherId, GenreId) VALUES (?, ?, ?, ?, ?)";

    private static final Logger _logger = Logger.getLogger(BookMigration.class);
    private JDBCService jdbcService = JDBCService.getInstance();

    private BookMigration() {
    }

    @Override
    public int Migrate(List<Book> data) {
        AtomicInteger counter = new AtomicInteger();
        for (Book book : data) {
            try {
                SaveBook(book);
                counter.getAndIncrement();
            }
            catch (DatabaseException e) {
                _logger.debug(e);
            }
        }
        _logger.debug("Total books: " + data.size() + ", successful migrated: " + counter);
        System.out.println(counter + " books migrated");
        return counter.get();
    }

    private void SaveBook(Book book) throws DatabaseException {
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = jdbcService.getConnection().prepareStatement(INSERT_QUERY);
            preparedStatement.setInt(1, book.getId());
            preparedStatement.setInt(2, book.getAuthorId());
            preparedStatement.setString(3, book.getYearOfPublication());
            preparedStatement.setInt(4, book.getPublisherId());
            preparedStatement.setInt(5, book.getGenreId());
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
