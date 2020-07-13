package Domain;

import java.util.ArrayList;

public class Show {
    public static final double SHOWS_PER_WEEK = 10;
    private ArrayList<LectureTheater> lectureTheaters;
    private ArrayList<Integer> seatsTakenInTheater;
    private MovieDate date;

    public Show(MovieDate date) {
        this.date = date;
        lectureTheaters = new ArrayList<>();
        seatsTakenInTheater = new ArrayList<>();
    }

    public MovieDate getDate() {
        return date;
    }

    public void addLectureTheaters(LectureTheater... theaters) {
        for (LectureTheater theater : theaters) {
            if (!(theater == null)) {
                lectureTheaters.add(theater);
                seatsTakenInTheater.add(0);
            }
        }
    }

    public int seatsAvailable(LectureTheater theater) {
        int index = lectureTheaters.indexOf(theater);
        return theater.getTotalSeats() - seatsTakenInTheater.get(index);
    }

    public void bookSeats(LectureTheater theater, int tickets) {
        int index = lectureTheaters.indexOf(theater);
        int seatsTaken = seatsTakenInTheater.get(index) + tickets;
        seatsTakenInTheater.set(index, seatsTaken);
    }

    public void editBookedSeats(LectureTheater lectureTheater, int previousTickets, int newTickets) {
        int index = lectureTheaters.indexOf(lectureTheater);
        seatsTakenInTheater.set(index, seatsTakenInTheater.get(index) - previousTickets);
        seatsTakenInTheater.set(index, seatsTakenInTheater.get(index) + newTickets);
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
