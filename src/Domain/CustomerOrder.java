package Domain;

import java.util.ArrayList;

public class CustomerOrder {
    private ArrayList<Film> filmsPurchased;
    private ArrayList<Integer> ticketsPurchased;
    private ArrayList<Snacks> snacksPurchased;
    private double total;
    private MovieDate date;

    public CustomerOrder(){
        filmsPurchased = new ArrayList<>();
        ticketsPurchased = new ArrayList<>();
        snacksPurchased = new ArrayList<>();
        total = 0;
    }

    public ArrayList<Film> getFilmsPurchased() {
        return filmsPurchased;
    }

    public Film getFilmsPurchased(int index) {
        return filmsPurchased.get(index);
    }

    public int getTicketsPurchased(int index) {
        return ticketsPurchased.get(index);
    }

    public ArrayList<Integer> getTicketsPurchased() {
        return ticketsPurchased;
    }

    public ArrayList<Snacks> getSnacksPurchased() {
        return snacksPurchased;
    }

    public double getTotalOne(int index) {
        return filmsPurchased.get(index).getPrice() * ticketsPurchased.get(index);
    }
    public double getTotalAll() {
        double total = 0.0;

        for (int i=0; i<filmsPurchased.size(); i++) {
            total += getTotalOne(i);
        }

        if (!snacksPurchased.isEmpty()) {
            for (int i=0; i<snacksPurchased.size(); i++) {
                total += snacksPurchased.get(i).getPrice();
            }
        }

        return total;

    } // should calculate total here

    public void addFilm(Film film, int tickets) {
        filmsPurchased.add(film);
        ticketsPurchased.add(tickets);
    }

    public void removeFilm(Film film) {
        int index = filmsPurchased.indexOf(film);
        filmsPurchased.remove(index);
        ticketsPurchased.remove(index);
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

    @Override
    public String toString() {
        return filmsPurchased.get(0) + " " + ticketsPurchased.get(0);
    }
}