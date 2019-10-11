package by.dziamidka.entity;

import java.util.List;
import java.util.Objects;

public class Publisher {
    private int ID;
    private List<Book> books;
    private String title;

    public Publisher() {
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "ID=" + ID +
                ", books=" + books +
                ", title='" + title + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publisher publisher = (Publisher) o;
        return ID == publisher.ID &&
                Objects.equals(books, publisher.books) &&
                Objects.equals(title, publisher.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, books, title);
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
