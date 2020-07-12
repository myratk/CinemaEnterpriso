package GUI;

import Domain.Customer.CustomerOrder;
import Domain.Snacks.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;

public class SnacksDialog extends Stage {
    SnackMenuCategory popcornCategory, hotBeverageCategory, coldBeverageCategory;
    ArrayList<SnackMenuCategory> allCategories = new ArrayList<>();
    ArrayList<Snack> selectedSnackMenu = new ArrayList<>();

    ScrollPane rightScrollPane;

    public SnacksDialog(CustomerOrder customerOrder) {
        this.setTitle("Snacks Menu");

        Label titleLabel = new Label("Movie Snacks Menu");
        titleLabel.setFont(new Font(24));
        VBox topVBox = new VBox(titleLabel);
        topVBox.setAlignment(Pos.CENTER);
        topVBox.setPadding(new Insets(10, 10, 10, 10));

        setSnacks();

        VBox leftVBox = new VBox();
        for (SnackMenuCategory snackMenuCategory : allCategories) {
            ImageView categoryImageView = new ImageView(snackMenuCategory.getIcon());
            categoryImageView.setFitWidth(100);
            categoryImageView.setFitHeight(90);

            VBox categoryVBox = new VBox(categoryImageView, new Label(snackMenuCategory.getName()));
            categoryVBox.setAlignment(Pos.CENTER);
            categoryVBox.setSpacing(3);
            categoryVBox.setOnMouseClicked(mouseEvent -> categoryClicked(snackMenuCategory) );
            leftVBox.getChildren().add(categoryVBox);
        }
        leftVBox.setSpacing(10);
        leftVBox.setAlignment(Pos.CENTER);
        leftVBox.setPadding(new Insets(5, 5, 5, 5));

        ScrollPane leftScrollPane = new ScrollPane(leftVBox);
        leftScrollPane.setPrefSize(150, 300);

        rightScrollPane = new ScrollPane(showSnacks(popcornCategory));
        rightScrollPane.setPrefSize(350, 300);

        HBox middleHBox = new HBox(leftScrollPane, rightScrollPane);
        middleHBox.setSpacing(5);
        middleHBox.setPadding(new Insets(10, 10, 10, 10));
        middleHBox.setAlignment(Pos.CENTER);

        VBox mainVBox = new VBox(topVBox, middleHBox);
        Scene scene = new Scene(mainVBox, 600, 500);
        this.setScene(scene);
    }

    private void setSnacks() {
        //initialize popcorn category
       popcornCategory = new SnackMenuCategory("Popcorn", new Image("file:popcornIcon.jpg"));
        Popcorn popcorn1 = new Popcorn("Caramel Popcorn", 5.00, 5.50, 6.00);
        popcorn1.setPicture(new Image("file:Caramel.jpg"));
        Popcorn popcorn2 = new Popcorn("Salted Popcorn", 5.00, 5.50, 6.00);
        popcorn2.setPicture(new Image("file:Salted.jpg"));
        Popcorn popcorn3 = new Popcorn("Butter Popcorn", 4.50, 5.00, 5.50);
        popcorn3.setPicture(new Image("file:Butter.jpg"));
        Popcorn popcorn4 = new Popcorn("Cheesy Popcorn", 5.50, 6.00, 6.50);
        popcorn4.setPicture(new Image("file:Cheesy.jpg"));
        Popcorn popcorn5 = new Popcorn("Cinnamon Crunch", 5.50, 6.00, 6.50);
        popcorn5.setPicture(new Image("file:Cinnamon.jpg"));
        popcornCategory.addSnack(popcorn1, popcorn2, popcorn3, popcorn4, popcorn5);
        allCategories.add(popcornCategory);

        hotBeverageCategory = new SnackMenuCategory("Hot Beverages", new Image("file:hotBIcon.jpg"));
        HotBeverage hotBeverage1 = new HotBeverage("Latte", 3.00, 3.50, 4.00);
        hotBeverage1.setPicture(new Image("file:Latte.jpg"));
        HotBeverage hotBeverage2 = new HotBeverage("Cappuccino", 3.00, 3.50, 4.00);
        hotBeverage2.setPicture(new Image("file:Cappuccino.jpg"));
        HotBeverage hotBeverage3 = new HotBeverage("Chai Tea", 2.50, 3.00, 3.50);
        hotBeverage3.setPicture(new Image("file:Tea.jpg"));
        hotBeverageCategory.addSnack(hotBeverage1, hotBeverage2, hotBeverage3);
        allCategories.add(hotBeverageCategory);

        coldBeverageCategory = new SnackMenuCategory("Cold Beverages", new Image("file:coldBIcon.png"));
        ColdBeverage coldBeverage1 = new ColdBeverage("Coke", 3.00, 3.50, 4.00);
        coldBeverage1.setPicture(new Image("file:Coke.jpg"));
        ColdBeverage coldBeverage2 = new ColdBeverage("Sprite", 3.00, 3.50, 4.00);
        coldBeverage2.setPicture(new Image("file:Sprite.jpg"));
        ColdBeverage coldBeverage3 = new ColdBeverage("Orange Slushie", 3.50, 4.00, 4.50);
        coldBeverage3.setPicture(new Image("file:OrangeSlushie.jpg"));
        ColdBeverage coldBeverage4 = new ColdBeverage("Berry Slushie", 3.50, 4.00, 4.50);
        coldBeverage4.setPicture(new Image("file:BerrySlushie.jpg"));
        coldBeverageCategory.addSnack(coldBeverage1, coldBeverage2, coldBeverage3, coldBeverage4);
        allCategories.add(coldBeverageCategory);
    }

    private VBox showSnacks(SnackMenuCategory snackMenuCategory) {
        ArrayList<Snack>snackMenu = snackMenuCategory.getSnackMenu();
        VBox rightVBox = new VBox();
        ArrayList<VBox> snackVBoxes = new ArrayList<>();

        for (Snack snack : snackMenu) {
            ImageView snackImageView = new ImageView(snack.getPicture());
            snackImageView.setFitWidth(150);
            snackImageView.setFitHeight(120);
            Label snackLabel = new Label(snack.getName());

            VBox snackVBox = new VBox(snackImageView, snackLabel);
            snackVBox.setAlignment(Pos.CENTER);
            snackVBox.setPadding(new Insets(10, 10, 10, 10));
            snackVBox.setSpacing(3);
            snackVBoxes.add(snackVBox);
        }
        HBox snackHBox;
        for(int i=0; i<snackVBoxes.size(); i+=2) {
            if(i+1 == snackVBoxes.size())
                snackHBox = new HBox(snackVBoxes.get(i));
            else
                snackHBox = new HBox(snackVBoxes.get(i), snackVBoxes.get(i+1));
            snackHBox.setSpacing(5);
            rightVBox.getChildren().add(snackHBox);
        }
        return rightVBox;
    }

    private void categoryClicked(SnackMenuCategory snackMenuCategory) {
        rightScrollPane.setContent(showSnacks(snackMenuCategory));
    }
}
