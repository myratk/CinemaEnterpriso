package Domain;

public class Film {
    private String filmName, filmReleaseYear;
    private double price;

    public Film(String filmName, String filmReleaseYear, double price) {
        this.filmName = filmName;
        this.filmReleaseYear = filmReleaseYear;
        this.price = price;
    }

    public String getFilmName() {
        return this.filmName;
    }

    public String getFilmReleaseYear() {
        return this.filmReleaseYear;
    }

    public double getPrice() {
        return this.price;
    }
}
