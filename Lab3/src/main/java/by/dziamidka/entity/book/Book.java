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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public void setYearOfPublication(String yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }
}
