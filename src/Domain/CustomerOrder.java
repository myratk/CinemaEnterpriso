package Domain;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomerOrder {
    private ArrayList<Film> filmsPurchased;
    private ArrayList<Integer> ticketsPurchased;
    private ArrayList<Snacks> snacksPurchased;
    private double total;

    public CustomerOrder(){
        filmsPurchased = new ArrayList<>();
        ticketsPurchased = new ArrayList<>();
        snacksPurchased = new ArrayList<>();
        total = 0;
    }

    public ArrayList<Film> getFilmsPurchased() {
        return filmsPurchased;
    }

    public ArrayList<Integer> getTicketsPurchased() {
        return ticketsPurchased;
    }

    public ArrayList<Snacks> getSnacksPurchased() {
        return snacksPurchased;
    }

    public double getTotal() {
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
}