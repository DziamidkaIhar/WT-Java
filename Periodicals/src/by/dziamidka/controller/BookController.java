package by.dziamidka.controller;

import by.dziamidka.entity.Author;
import by.dziamidka.entity.Book;
import by.dziamidka.entity.Genre;
import by.dziamidka.entity.Publisher;

import java.time.LocalDate;

public class BookController {

    private Book book;

    public BookController(Book book) {
        this.book = book;
    }

    public Book ChangeTitle(String newTitle){
        book.setTitle(newTitle);
        return book;
    }

    public Book ChangeAuthor(Author author){
        book.setAuthor(author);
        return book;
    }

    public Book ChangePublisher(Publisher publisher){
        book.setPublisher(publisher);
        return book;
    }

    public Book ChangeYear(LocalDate newDate){
        book.setYearOfPublication(newDate);
        return book;
    }

    public Book ChangeGenre(Genre genre){
        book.setGenre(genre);
        return book;
    }
}
