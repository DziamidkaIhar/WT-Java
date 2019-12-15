package by.dziamidka.entity;

import by.dziamidka.entity.author.Author;
import by.dziamidka.entity.book.Book;
import by.dziamidka.entity.genre.Genre;
import by.dziamidka.entity.library.Library;
import by.dziamidka.entity.publisher.Publisher;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JacksonXmlRootElement(localName = "HorseRacingData")
public class Periodicals {

    @JacksonXmlElementWrapper(localName = "Libraries")
    @JacksonXmlProperty(localName = "library")
    private List<Library> libraries;

    @JacksonXmlElementWrapper(localName = "Authors")
    @JacksonXmlProperty(localName = "author")
    private List<Author> authors;

    @JacksonXmlElementWrapper(localName = "Genres")
    @JacksonXmlProperty(localName = "genre")
    private List<Genre> genres;

    @JacksonXmlElementWrapper(localName = "Publishers")
    @JacksonXmlProperty(localName = "publisher")
    private List<Publisher> publishers;

    @JacksonXmlElementWrapper(localName = "Books")
    @JacksonXmlProperty(localName = "book")
    private List<Book> books;
}
