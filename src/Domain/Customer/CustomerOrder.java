package Domain.Customer;

import Domain.Customer.Booking;
import Domain.Film;
import Domain.LectureTheater;
import Domain.MovieDate;
import Domain.Snacks.Snacks;

import java.util.ArrayList;

public class CustomerOrder {
    private ArrayList<Booking> bookings;
    private ArrayList<Snacks> snacksPurchased;
    private MovieDate date;

    public CustomerOrder(){
        bookings = new ArrayList<>();
        snacksPurchased = new ArrayList<>();
    }

    public ArrayList<Snacks> getSnacksPurchased() {
        return snacksPurchased;
    }

    public double getTotal() {
        double total = 0.0;

       for (Booking booking : bookings) {
           total += booking.getTotal();
       }

       for (Snacks snack : snacksPurchased) {
           total += snack.getPrice();
       }

        return total;

    }

    public void addBooking(Film film, LectureTheater lectureTheater, int tickets) {
        bookings.add(new Booking(film, lectureTheater, tickets));
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

    public boolean isEmpty() {
        return bookings.isEmpty();
    }

    public void addSnack(Snacks snack) {
        snacksPurchased.add(snack);
    }

    public void removeSnack(Snacks snack) {
        snacksPurchased.remove(snack);
    }

    public void setDate(MovieDate date) {
        this.date = date;
    }

    public MovieDate getDate() {
        return date;
    }

}