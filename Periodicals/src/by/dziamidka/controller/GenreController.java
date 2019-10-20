package by.dziamidka.controller;

import by.dziamidka.entity.Book;
import by.dziamidka.entity.Genre;

import java.util.ArrayList;

public class GenreController {

    private Genre genre;

    public GenreController(Genre genre) {
        this.genre = genre;
    }

    public void AddBook(Book book){
        ArrayList<Book> tmp = genre.getBooks();
        tmp.add(book);
        genre.setBooks(tmp);
    }
}
