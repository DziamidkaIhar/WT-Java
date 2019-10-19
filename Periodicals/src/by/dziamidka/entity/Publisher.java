package by.dziamidka.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Publisher implements Serializable {
    private int ID;
    private List<Integer> idBooks;
    private String title;

    public Publisher() {
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "ID=" + ID +
                ", idBooks=" + idBooks +
                ", title='" + title + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publisher publisher = (Publisher) o;
        return ID == publisher.ID &&
                Objects.equals(idBooks, publisher.idBooks) &&
                Objects.equals(title, publisher.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, idBooks, title);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
