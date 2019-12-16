package by.dziamidka;

import by.dziamidka.entity.Periodicals;
import by.dziamidka.entity.author.AuthorsMigration;
import by.dziamidka.entity.book.BookMigration;
import by.dziamidka.entity.bookInLibrary.BookInLibrary;
import by.dziamidka.entity.bookInLibrary.BookInLibraryMigration;
import by.dziamidka.entity.genre.GenreMigration;
import by.dziamidka.entity.library.LibraryMigration;
import by.dziamidka.entity.publisher.Publisher;
import by.dziamidka.entity.publisher.PublisherMigration;
import by.dziamidka.exception.DatabaseException;
import by.dziamidka.exception.XsdValidationException;
import by.dziamidka.service.JDBCService;
import by.dziamidka.service.XMLService;
import by.dziamidka.service.XsdValidatorService;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.log4j.Logger;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Test {

    private static final Logger _logger = Logger.getLogger(Test.class);

    private static final String _xmlPath = "src/main/resources/Periodicals.xml";

    private static final String _xsdPath = "src/main/resources/Periodicals.xsd";

    private static XsdValidatorService _xsdValidator = XsdValidatorService.getInstance();

    private static JDBCService jdbcService = JDBCService.getInstance();

    private static XMLService xmlService = XMLService.getInstance();

    private static final XmlMapper xmlMapper = new XmlMapper();

    private static final String DBUser = "test";
    private  static  final String DBPassword = "2281488";
    private  static final  String DBUrl = "jdbc:sqlserver://localhost;DatabaseName=Periodicals";

    private static LibraryMigration libraryMigration = LibraryMigration.getInstance();
    private static PublisherMigration publisherMigration = PublisherMigration.getInstance();
    private static GenreMigration genreMigration = GenreMigration.getInstance();
    private static AuthorsMigration authorsMigration = AuthorsMigration.getInstance();
    private static BookMigration bookMigration = BookMigration.getInstance();
    private static BookInLibraryMigration bookInLibraryMigration = BookInLibraryMigration.getInstance();

    public static void main(String[] args)
    {
        System.out.println("Start");
        _logger.debug("Start");
        try
        {
            _xsdValidator.Validate(new File(_xmlPath), new File(_xsdPath));
            jdbcService.init(DBUrl, DBUser, DBPassword);
            Periodicals periodicalsData;
            try {
                String xml = xmlService.InputStreamToString(new FileInputStream(_xmlPath));
                periodicalsData = xmlMapper.readValue(xml, Periodicals.class);
                libraryMigration.Migrate(periodicalsData.getLibraries());
                genreMigration.Migrate(periodicalsData.getGenres());
                publisherMigration.Migrate(periodicalsData.getPublishers());
                authorsMigration.Migrate(periodicalsData.getAuthors());
                bookMigration.Migrate(periodicalsData.getBooks());
                bookInLibraryMigration.Migrate(periodicalsData.getBookInLibrary());
            } catch (IOException e) {
                _logger.error(e.getMessage());
                System.out.println("Can't read data");
            }
            System.out.println("successful validation");
            _logger.debug("successful validation");
        }
        catch (XsdValidationException e)
        {
            System.out.println("Validation exception");
            _logger.error("xsd schema exception");
        }
        catch (DatabaseException e)
        {
            System.out.println("Database Exception");
            _logger.error(e.getMessage());
        }
        _logger.debug("End");
        System.out.println("Finish");
    }
}
