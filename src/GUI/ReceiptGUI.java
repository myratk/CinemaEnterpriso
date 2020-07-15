package GUI;

import Domain.Customer.Booking;
import Domain.Customer.CustomerBasket;
import Domain.Customer.SnackPurchased;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
        Label qtyLabel = new Label("QTY");
        Label costLabel = new Label("Price");
        HBox headingHBox = new HBox(nameLabel, qtyLabel, costLabel);

        VBox itemListVBox = new VBox();
        HBox itemHBox;
        for (int i=0; i<customerBasket.getNoOfBookings(); i++) {
            Booking booking = customerBasket.getBooking(i);
            nameLabel = new Label(booking.getFilm().getFilmName());
            qtyLabel = new Label("" + booking.getNoOfTickets());
            costLabel = new Label("" + gb.format(booking.getTotal()));
            itemHBox = new HBox(nameLabel, qtyLabel, costLabel);
            itemListVBox.getChildren().add(itemHBox);
        }
        for (int i=0; i<customerBasket.getNumberOfSnacks(); i++) {
            SnackPurchased snack = customerBasket.getSnackPurchased(i);
            nameLabel = new Label(snack.getName());
            qtyLabel = new Label("" + snack.getQuantity());
            costLabel = new Label("" + gb.format(snack.getPrice()));
            itemHBox = new HBox(nameLabel, qtyLabel, costLabel);
            itemListVBox.getChildren().add(itemHBox);
        }
        middleScrollPane.setContent(itemListVBox);

        VBox middleVBox = new VBox(headingHBox, middleScrollPane, new Label("TOTAL: " + gb.format(customerBasket.getTotal())));

        Button doneButton = new Button("Done");
        doneButton.setOnAction(actionEvent -> {
            CinemaEnterpriseMainGUI gui = new CinemaEnterpriseMainGUI();
            gui.start(new Stage());
            this.close();
        });
        VBox mainVBox = new VBox(uniLabel, detailHBox, middleVBox, doneButton);
        mainVBox.setAlignment(Pos.CENTER);
        mainVBox.setPadding(new Insets(10, 10, 10, 10));
        Scene scene = new Scene(mainVBox, 550, 600);
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
