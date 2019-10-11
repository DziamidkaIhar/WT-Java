package by.dziamidka.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class Author implements Serializable{
    private int ID;
    private String Name;
    private String Surname;
    private LocalDate dateOfBirth;
    private List<Book> books;

    public Author() {
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        int result = (Name != null)?Name.hashCode():0;
        result = 31 * result + ((Surname != null)?Surname.hashCode():0);
        return result;
    }

    @Override
    public String toString() {
        return "Author{" +
                "ID=" + ID +
                ", name='" + Name + '\'' +
                ", surname='" + Surname + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", books=" + books +
                '}';
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

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
