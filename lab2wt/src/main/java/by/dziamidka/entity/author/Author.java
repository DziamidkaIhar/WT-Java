package by.dziamidka.entity.author;

import by.dziamidka.entity.Entity;
import by.dziamidka.entity.book.Book;

import java.util.ArrayList;

public class Author extends Entity {
    private String name;
    private String surname;
    private ArrayList<Book> books;

    public Author() {
    }

    public Author(String name, String surname) {
        this.name = name;
        this.surname = surname;
        books = new ArrayList<Book>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }
}
