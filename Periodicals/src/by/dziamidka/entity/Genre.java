package by.dziamidka.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Genre implements Serializable {
    private int ID;
    private BookGenre bookGenre;
    private List<Integer> idBooks;

    public Genre(BookGenre bookGenre) {
        this.bookGenre = bookGenre;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "ID=" + ID +
                ", bookGenre=" + bookGenre +
                ", idBooks=" + idBooks +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return ID == genre.ID &&
                bookGenre == genre.bookGenre &&
                Objects.equals(idBooks, genre.idBooks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, bookGenre, idBooks);
    }

    public Genre() {
    }

    public BookGenre getBookGenre() {
        return bookGenre;
    }

    public void setBookGenre(BookGenre bookGenre) {
        this.bookGenre = bookGenre;
    }

    public int getID() {
        return ID;
    }

    public List<Integer> getIdBooks() {
        return idBooks;
    }

    public void setIdBooks(List<Integer> idBooks) {
        this.idBooks = idBooks;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
