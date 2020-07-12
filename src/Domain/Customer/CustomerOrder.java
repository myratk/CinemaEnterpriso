package Domain.Customer;

import Domain.Film;
import Domain.LectureTheater;
import Domain.MovieDate;
import Domain.Snacks.Snack;

import java.util.ArrayList;

public class CustomerOrder {
    private ArrayList<Booking> bookings;
    private ArrayList<Snack> snackPurchased;
    private MovieDate date;

    public CustomerOrder(){
        bookings = new ArrayList<>();
        snackPurchased = new ArrayList<>();
    }

    public ArrayList<Snack> getSnackPurchased() {
        return snackPurchased;
    }

    public double getTotal() {
        double total = 0.0;

       for (Booking booking : bookings) {
           total += booking.getTotal();
       }

       for (Snack snack : snackPurchased) {
           //total += snack.getPrice();
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

    public void addSnack(Snack snack) {
        snackPurchased.add(snack);
    }

    public void removeSnack(Snack snack) {
        snackPurchased.remove(snack);
    }

    public void setDate(MovieDate date) {
        this.date = date;
    }

    public MovieDate getDate() {
        return date;
    }

}