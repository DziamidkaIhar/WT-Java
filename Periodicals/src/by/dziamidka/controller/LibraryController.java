package by.dziamidka.controller;

import by.dziamidka.entity.Book;
import by.dziamidka.entity.Library;

import java.util.ArrayList;

public class LibraryController {
    private Library library;

    public LibraryController(Library library) {
        this.library = library;
    }

    public void ShowBooks(){
        ArrayList<Book> books = library.getBooks();
        for (int i = 0; i < books.size(); i++){
            Book curBook = books.get(i);
            if (curBook != null) {
                System.out.println(i+1 + ")      Title:  " + curBook.getTitle() + ".\n" +
                        "Author:  " + curBook.getAuthor().getName()+ " " + curBook.getAuthor().getSurname() + ".\n" +
                        "Year of publication:  " + curBook.getYearOfPublication().getYear() + ".\n" +
                        "Publisher:  " + curBook.getPublisher().getTitle() + ".\n" +
                        "Genre:  " + curBook.getGenre().getBookGenre().getValue() + ".\n");
            }
        }
    }

    public Library AddBook(Book book){
        ArrayList<Book> tmpBooks = library.getBooks();
        tmpBooks.add(book);
        library.setBooks(tmpBooks);
        System.out.println("Book " + book.getTitle() + " was added.");
        return library;
    }
}
