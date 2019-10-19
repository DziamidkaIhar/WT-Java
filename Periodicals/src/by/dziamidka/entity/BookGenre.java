package by.dziamidka.entity;

import java.io.Serializable;

public enum BookGenre implements Serializable {
    FANTASY("fantasy"),
    SCIENCE_FICTION("science fiction"),
    WESTERN("western"),
    ROMANCE("romance"),
    THRILLER("thriller"),
    MYSTERY("mystery"),
    DETECTIVE("detective"),
    DYSTOPIA("dystopia");



    BookGenre(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
