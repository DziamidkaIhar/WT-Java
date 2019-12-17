package by.dziamidka.entity.bokInLibrary;

import by.dziamidka.entity.Entity;

public class BookInLibrary extends Entity {
    private int libraryId;
    private int bookId;

    public BookInLibrary() {
    }

    public int getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(int libraryId) {
        this.libraryId = libraryId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
}
