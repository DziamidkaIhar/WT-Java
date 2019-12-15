package by.dziamidka.entity.publisher;

import by.dziamidka.entity.Entity;
import by.dziamidka.entity.book.Book;

import java.util.ArrayList;

public class Publisher extends Entity {
    private ArrayList<Book> books;
    private String title;

    public Publisher() {
    }

    public Publisher(String title) {
        books = new ArrayList<Book>();
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }
}
