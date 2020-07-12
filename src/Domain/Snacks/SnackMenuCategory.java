package Domain.Snacks;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Arrays;

public class SnackMenuCategory {
    public static int totalMenuCategories;
    private String name;
    private Image icon;
    private ArrayList<Snack> snackMenu;

    public SnackMenuCategory(String name, Image icon) {
        this.name = name;
        this.icon = icon;
        snackMenu = new ArrayList<>();
        totalMenuCategories++;
    }

    public String getName() {
        return name;
    }

    public Image getIcon() {
        return icon;
    }

    public void addSnack(Snack... snacks) {
        snackMenu.addAll(Arrays.asList(snacks));
    }

    public ArrayList<Snack> getSnackMenu() {
        return snackMenu;
    }

    public Snack getSnack(int index) {
        return snackMenu.get(index);
    }
}
