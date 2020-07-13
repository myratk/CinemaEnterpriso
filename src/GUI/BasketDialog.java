package GUI;

import Domain.Customer.Booking;
import Domain.Customer.CustomerOrder;
import Domain.Customer.SnackPurchased;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.text.NumberFormat;
import java.util.Locale;

public class BasketDialog extends Stage {
    ScrollPane snackScrollPane;
    Label snackLabel, quantityLabel, costLabel;

    public BasketDialog(CustomerOrder customerOrder) {
        this.setTitle("Your Basket");

        Label titleLabel = new Label("Enterprising University");
        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setFont(new Font("Arial", 20));

        VBox topVBox = new VBox(titleLabel);
        topVBox.setAlignment(Pos.CENTER);
        topVBox.setPadding(new Insets(10, 10, 10, 10));

        Label filmLabel = new Label("Film");
        filmLabel.setAlignment(Pos.CENTER_LEFT); filmLabel.setPrefWidth(200);
        Label theaterLabel = new Label("Theater");
        theaterLabel.setAlignment(Pos.CENTER_LEFT); theaterLabel.setPrefWidth(100);
        Label dateLabel = new Label("Date");
        dateLabel.setAlignment(Pos.CENTER_LEFT); dateLabel.setPrefWidth(100);
        Label ticketsLabel = new Label("Tickets");
        ticketsLabel.setAlignment(Pos.CENTER_LEFT); ticketsLabel.setPrefWidth(50);
        Label priceLabel = new Label("Cost");
        priceLabel.setAlignment(Pos.CENTER_RIGHT); priceLabel.setPrefWidth(50);
        HBox headingHBox = new HBox(filmLabel, theaterLabel, dateLabel, ticketsLabel, priceLabel);

        NumberFormat gb = NumberFormat.getCurrencyInstance(Locale.UK);
        VBox customerOrderVBox = new VBox();
        for(int i=0; i<customerOrder.getNoOfBookings(); i++) {
            Booking custBooking = customerOrder.getBooking(i);
            filmLabel = new Label(custBooking.getFilm().getFilmName());
            filmLabel.setAlignment(Pos.CENTER_LEFT); filmLabel.setPrefWidth(200);

            theaterLabel = new Label(custBooking.getLectureTheater().getName());
            theaterLabel.setAlignment(Pos.CENTER_LEFT); theaterLabel.setPrefWidth(100);

            dateLabel = new Label("" + custBooking.getDate());
            dateLabel.setAlignment(Pos.CENTER_LEFT); dateLabel.setPrefWidth(100);

            ticketsLabel = new Label("" + custBooking.getNoOfTickets());
            ticketsLabel.setAlignment(Pos.CENTER_LEFT); ticketsLabel.setPrefWidth(50);

            priceLabel = new Label("" + gb.format(custBooking.getTotal()));
            priceLabel.setAlignment(Pos.CENTER_RIGHT); priceLabel.setPrefWidth(50);

            HBox bookingHBox = new HBox(filmLabel, theaterLabel, dateLabel, ticketsLabel, priceLabel);
            customerOrderVBox.getChildren().add(bookingHBox);
        }
        customerOrderVBox.setSpacing(5);
        customerOrderVBox.setPadding(new Insets(10, 10, 10, 10));

        ScrollPane filmsScrollPane = new ScrollPane(customerOrderVBox);
        filmsScrollPane.setPrefSize(430, 160);

        Label totalCostL = new Label("Total Cost: " + gb.format(customerOrder.getTotal()));
        totalCostL.setAlignment(Pos.CENTER_RIGHT);
        totalCostL.setPrefWidth(520);

        VBox topMiddleVBox = new VBox(headingHBox, filmsScrollPane, totalCostL);
        topMiddleVBox.setPadding(new Insets(10, 10, 10, 10));
        topMiddleVBox.setSpacing(5);

        snackLabel = new Label("Snack");
        snackLabel.setAlignment(Pos.CENTER_LEFT); snackLabel.setPrefWidth(200);
        quantityLabel = new Label("Items");
        quantityLabel.setAlignment(Pos.CENTER_RIGHT); quantityLabel.setPrefWidth(100);
        costLabel = new Label("Price");
        costLabel.setAlignment(Pos.CENTER_RIGHT); costLabel.setPrefWidth(100);
        HBox headingHBox2 = new HBox(snackLabel, quantityLabel, costLabel);

        snackScrollPane = new ScrollPane();
        snackScrollPane.setPrefSize(430, 100);
        snackScrollPane.setVisible(false);

        VBox bottomMiddleVBox = new VBox(headingHBox2, snackScrollPane);
        bottomMiddleVBox.setPadding(new Insets(10, 10, 10, 10));
        bottomMiddleVBox.setSpacing(5);

        Button snacksButton = new Button("Add Snacks");
        ImageView snackImageView = new ImageView(new Image("file:snacksIcon.png"));
        snackImageView.setFitWidth(50); snackImageView.setFitHeight(50);
        snacksButton.setGraphic(snackImageView);
        snacksButton.setOnAction(actionEvent -> {
            SnacksDialog snacksDialog = new SnacksDialog(customerOrder);
            snacksDialog.initModality(Modality.APPLICATION_MODAL);
            snacksDialog.initOwner(BasketDialog.this);
            snacksDialog.showAndWait();
            snackScrollPane.setVisible(true);
            snackScrollPane.setContent(showSnacks(customerOrder));
        } );

        Button checkOutButton = new Button("Check Out");

        VBox bottomVBox = new VBox(snacksButton, checkOutButton);
        bottomVBox.setPadding(new Insets(20, 10, 10, 10));
        bottomVBox.setAlignment(Pos.CENTER);
        bottomVBox.setSpacing(30);


        VBox mainVBox = new VBox(topVBox, topMiddleVBox, bottomMiddleVBox, bottomVBox);
        Scene scene = new Scene(mainVBox, 550, 600);
        this.setScene(scene);
    }

    private VBox showSnacks(CustomerOrder customerOrder) {
        NumberFormat gb = NumberFormat.getCurrencyInstance(Locale.UK);
        VBox snacksVBox = new VBox();
        for (int i=0; i<customerOrder.getNumberOfSnacks(); i++) {
            SnackPurchased snackPurchased = customerOrder.getSnackPurchased(i);
            snackLabel = new Label(snackPurchased.getName());
            snackLabel.setAlignment(Pos.CENTER_LEFT); snackLabel.setPrefWidth(200);

            quantityLabel = new Label("" + snackPurchased.getQuantity());
            quantityLabel.setAlignment(Pos.CENTER_RIGHT); quantityLabel.setPrefWidth(100);

            costLabel = new Label("" + gb.format(snackPurchased.getTotal()));
            costLabel.setAlignment(Pos.CENTER_RIGHT); costLabel.setPrefWidth(100);

            HBox oneSnackHBox = new HBox(snackLabel, quantityLabel, costLabel);
            snacksVBox.getChildren().add(oneSnackHBox);
        }
        return snacksVBox;
    }

}
