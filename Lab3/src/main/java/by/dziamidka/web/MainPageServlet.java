package by.dziamidka.web;

import by.dziamidka.MyException;
import by.dziamidka.entity.author.Author;
import by.dziamidka.entity.book.Book;
import by.dziamidka.entity.genre.Genre;
import by.dziamidka.entity.library.Library;
import by.dziamidka.entity.publisher.Publisher;
import by.dziamidka.parser.*;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.SAXParser;
import java.io.IOException;
import java.util.List;

public class MainPageServlet extends HttpServlet {
    private static final String xmlPath = "src/main/resources/Periodicals.xml";

    private static final Logger logger = Logger.getLogger(AuthorDomParser.class);

    private static final AuthorDomParser authorDomParser = AuthorDomParser.getInstance();
    private static final GenreSaxParser genreSaxParser = GenreSaxParser.getInstance();
    private static final LibraryStAXParser libraryStAXParser = LibraryStAXParser.getInstance();
    private static final BookDomParser bookDomParser = BookDomParser.getInstance();
    private static final PublisherDomParser publisherDomParser = PublisherDomParser.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("title", "MSP");
        try {
            List<Author> authors = authorDomParser.getDataFromXmlPath(xmlPath);
            List<Book> books = bookDomParser.getDataFromXmlPath(xmlPath);
            List<Genre> genres = genreSaxParser.getDataFromXmlPath(xmlPath);
            List<Library> libraries = libraryStAXParser.getDataFromXmlPath(xmlPath);
            List<Publisher> publishers = publisherDomParser.getDataFromXmlPath(xmlPath);
            req.setAttribute("authors", authors);
            req.setAttribute("books", books);
            req.setAttribute("genres", genres);
            req.setAttribute("libraries", libraries);
            req.setAttribute("publishers", publishers);
            req.getRequestDispatcher("/Pages/mainPag.jsp").forward(req,resp);
        } catch (MyException e) {
            logger.error("can't read xml: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
