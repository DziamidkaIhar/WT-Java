package by.dziamidka.entity.book;


import by.dziamidka.entity.Entity;

public class Book extends Entity {
    private String title;
    private int authorId;
    private String yearOfPublication;
    private int publisherId;
    private int genreId;

    public Book() {
    }

    public Book(String title, int authorId, String yearOfPublication, int publisherId, int genreId) {
        this.title = title;
        this.authorId = authorId;
        this.yearOfPublication = yearOfPublication;
        this.publisherId = publisherId;
        this.genreId = genreId;
    }

    public String getTitle() {
        return title;
    }

    public int getAuthorId() {
        return authorId;
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


