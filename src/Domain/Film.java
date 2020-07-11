package Domain;

import javafx.scene.image.Image;

public class Film {
    private String filmName, filmReleaseYear;
    private double price;
    private String ageRestriction, synopsis;
    private Image poster;

    public Film(String filmName, String filmReleaseYear, double price, String ageRestriction) {
        this.filmName = filmName;
        this.filmReleaseYear = filmReleaseYear;
        this.price = price;
        this.ageRestriction = ageRestriction;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public void setPoster(Image poster) {
        this.poster = poster;
    }

    public Image getPoster() { return poster; }

    public String getFilmName() {
        return this.filmName;
    }

    public String getFilmReleaseYear() {
        return this.filmReleaseYear;
    }

    public double getPrice() {
        return this.price;
    }

    public String getAgeRestriction() {
        return ageRestriction;
    }
}
