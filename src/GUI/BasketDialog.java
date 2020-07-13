package GUI;

import Domain.Customer.Booking;
import Domain.Customer.CustomerOrder;
import Domain.Customer.SnackPurchased;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.text.NumberFormat;
import java.util.Locale;

public class BasketDialog extends Stage {
    ScrollPane snackScrollPane;
    Label snackLabel, quantityLabel, costLabel;
    Label totalCostL1, totalCostL2, totalCostL3;

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
            theaterLabel.setAlignment(Pos.CENTER_LEFT); theaterLabel.setPrefWidth(80);

            dateLabel = new Label("" + custBooking.getDate());
            dateLabel.setAlignment(Pos.CENTER_LEFT); dateLabel.setPrefWidth(100);

            ticketsLabel = new Label("" + custBooking.getNoOfTickets());
            ticketsLabel.setAlignment(Pos.CENTER_LEFT); ticketsLabel.setPrefWidth(50);

            priceLabel = new Label("" + gb.format(custBooking.getTotal()));
            priceLabel.setAlignment(Pos.CENTER_RIGHT); priceLabel.setPrefWidth(50);

            ImageView redCross = new ImageView(new Image("file:cancelIcon.jpg"));
            redCross.setFitWidth(20); redCross.setFitHeight(20);
            VBox removeVBox = new VBox(redCross);
            removeVBox.setOnMouseEntered(mouseEvent -> removeVBox.setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, null, null))));
            removeVBox.setOnMouseExited(mouseEvent -> removeVBox.setBorder(new Border(new BorderStroke(null, null, null, null))));

            HBox bookingHBox = new HBox(filmLabel, theaterLabel, dateLabel, ticketsLabel, priceLabel, new Label("  "), removeVBox);
            removeVBox.setOnMouseClicked(mouseEvent -> {
                customerOrderVBox.getChildren().remove(bookingHBox);
                customerOrder.removeBooking(custBooking);
                totalCostL1.setText("Total Cost: " + gb.format(customerOrder.getFilmTotal()));
                totalCostL3.setText("Total: " + gb.format(customerOrder.getTotal()));
            });

            customerOrderVBox.getChildren().add(bookingHBox);
        }
        customerOrderVBox.setSpacing(5);
        customerOrderVBox.setPadding(new Insets(10, 10, 10, 10));

        ScrollPane filmsScrollPane = new ScrollPane(customerOrderVBox);
        filmsScrollPane.setPrefSize(430, 160);

        totalCostL1 = new Label("Total Cost: " + gb.format(customerOrder.getFilmTotal()));
        totalCostL1.setAlignment(Pos.CENTER_RIGHT);
        totalCostL1.setPrefWidth(520);

        VBox topMiddleVBox = new VBox(headingHBox, filmsScrollPane, totalCostL1);
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

        totalCostL2 = new Label("Total Cost: " + gb.format(customerOrder.getSnackTotal()));
        totalCostL2.setAlignment(Pos.CENTER_RIGHT);
        totalCostL2.setPrefWidth(520);

        VBox bottomMiddleVBox = new VBox(headingHBox2, snackScrollPane, totalCostL2);
        bottomMiddleVBox.setPadding(new Insets(10, 10, 10, 10));
        bottomMiddleVBox.setSpacing(5);

        totalCostL3 = new Label("Total: " + gb.format(customerOrder.getTotal()));
        totalCostL3.setAlignment(Pos.CENTER);
        totalCostL3.setFont(new Font(18));

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
            totalCostL2.setText("Total Cost: " + gb.format(customerOrder.getSnackTotal()));
            totalCostL3.setText("Total: " + gb.format(customerOrder.getTotal()));
        } );

        Button checkOutButton = new Button("Check Out");
        Button backButton = new Button("Go Back");
        backButton.setOnAction(actionEvent -> this.close() );
        HBox buttonsHBox = new HBox(backButton, checkOutButton);
        buttonsHBox.setAlignment(Pos.CENTER);
        buttonsHBox.setSpacing(40);

        VBox bottomVBox = new VBox(snacksButton, totalCostL3, buttonsHBox);
        bottomVBox.setPadding(new Insets(20, 10, 10, 10));
        bottomVBox.setAlignment(Pos.CENTER);
        bottomVBox.setSpacing(30);


        VBox mainVBox = new VBox(topVBox, topMiddleVBox, bottomMiddleVBox, bottomVBox);
        Scene scene = new Scene(mainVBox, 550, 550);
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

            ImageView redCross = new ImageView(new Image("file:cancelIcon.jpg"));
            redCross.setFitWidth(20); redCross.setFitHeight(20);
            VBox removeVBox = new VBox(redCross);
            removeVBox.setOnMouseEntered(mouseEvent -> removeVBox.setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, null, null))));
            removeVBox.setOnMouseExited(mouseEvent -> removeVBox.setBorder(new Border(new BorderStroke(null, null, null, null))));

            HBox oneSnackHBox = new HBox(snackLabel, quantityLabel, costLabel, new Label("  "), removeVBox);
            removeVBox.setOnMouseClicked(mouseEvent -> {
                snacksVBox.getChildren().remove(oneSnackHBox);
                customerOrder.removeSnack(snackPurchased);
                totalCostL2.setText("Total Cost: " + gb.format(customerOrder.getSnackTotal()));
                totalCostL3.setText("Total: " + gb.format(customerOrder.getTotal()));
            });
            snacksVBox.getChildren().add(oneSnackHBox);
        }
        return snacksVBox;
    }

}
