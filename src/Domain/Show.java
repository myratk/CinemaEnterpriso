package Domain;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class Show {
    public static final double SHOWS_PER_WEEK = 10;
    private ArrayList<LectureTheater> lectureTheaters;
    private HashMap<LectureTheater, Integer> seatsTakenInTheater;
    private GregorianCalendar date;

    public Show(GregorianCalendar date) {
        this.date = date;
        lectureTheaters = new ArrayList<>();
        seatsTakenInTheater = new HashMap<>();
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public void addLectureTheaters(LectureTheater... theaters) {
        for (LectureTheater theater : theaters) {
            if (!(theater == null)) {
                lectureTheaters.add(theater);
                seatsTakenInTheater.put(theater, 0);
            }
        }
    }

    public boolean bookSeats(LectureTheater theater, int tickets) {
        if (lectureTheaters.contains(theater)) {
            if (theater.getTotalSeats() >= (seatsTakenInTheater.get(theater) + tickets)) {
                seatsTakenInTheater.put(theater, seatsTakenInTheater.get(theater) + tickets);
                return true;
            }
        }
        return false;
    }

    public void addFilms(Film... films) {
        int i=0;
        for (Film f : films) {
            lectureTheaters.get(i).setFilmInTheater(this.date, f);

            i++;
        }
    }

    public ArrayList<Film> getAllFilms() {
        ArrayList<Film> films = new ArrayList<>();

        for(int i=0; i<lectureTheaters.size(); i++) {
            LectureTheater tempTheater = lectureTheaters.get(i);
            films.add(tempTheater.getFilmByDate(this.date));
        }

        return films;
    }

    public ArrayList<LectureTheater> getLectureTheaters() {
        return lectureTheaters;
    }
}
