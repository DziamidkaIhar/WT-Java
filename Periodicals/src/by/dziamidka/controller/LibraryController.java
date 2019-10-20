package by.dziamidka.controller;

import by.dziamidka.entity.Book;
import by.dziamidka.entity.Library;

import java.util.ArrayList;
import java.util.List;

public class LibraryController {
    private Library library;

    public LibraryController(Library library) {
        this.library = library;
    }

    public void ShowBooks(){

    }

    public void AddBook(Book book){
        ArrayList<Book> tmpBooks = library.getBooks();
        tmpBooks.add(book);
        library.setBooks(tmpBooks);
    }
}
