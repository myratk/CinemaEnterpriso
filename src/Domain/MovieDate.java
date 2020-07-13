package Domain;

import java.util.GregorianCalendar;

public class MovieDate extends GregorianCalendar {
    public MovieDate() {super(); }
    public MovieDate(int year, int month, int day) {
        super(year, month, day);
    }

    public MovieDate(int year, int month, int day, int hour, int minutes) {
        super(year, month, day, hour, minutes);
    }

    @Override
    public String toString() {
        return super.getTime().getDate() + "/" + (super.getTime().getMonth()+1) + ", " + "Sat";
    }
}
