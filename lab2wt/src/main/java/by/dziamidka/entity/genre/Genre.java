package by.dziamidka.entity.genre;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public enum Genre {
    FANTASY(1,"fantasy"),
    SCIENCE_FICTION(2,"science_fiction"),
    WESTERN(3,"western"),
    ROMANCE(4,"romance"),
    THRILLER(5,"thriller"),
    MYSTERY(6,"mystery"),
    DETECTIVE(7,"detective"),
    DYSTOPIA(8,"dystopia");

    @JacksonXmlProperty(isAttribute = true)
    private int id;

    private String title;

    Genre(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getID() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Genre getById(int id) {
        switch (id)
        {
            case 1:
                return FANTASY;
            case 2:
                return SCIENCE_FICTION;
            case 3:
                return WESTERN;
            case 4:
                return ROMANCE;
            case 5:
                return THRILLER;
            case 6:
                return MYSTERY;
            case 7:
                return DETECTIVE;
            case 8:
                return DYSTOPIA;
            default:
                throw new IllegalArgumentException("Don't pass exams");
        }
    }
}
