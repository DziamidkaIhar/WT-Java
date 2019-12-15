package by.dziamidka.entity.genre;

public enum Genre {
    FANTASY(1,"fantasy"),
    SCIENCE_FICTION(2,"science_fiction"),
    WESTERN(2,"western"),
    ROMANCE(3,"romance"),
    THRILLER(4,"thriller"),
    MYSTERY(5,"mystery"),
    DETECTIVE(6,"detective"),
    DYSTOPIA(7,"dystopia");

    private int id;
    private String title;

    Genre(int ID, String title) {
        this.id = ID;
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
