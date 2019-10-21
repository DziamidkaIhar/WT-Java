package by.dziamidka.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

public class Library implements Serializable {

    private ArrayList<Book> books;
    private LocalDate creationDate;
    private String title;

    public  Library(){
    }

    public Library(String title) {
        creationDate = LocalDate.now();
        books = new ArrayList<Book>();
        this.title = title;
    }

    @Override
    public String toString() {
        return "Library{" +
                "books=" + books +
                ", creationDate=" + creationDate +
                ", title='" + title + '\'' +
                '}';
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Library library = (Library) o;
        return Objects.equals(books, library.books) &&
                Objects.equals(creationDate, library.creationDate) &&
                Objects.equals(title, library.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(books, creationDate, title);
    }

}
