package Domain;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomerOrder {
    private ArrayList<Film> filmsPurchased;
    private ArrayList<Snacks> snacksPurchased;
    private double total;

    public CustomerOrder(){
        filmsPurchased = new ArrayList<>();
        snacksPurchased = new ArrayList<>();
        total = 0;
    }

    public ArrayList<Film> getFilmsPurchased() {
        return filmsPurchased;
    }

    public ArrayList<Snacks> getSnacksPurchased() {
        return snacksPurchased;
    }

    public double getTotal() {
        return total;
    } // should calculate total here

    public void addFilm(Film film) {
        filmsPurchased.add(film);
    }

    public void removeFilm(Film film) { filmsPurchased.remove(film); }

    public void addSnack(Snacks snack) {
        snacksPurchased.add(snack);
    }

    public void removeSnack(Snacks snack) {
        snacksPurchased.remove(snack);
    }
}