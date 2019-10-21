package by.dziamidka.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Publisher implements Serializable {
    private ArrayList<Book> books;
    private String title;

    public Publisher() {
    }

    public Publisher(String title) {
        books = new ArrayList<Book>();
        this.title = title;
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "books=" + books +
                ", title='" + title + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publisher publisher = (Publisher) o;
        return Objects.equals(books, publisher.books) &&
                Objects.equals(title, publisher.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(books, title);
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
