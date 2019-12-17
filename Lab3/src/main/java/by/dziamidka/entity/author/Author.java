package by.dziamidka.entity.author;

import by.dziamidka.entity.Entity;
import by.dziamidka.entity.book.Book;

import java.util.List;

public class Author extends Entity{
    private String name;
    private String surname;
    private List<Book> books;

    public Author()
    {
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public List<Book> getBooks() {
        return books;
    }
}
