package by.dziamidka.entity;

import java.util.Objects;

public class Genre {
    private int ID;
    private BookGenre bookGenre;

    @Override
    public String toString() {
        return "Genre{" +
                "ID=" + ID +
                ", bookGenre=" + bookGenre +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return ID == genre.ID &&
                bookGenre == genre.bookGenre;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, bookGenre);
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

    public void setID(int ID) {
        this.ID = ID;
    }
}
