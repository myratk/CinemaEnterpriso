package Domain.Customer;

import Domain.Film;
import Domain.LectureTheater;
import Domain.MovieDate;

public class Booking {
    private Film film;
    private LectureTheater lectureTheater;
    private int noOfTickets;
    private MovieDate date;

    public Booking(Film film, LectureTheater lectureTheater, int noOfTickets, MovieDate date) {
        this.film = film;
        this.lectureTheater = lectureTheater;
        this.noOfTickets = noOfTickets;
        this.date = date;
    }

    public Film getFilm() {
        return film;
    }

    public LectureTheater getLectureTheater() {
        return lectureTheater;
    }

    public int getNoOfTickets() {
        return noOfTickets;
    }

    public MovieDate getDate() {
        return date;
    }

    public void setTickets(int tickets) {
        noOfTickets = tickets;
    }

    public double getTotal() {
        return film.getPrice() * noOfTickets;
    }

}
