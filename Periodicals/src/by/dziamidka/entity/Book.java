package by.dziamidka.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;



public class Book implements Serializable {
    private String title;
    private Author author;
    private LocalDate yearOfPublication;
    private Publisher publisher;
    private Genre genre;

    public Book(String title, Author author, LocalDate yearOfPublication, Publisher publisher, Genre genre) {
        this.title = title;
        this.author = author;
        this.yearOfPublication = yearOfPublication;
        this.publisher = publisher;
        this.genre = genre;
    }

    public Book() {
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author=" + author.getName() +
                ", yearOfPublication=" + yearOfPublication +
                ", publisher=" + publisher.getTitle() +
                ", genre=" + genre.getBookGenre().getValue() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(title, book.title) &&
                Objects.equals(author, book.author) ;/*&&
                Objects.equals(yearOfPublication, book.yearOfPublication) &&
                Objects.equals(publisher, book.publisher) &&
                Objects.equals(genre, book.genre);*/
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, yearOfPublication, publisher, genre);
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public LocalDate getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(LocalDate yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Book(String title) {
        this.title = title;
    }
}
