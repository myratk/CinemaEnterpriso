package Domain.Customer;

import Domain.Film;
import Domain.LectureTheater;
import Domain.MovieDate;
import Domain.Snacks.Snack;

import java.awt.print.Book;
import java.util.ArrayList;

public class CustomerOrder {
    private ArrayList<Booking> bookings;
    private ArrayList<SnackPurchased> snacksPurchased;
    private MovieDate date;

    public CustomerOrder(){
        bookings = new ArrayList<>();
        snacksPurchased = new ArrayList<>();
    }

    public ArrayList<SnackPurchased> getSnacksPurchased() {
        return snacksPurchased;
    }

    public double getFilmTotal() {
        double total = 0.0;
       for (Booking booking : bookings) {
           total += booking.getTotal();
       }
        return total;
    }

    public double getSnackTotal() {
        double total = 0.0;
        for (SnackPurchased snack : snacksPurchased) {
          total += snack.getTotal();
        }
        return total;
    }
    public double getTotal() {
        return getFilmTotal() + getSnackTotal();
    }

    public void addBooking(Film film, LectureTheater lectureTheater, int tickets, MovieDate movieDate) {
        bookings.add(new Booking(film, lectureTheater, tickets, movieDate));
    }

    public void removeBooking(Booking booking) {
        bookings.remove(booking);
    }

    public int getNoOfBookings() {
        return bookings.size();
    }

    public Booking getBooking(int index) {
        return bookings.get(index);
    }

    public boolean isBookingsEmpty() {
        return bookings.isEmpty();
    }

    public boolean containsBooking(Film film, MovieDate date) {
        for (Booking booking : bookings) {
            if (booking.getFilm().equals(film) && booking.getDate().equals(date))
                return true;
        }
        return false;
    }

    public int getTicketsOfABooking(Film film, MovieDate date) {
        for (Booking booking : bookings) {
            if(booking.getFilm().equals(film) && booking.getDate().equals(date))
                return booking.getNoOfTickets();
        }
        return 0;
    }

    public void editBooking(Film film, MovieDate date, int tickets) {
        for (Booking booking : bookings) {
            if(booking.getFilm().equals(film) && booking.getDate().equals(date))
                booking.setTickets(tickets);
        }
    }

    public int getNumberOfSnacks() {
        return snacksPurchased.size();
    }

    public SnackPurchased getSnackPurchased(int index) { return snacksPurchased.get(index); }
    public void addSnack(SnackPurchased snackPurchased) {
        snacksPurchased.add(snackPurchased);
    }

    public void removeSnack(SnackPurchased snack) {
        snacksPurchased.remove(snack);
    }

    public void setDate(MovieDate date) {
        this.date = date;
    }

    public MovieDate getDate() {
        return date;
    }

}