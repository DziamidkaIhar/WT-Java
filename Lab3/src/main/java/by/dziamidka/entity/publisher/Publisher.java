package by.dziamidka.entity.publisher;

import by.dziamidka.entity.Entity;
import by.dziamidka.entity.book.Book;

import java.util.List;

public class Publisher extends Entity {
    private List<Book> books;
    private String title;

    public Publisher() {
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
