package Domain.Customer;

import Domain.Film;
import Domain.LectureTheater;
import Domain.MovieDate;

import java.util.ArrayList;

public class Booking {
    private Film film;
    private LectureTheater lectureTheater;
    private int noOfTickets;
    private ArrayList<Integer> seatNumbers;
    private MovieDate date;

    public Booking(Film film, LectureTheater lectureTheater, int noOfTickets, ArrayList<Integer> seatNumbers, MovieDate date) {
        this.film = film;
        this.lectureTheater = lectureTheater;
        this.noOfTickets = noOfTickets;
        this.seatNumbers = seatNumbers;
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

    public void setSeatNumbers(ArrayList<Integer> seatNumbers) {
        this.seatNumbers = seatNumbers;
    }

    public ArrayList<Integer> getSeatNumbers() {
        return seatNumbers;
    }

    public double getTotal() {
        return film.getPrice() * noOfTickets;
    }

}
