package GUI;

import Domain.Customer.Booking;
import Domain.Customer.CustomerBasket;
import Domain.Customer.SnackPurchased;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import jdk.dynalink.StandardNamespace;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Random;

public class ReceiptGUI extends Stage{
    public ReceiptGUI(CustomerBasket customerBasket, double change) {
        this.setTitle("Receipt");
        NumberFormat gb = NumberFormat.getCurrencyInstance(Locale.UK);

        Label uniLabel = new Label("Enterprising University");
        uniLabel.setFont(new Font(20));
        HBox detailHBox = new HBox(new Label("Invoice no: " + generateInvoiceNum()), new Label("Date: " + customerBasket.getDate()));
        detailHBox.setAlignment(Pos.CENTER);
        detailHBox.setSpacing(40);
        detailHBox.setPadding(new Insets(10, 10, 10, 10));

        ScrollPane middleScrollPane = new ScrollPane();
        Label nameLabel = new Label("Item");
        nameLabel.setAlignment(Pos.CENTER_LEFT); nameLabel.setPrefWidth(200);
        Label qtyLabel = new Label("QTY");
        qtyLabel.setAlignment(Pos.CENTER_LEFT); qtyLabel.setPrefWidth(50);
        Label costLabel = new Label("Price");
        costLabel.setAlignment(Pos.CENTER_RIGHT); costLabel.setPrefWidth(50);
        HBox headingHBox = new HBox(nameLabel, qtyLabel, costLabel);
        headingHBox.setPadding(new Insets(10, 10, 10, 10));

        VBox itemListVBox = new VBox();
        HBox itemHBox;
        for (int i=0; i<customerBasket.getNoOfBookings(); i++) {
            Booking booking = customerBasket.getBooking(i);
            nameLabel = new Label(booking.getFilm().getFilmName());
            nameLabel.setAlignment(Pos.CENTER_LEFT); nameLabel.setPrefWidth(200);
            qtyLabel = new Label("" + booking.getNoOfTickets());
            qtyLabel.setAlignment(Pos.CENTER_LEFT); qtyLabel.setPrefWidth(50);
            costLabel = new Label("" + gb.format(booking.getTotal()));
            costLabel.setAlignment(Pos.CENTER_RIGHT); costLabel.setPrefWidth(50);
            itemHBox = new HBox(nameLabel, qtyLabel, costLabel);
            itemHBox.setPadding(new Insets(10, 10, 10, 10));
            itemListVBox.getChildren().add(itemHBox);
        }
        for (int i=0; i<customerBasket.getNumberOfSnacks(); i++) {
            SnackPurchased snack = customerBasket.getSnackPurchased(i);
            nameLabel = new Label(snack.getName());
            nameLabel.setAlignment(Pos.CENTER_LEFT); nameLabel.setPrefWidth(200);
            qtyLabel = new Label("" + snack.getQuantity());
            qtyLabel.setAlignment(Pos.CENTER_LEFT); qtyLabel.setPrefWidth(50);
            costLabel = new Label("" + gb.format(snack.getTotal()));
            costLabel.setAlignment(Pos.CENTER_RIGHT); costLabel.setPrefWidth(50);
            itemHBox = new HBox(nameLabel, qtyLabel, costLabel);
            itemHBox.setPadding(new Insets(10, 10, 10, 10));
            itemListVBox.getChildren().add(itemHBox);
        }
        itemListVBox.setPadding(new Insets(5, 5, 5, 5));
        itemListVBox.setSpacing(2);
        middleScrollPane.setPrefSize(325, 250);
        middleScrollPane.setContent(itemListVBox);

        Label totalLabel = new Label("TOTAL: " + gb.format(customerBasket.getTotal()));
        totalLabel.setFont(new Font(16));
        VBox middleVBox = new VBox(headingHBox, middleScrollPane, totalLabel);
        middleVBox.setAlignment(Pos.CENTER);
        middleVBox.setSpacing(5);

        Button doneButton = new Button("Done");
        doneButton.setOnAction(actionEvent -> this.close() );
        Button bookAgainButton = new Button("Book Again");
        bookAgainButton.setOnAction(actionEvent -> {
            CinemaEnterpriseMainGUI gui = new CinemaEnterpriseMainGUI();
            gui.start(new Stage());
            this.close();
        });
        HBox bottomButtonsHBox = new HBox(doneButton, bookAgainButton);
        bottomButtonsHBox.setAlignment(Pos.CENTER);
        bottomButtonsHBox.setSpacing(20);

        Label changeLabel = new Label("Change given: " + gb.format(change));

        VBox mainVBox = new VBox(uniLabel, detailHBox, middleVBox, changeLabel, new Label(""), bottomButtonsHBox);
        mainVBox.setAlignment(Pos.CENTER);
        mainVBox.setSpacing(10);
        Scene scene = new Scene(mainVBox, 350, 600);
        this.setScene(scene);
    }

    private String generateInvoiceNum() {
        String randomNum = "";
        for (int i=0; i<7; i++) {
            Random rand = new Random();
            randomNum += rand.nextInt(10);
        }
        return randomNum;
    }
}
