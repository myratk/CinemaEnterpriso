package Domain;

import java.util.HashMap;

public class LectureTheater {
    private String name;
    private int totalSeats;
    private HashMap<MovieDate, Film> filmsShowing;

    public LectureTheater(String name, int seats) {
        this.name = name;
        this.totalSeats = seats;
        filmsShowing = new HashMap<>();
    }

    public int getTotalSeats(){
        return totalSeats;
    }

    public Film getFilmByDate(MovieDate date) {
        if (filmsShowing.containsKey(date))
            return filmsShowing.get(date);
        return null;
    }

    public void setFilmInTheater(MovieDate date, Film film) {
        filmsShowing.put(date, film);
    }

    public String getName() {
        return name;
    }
}
