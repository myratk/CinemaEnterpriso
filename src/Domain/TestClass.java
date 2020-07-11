package Domain;

import java.util.Calendar;
import java.util.Random;

public class TestClass {
    public static void main(String[] args) {
        System.out.println(generateInvoiceNum());
    }

    private static String generateInvoiceNum() {
        String randomNum = "";
        for (int i=0; i<7; i++) {
            Random rand = new Random();
            randomNum += rand.nextInt(10);
        }
        return randomNum;
    }
}
