package by.dziamidka.entity;

import java.util.List;
import java.util.Objects;

public class Library {

    private List<Integer> booksID;

    public  Library()
    {
    }

    @Override
    public String toString() {
        return "Library{" +
                "booksID=" + booksID +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Library library = (Library) o;
        return Objects.equals(booksID, library.booksID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(booksID);
    }

    public List<Integer> getBooksID() {
        return booksID;
    }

    public void setBooksID(List<Integer> booksID) {
        this.booksID = booksID;
    }



}
