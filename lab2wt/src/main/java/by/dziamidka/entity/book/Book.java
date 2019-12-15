package by.dziamidka.entity.book;


import by.dziamidka.entity.Entity;

public class Book extends Entity {
    private String title;
    private int authorID;
    private String yearOfPublication;
    private int publisherId;
    private int genreId;

    public Book() {
    }

    public Book(String title, int authorID, String yearOfPublication, int publisherId, int genreId) {
        this.title = title;
        this.authorID = authorID;
        this.yearOfPublication = yearOfPublication;
        this.publisherId = publisherId;
        this.genreId = genreId;
    }

    public String getTitle() {
        return title;
    }

    public int getAuthorID() {
        return authorID;
    }

    public String getYearOfPublication() {
        return yearOfPublication;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public int getGenreId() {
        return genreId;
    }
}


