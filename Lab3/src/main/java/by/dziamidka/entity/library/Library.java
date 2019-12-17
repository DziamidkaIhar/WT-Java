package by.dziamidka.entity.library;

import by.dziamidka.entity.Entity;
import by.dziamidka.entity.book.Book;

import java.util.List;

public class Library extends Entity {
    private List<Book> books;
    private String creationDate;
    private String title;

    public Library() {
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Book> getBooks() {
        return books;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public String getTitle() {
        return title;
    }
}
