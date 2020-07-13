package Domain.Customer;

import Domain.Film;
import Domain.LectureTheater;
import Domain.MovieDate;
import Domain.Snacks.Snack;

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

    public double getTotal() {
        double total = 0.0;

       for (Booking booking : bookings) {
           total += booking.getTotal();
       }

       for (SnackPurchased snackPurchased : snacksPurchased) {
           //total += snack.getPrice();
       }

        return total;

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

    public int getNumberOfSnacks() {
        return snacksPurchased.size();
    }

    public SnackPurchased getSnackPurchased(int index) { return snacksPurchased.get(index); }
    public void addSnack(SnackPurchased snackPurchased) {
        snacksPurchased.add(snackPurchased);
    }

    public void removeSnack(Snack snack) {
        snacksPurchased.remove(snack);
    }

    public void setDate(MovieDate date) {
        this.date = date;
    }

    public MovieDate getDate() {
        return date;
    }

}