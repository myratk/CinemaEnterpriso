package Domain.Customer;

import Domain.Film;
import Domain.LectureTheater;

public class Booking {
    private Film film;
    private LectureTheater lectureTheater;
    private int noOfTickets;

    public Booking(Film film, LectureTheater lectureTheater, int noOfTickets) {
        this.film = film;
        this.lectureTheater = lectureTheater;
        this.noOfTickets = noOfTickets;
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

    public void addTickets(int tickets) {
        noOfTickets += tickets;
    }

    public double getTotal() {
        return film.getPrice() + noOfTickets;
    }

}
