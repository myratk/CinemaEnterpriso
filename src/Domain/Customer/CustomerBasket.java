package Domain.Customer;

import Domain.Film;
import Domain.LectureTheater;
import Domain.MovieDate;
import Domain.Snacks.Snack;

import java.awt.print.Book;
import java.util.ArrayList;

public class CustomerBasket {
    private ArrayList<Booking> bookings;
    private ArrayList<SnackPurchased> snacksPurchased;
    private MovieDate date;

    public CustomerBasket(){
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

    private Booking findBooking(Film film, MovieDate date) {
        for (Booking booking : bookings) {
            if (booking.getFilm().equals(film) && booking.getDate().equals(date))
                return booking;
        }
        return null;
    }
    public void addBooking(Film film, LectureTheater lectureTheater, int tickets, ArrayList<Integer> seatsTaken, MovieDate movieDate) {
        bookings.add(new Booking(film, lectureTheater, tickets, seatsTaken, movieDate));
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
        if (!(findBooking(film, date) == null))
            return true;
        return false;
    }
    public int getTicketsOfABooking(Film film, MovieDate date) {
        Booking booking = findBooking(film, date);
        if (booking == null)
            return 0;
        return booking.getNoOfTickets();
    }
    public void editBooking(Film film, MovieDate date, int newTickets, ArrayList<Integer> seatsTaken) {
        Booking booking = findBooking(film, date);
        booking.setTickets(newTickets);
        booking.setSeatNumbers(seatsTaken);
    }
    public ArrayList<Integer> getSeatNumbers(Film film, MovieDate date) {
        Booking booking = findBooking(film, date);
        if (!(booking == null))
            return booking.getSeatNumbers();
        return null;
    }

    public int getNumberOfSnacks() {
        return snacksPurchased.size();
    }
    public boolean isSnacksEmpty() { return snacksPurchased.isEmpty(); }
    public SnackPurchased getSnackPurchased(int index) { return snacksPurchased.get(index); }
    public void addSnack(String name, double price, int quantity, Snack.Size size) {
        snacksPurchased.add(new SnackPurchased(name, price, quantity, size));
    }
    public void removeSnack(SnackPurchased snack) {
        snacksPurchased.remove(snack);
    }

    public boolean containsSnack(String snackName, Snack.Size size) {
        for (SnackPurchased snackPurchased : snacksPurchased) {
            if (snackPurchased.getName().equals(snackName) && snackPurchased.getSize().equals(size))
                return true;
        }
        return false;
    }

    public int getQuantityOfSnacks(String snackName, Snack.Size size) {
        for (SnackPurchased snackPurchased : snacksPurchased) {
            if(snackPurchased.getName().equals(snackName) && snackPurchased.getSize().equals(size)) {
                return snackPurchased.getQuantity();
            }
        }
        return 0;
    }

    public void editSnacksPurchased(String snackName, Snack.Size size, int quantity) {
        for (SnackPurchased snackPurchased : snacksPurchased) {
            if(snackPurchased.getName().equals(snackName) && snackPurchased.getSize().equals(size)) {
                snackPurchased.setQuantity(quantity);
            }
        }
    }

    public void setDate(MovieDate date) {
        this.date = date;
    }

    public MovieDate getDate() {
        return date;
    }

}