package GUI;

import Domain.Customer.Booking;
import Domain.Customer.CustomerOrder;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
        theaterLabel.setAlignment(Pos.CENTER_LEFT); theaterLabel.setPrefWidth(200);
        Label ticketsLabel = new Label("Tickets");
        ticketsLabel.setAlignment(Pos.CENTER_RIGHT); ticketsLabel.setPrefWidth(50);
        Label priceLabel = new Label("Cost");
        priceLabel.setAlignment(Pos.CENTER_RIGHT); priceLabel.setPrefWidth(50);
        HBox headingHBox = new HBox(filmLabel, theaterLabel, ticketsLabel, priceLabel);

        NumberFormat gb = NumberFormat.getCurrencyInstance(Locale.UK);
        VBox customerOrderVBox = new VBox();
        for(int i=0; i<customerOrder.getNoOfBookings(); i++) {
            Booking custBooking = customerOrder.getBooking(i);
            filmLabel = new Label(custBooking.getFilm().getFilmName());
            filmLabel.setAlignment(Pos.CENTER_LEFT); filmLabel.setPrefWidth(200);

            theaterLabel = new Label(custBooking.getLectureTheater().getName());
            theaterLabel.setAlignment(Pos.CENTER_LEFT); theaterLabel.setPrefWidth(200);

            ticketsLabel = new Label("" + custBooking.getNoOfTickets());
            ticketsLabel.setAlignment(Pos.CENTER_RIGHT); ticketsLabel.setPrefWidth(50);


            priceLabel = new Label("" + gb.format(custBooking.getTotal()));
            priceLabel.setAlignment(Pos.CENTER_RIGHT); priceLabel.setPrefWidth(50);

            HBox bookingHBox = new HBox(filmLabel, theaterLabel, ticketsLabel, priceLabel);
            customerOrderVBox.getChildren().add(bookingHBox);
        }
        customerOrderVBox.setSpacing(5);
        customerOrderVBox.setPadding(new Insets(10, 10, 10, 10));

        ScrollPane middleScrollPane = new ScrollPane(customerOrderVBox);
        middleScrollPane.setPrefSize(400, 160);

        Label totalCostL = new Label("Total Cost: " + gb.format(customerOrder.getTotal()));
        totalCostL.setAlignment(Pos.CENTER_RIGHT);
        totalCostL.setPrefWidth(520);

        VBox middleVBox = new VBox(headingHBox, middleScrollPane, totalCostL);
        middleVBox.setPadding(new Insets(10, 10, 10, 10));
        middleVBox.setSpacing(5);

        Button snacksButton = new Button("Add Snacks");
        ImageView snackImageView = new ImageView(new Image("file:snacksIcon.png"));
        snackImageView.setFitWidth(50); snackImageView.setFitHeight(50);
        snacksButton.setGraphic(snackImageView);
        snacksButton.setOnAction(actionEvent -> addSnacksPressed(customerOrder) );

        Button checkOutButton = new Button("Check Out");

        VBox bottomVBox = new VBox(snacksButton, checkOutButton);
        bottomVBox.setPadding(new Insets(20, 10, 10, 10));
        bottomVBox.setAlignment(Pos.CENTER);
        bottomVBox.setSpacing(30);


        VBox mainVBox = new VBox(topVBox, middleVBox, bottomVBox);
        Scene scene = new Scene(mainVBox, 550, 400);
        this.setScene(scene);
    }

    private void addSnacksPressed(CustomerOrder customerOrder) {
        SnacksDialog snacksDialog = new SnacksDialog(customerOrder);
        snacksDialog.initModality(Modality.APPLICATION_MODAL);
        snacksDialog.initOwner(BasketDialog.this);
        snacksDialog.showAndWait();
    }

}
