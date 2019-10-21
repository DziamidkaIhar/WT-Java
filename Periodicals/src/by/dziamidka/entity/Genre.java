package by.dziamidka.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Genre implements Serializable {
    private BookGenre bookGenre;
    private ArrayList<Book> books;

    public Genre(BookGenre bookGenre) {
        books = new ArrayList<Book>();
        this.bookGenre = bookGenre;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "bookGenre=" + bookGenre +
                ", books=" + books +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return bookGenre == genre.bookGenre &&
                Objects.equals(books, genre.books);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookGenre, books);
    }

    public Genre() {
    }

    public BookGenre getBookGenre() {
        return bookGenre;
    }

    public void setBookGenre(BookGenre bookGenre) {
        this.bookGenre = bookGenre;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }
}
