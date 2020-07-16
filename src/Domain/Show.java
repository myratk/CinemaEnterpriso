package Domain;

import java.util.ArrayList;

public class Show {
    public static final double SHOWS_PER_WEEK = 10;
    private ArrayList<LectureTheater> lectureTheaters;
    private ArrayList<Seats> seatsInTheater;
    private MovieDate date;

    public Show(MovieDate date) {
        this.date = date;
        lectureTheaters = new ArrayList<>();
        seatsInTheater = new ArrayList<>();
    }

    public MovieDate getDate() {
        return date;
    }

    public void addLectureTheaters(LectureTheater... theaters) {
        for (LectureTheater theater : theaters) {
            if (!(theater == null)) {
                lectureTheaters.add(theater);
                seatsInTheater.add(new Seats(theater.getTotalSeats()));
            }
        }
    }

    public int seatsAvailable(LectureTheater theater) {
        int index = lectureTheaters.indexOf(theater);
        return seatsInTheater.get(index).noOfSeatsEmpty();
    }

    public void bookSeats(LectureTheater theater, ArrayList<Integer> seats) {
        int index = lectureTheaters.indexOf(theater);
        seatsInTheater.get(index).bookSeats(seats);
    }


    public void editBookedSeats(LectureTheater lectureTheater, ArrayList<Integer> previousSeats, ArrayList<Integer> newSeats) {
       Seats seats = seatsInTheater.get(lectureTheaters.indexOf(lectureTheater));
       seats.removeSeatsTaken(previousSeats);
       seats.bookSeats(newSeats);
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
