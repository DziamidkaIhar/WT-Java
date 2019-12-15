package by.dziamidka.entity.library;

import by.dziamidka.entity.Entity;
import by.dziamidka.entity.book.Book;

import java.time.LocalDate;
import java.util.ArrayList;

public class Library extends Entity {
    private ArrayList<Book> books;
    private String creationDate;
    private String title;

    public  Library(){
    }

    public Library(String title) {
        creationDate = LocalDate.now().toString();
        books = new ArrayList<Book>();
        this.title = title;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
