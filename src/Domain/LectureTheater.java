package Domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class LectureTheater {
    private String name;
    private int totalSeats;
    private HashMap<GregorianCalendar, Film> filmsShowing;

    public LectureTheater(String name, int seats) {
        this.name = name;
        this.totalSeats = seats;
        filmsShowing = new HashMap<>();
    }

    public int getTotalSeats(){
        return totalSeats;
    }

    public Film getFilmByDate(GregorianCalendar date) {
        if (filmsShowing.containsKey(date))
            return filmsShowing.get(date);
        return null;
    }

    public void setFilmInTheater(GregorianCalendar date, Film film) {
        filmsShowing.put(date, film);
    }
}
