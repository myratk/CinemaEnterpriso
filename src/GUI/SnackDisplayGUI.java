package GUI;

import Domain.Customer.CustomerBasket;
import Domain.Customer.SnackPurchased;
import Domain.Snacks.Snack;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.text.NumberFormat;
import java.util.Locale;


public class SnackDisplayGUI extends Stage {
    private Label priceLabel;
    TextField quantTF;
    private Snack.Size chosenSize;

    private CustomerBasket customerBasket;
    private Snack snack;

    public SnackDisplayGUI(CustomerBasket customerBasket, Snack snack) {
        this.setTitle("Choose Snack");
        this.customerBasket = customerBasket;
        this.snack = snack;

        Label titleLabel = new Label(snack.getName());
        titleLabel.setFont(new Font(24));
        HBox topHBox = new HBox(titleLabel);
        topHBox.setAlignment(Pos.CENTER);
        topHBox.setPadding(new Insets(10, 10, 10, 10));

        ImageView snackImageView = new ImageView(snack.getPicture());
        snackImageView.setFitWidth(250);
        snackImageView.setFitHeight(150);
        HBox leftHBox = new HBox(snackImageView);
        leftHBox.setAlignment(Pos.CENTER);
        leftHBox.setPadding(new Insets(10, 10, 10, 10));

        Label chooseLabel = new Label("Please choose a size: ");

        Button smallButton = new Button("S");
        smallButton.setOnAction(actionEvent -> {
            chosenSize = Snack.Size.small;
            buttonPressed(snack.getPrice(chosenSize));
        } );
        Button mediumButton = new Button("M");
        mediumButton.setOnAction(actionEvent -> {
            chosenSize = Snack.Size.medium;
            buttonPressed(snack.getPrice(chosenSize));
        } );
        Button largeButton = new Button("L");
        largeButton.setOnAction(actionEvent -> {
            chosenSize = Snack.Size.large;
            buttonPressed(snack.getPrice(chosenSize));
        } );
        ButtonBar sizeButtonBar = new ButtonBar();
        sizeButtonBar.getButtons().addAll(smallButton, mediumButton, largeButton);

        priceLabel = new Label("price: ");

        quantTF = new TextField("1");
        quantTF.setEditable(false);
        quantTF.setAlignment(Pos.CENTER);
        quantTF.setPrefWidth(35);

        Button addButton = new Button("+");
        addButton.setOnAction(actionEvent -> {
            int quantity = Integer.parseInt(quantTF.getText()) + 1;
            quantTF.setText("" + quantity);
        });
        Button minusButton = new Button("-");
        minusButton.setOnAction(actionEvent -> {
            int quantity = Integer.parseInt(quantTF.getText()) - 1;
            if (!(quantity == 0))
                quantTF.setText("" + quantity);
        } );

        HBox quantityHBox = new HBox(new Label("Quantity"), minusButton, quantTF, addButton);
        quantityHBox.setSpacing(7);
        quantityHBox.setAlignment(Pos.CENTER);
        quantityHBox.setPadding(new Insets(25));

        VBox rightVBox = new VBox(chooseLabel, sizeButtonBar, priceLabel, quantityHBox);
        rightVBox.setAlignment(Pos.CENTER);
        rightVBox.setSpacing(10);
        rightVBox.setPadding(new Insets(10, 10, 10, 10));

        Button addToBasket = new Button("Add Items");
        addToBasket.setOnAction(actionEvent -> {
            if (chosenSize == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "PLease choose a size", ButtonType.OK);
                alert.showAndWait();
            }
            else {
                if (customerBasket.containsSnack(snack.getName(), chosenSize))
                    customerBasket.editSnacksPurchased(snack.getName(), chosenSize, Integer.parseInt(quantTF.getText()));
                else
                    customerBasket.addSnack(snack.getName(), snack.getPrice(chosenSize), Integer.parseInt(quantTF.getText()), chosenSize);
                this.close();
            }
        });
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(actionEvent -> this.close() );
        HBox bottomHBox = new HBox(addToBasket, cancelButton);
        bottomHBox.setAlignment(Pos.CENTER);
        bottomHBox.setSpacing(10);
        bottomHBox.setPadding(new Insets(10, 10, 10, 10));

        HBox middleHBox = new HBox(leftHBox, rightVBox);

        VBox mainVBox = new VBox(topHBox, middleHBox, bottomHBox);
        Scene scene = new Scene(mainVBox, 600, 290);
        this.setScene(scene);
    }

    private void buttonPressed(double price) {
        NumberFormat gb = NumberFormat.getCurrencyInstance(Locale.UK);
        priceLabel.setText("price: " + gb.format(price));
        if (customerBasket.containsSnack(snack.getName(), chosenSize)) {
            quantTF.setText("" + customerBasket.getQuantityOfSnacks(snack.getName(), chosenSize));
        }
    }
}
