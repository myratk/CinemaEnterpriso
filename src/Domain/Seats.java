package Domain;

import java.util.ArrayList;

public class Seats {
    private int noOfSeats;
    private ArrayList<Boolean> seatsFull;

    public Seats(int noOfSeats) {
        this.noOfSeats = noOfSeats;
        seatsFull = new ArrayList<>();
        for (int i=0; i<noOfSeats; i++) {
            seatsFull.add(false);
        }
    }

    public void bookSeats(ArrayList<Integer> seats) {
        for(Integer i : seats) {
            seatsFull.set(i, true);
        }
    }

    public int noOfSeatsEmpty() {
        int count = 0;
        for (boolean b : seatsFull) {
            if (b == false)
                count++;
        }
        return count;
    }

    public void removeSeatsTaken(ArrayList<Integer> oldSeats) {
        for (Integer i : oldSeats) {
            seatsFull.set(i, false);
        }
    }

    @Override
    public String toString() {
        String temp = "";
        for (Boolean b : seatsFull) {
            temp += b + ", ";
        }
        return temp;
    }
}
