package Domain;

import java.util.Calendar;
import java.util.Random;

public class TestClass {
    enum Size {small, medium, large};

    public static void main(String[] args) {
        Size size = Size.small;
        System.out.println(size.ordinal());


    }
}
