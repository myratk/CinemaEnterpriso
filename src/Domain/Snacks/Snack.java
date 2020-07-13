package Domain.Snacks;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class Snack {
    private String name;
    private Image picture;
    public enum Size {small, medium, large}
    ArrayList<Double> prices;
    
    public Snack(String name, double priceS, double priceM, double priceL) {
        this.name = name;
        prices = new ArrayList<>();
        prices.add(priceS);
        prices.add(priceM);
        prices.add(priceL);
    }

    public String getName() {
        return name;
    }

    public double getPrice(Size size) {
        return prices.get(size.ordinal());
    }

    public Image getPicture() {
        return picture;
    }

    public void setPicture(Image picture) {
        this.picture = picture;
    }

}