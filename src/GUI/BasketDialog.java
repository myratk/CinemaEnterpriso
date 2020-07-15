package GUI;

import Domain.Customer.Booking;
import Domain.Customer.CustomerBasket;
import Domain.Customer.SnackPurchased;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    Label snackLabel, sizeLabel, quantityLabel, costLabel;
    Label totalCostL1, totalCostL2, totalCostL3;

    double totalCashGiven;

    public BasketDialog(CustomerBasket customerBasket, Stage mainStage) {
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
        theaterLabel.setAlignment(Pos.CENTER_LEFT); theaterLabel.setPrefWidth(80);
        Label dateLabel = new Label("Date");
        dateLabel.setAlignment(Pos.CENTER_LEFT); dateLabel.setPrefWidth(100);
        Label ticketsLabel = new Label("Tickets");
        ticketsLabel.setAlignment(Pos.CENTER); ticketsLabel.setPrefWidth(50);
        Label priceLabel = new Label("Cost");
        priceLabel.setAlignment(Pos.CENTER); priceLabel.setPrefWidth(50);
        HBox headingHBox = new HBox(filmLabel, theaterLabel, dateLabel, ticketsLabel, priceLabel);

        NumberFormat gb = NumberFormat.getCurrencyInstance(Locale.UK);
        VBox customerOrderVBox = new VBox();
        for(int i = 0; i< customerBasket.getNoOfBookings(); i++) {
            Booking custBooking = customerBasket.getBooking(i);
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
                customerBasket.removeBooking(custBooking);
                totalCostL1.setText("Total Cost: " + gb.format(customerBasket.getFilmTotal()));
                totalCostL3.setText("Total: " + gb.format(customerBasket.getTotal()));
            });

            customerOrderVBox.getChildren().add(bookingHBox);
        }
        customerOrderVBox.setSpacing(3);
        customerOrderVBox.setPadding(new Insets(10, 10, 10, 10));

        ScrollPane filmsScrollPane = new ScrollPane(customerOrderVBox);
        filmsScrollPane.setPrefSize(440, 200);

        totalCostL1 = new Label("Total Cost: " + gb.format(customerBasket.getFilmTotal()));
        totalCostL1.setAlignment(Pos.CENTER_RIGHT);
        totalCostL1.setPrefWidth(520);

        VBox topMiddleVBox = new VBox(headingHBox, filmsScrollPane, totalCostL1);
        topMiddleVBox.setPadding(new Insets(10, 10, 10, 10));
        topMiddleVBox.setSpacing(5);

        snackLabel = new Label("Snack");
        snackLabel.setAlignment(Pos.CENTER_LEFT); snackLabel.setPrefWidth(200);
        sizeLabel = new Label("Size");
        sizeLabel.setAlignment(Pos.CENTER_LEFT); sizeLabel.setPrefWidth(100);
        quantityLabel = new Label("Items");
        quantityLabel.setAlignment(Pos.CENTER); quantityLabel.setPrefWidth(80);
        costLabel = new Label("Price");
        costLabel.setAlignment(Pos.CENTER); costLabel.setPrefWidth(80);
        HBox headingHBox2 = new HBox(snackLabel, sizeLabel, quantityLabel, costLabel);

        snackScrollPane = new ScrollPane();
        snackScrollPane.setPrefSize(430, 200);
        if (customerBasket.isSnacksEmpty())
            snackScrollPane.setVisible(false);
        else
            snackScrollPane.setContent(showSnacks(customerBasket));

        totalCostL2 = new Label("Total Cost: " + gb.format(customerBasket.getSnackTotal()));
        totalCostL2.setAlignment(Pos.CENTER_RIGHT);
        totalCostL2.setPrefWidth(520);

        VBox bottomMiddleVBox = new VBox(headingHBox2, snackScrollPane, totalCostL2);
        bottomMiddleVBox.setPadding(new Insets(10, 10, 10, 10));
        bottomMiddleVBox.setSpacing(5);

        totalCostL3 = new Label("Total: " + gb.format(customerBasket.getTotal()));
        totalCostL3.setAlignment(Pos.CENTER);
        totalCostL3.setFont(new Font(18));

        Button snacksButton = new Button("Add Snacks");
        ImageView snackImageView = new ImageView(new Image("file:snacksIcon.png"));
        snackImageView.setFitWidth(50); snackImageView.setFitHeight(50);
        snacksButton.setGraphic(snackImageView);
        snacksButton.setOnAction(actionEvent -> {
            SnacksDialog snacksDialog = new SnacksDialog(customerBasket);
            snacksDialog.initModality(Modality.APPLICATION_MODAL);
            snacksDialog.initOwner(BasketDialog.this);
            snacksDialog.showAndWait();
            snackScrollPane.setVisible(true);
            snackScrollPane.setContent(showSnacks(customerBasket));
            totalCostL2.setText("Total Cost: " + gb.format(customerBasket.getSnackTotal()));
            totalCostL3.setText("Total: " + gb.format(customerBasket.getTotal()));
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

        //Label instructionL = new Label("Enter the number of notes");
        TextField fiftyTF = new TextField("0");
        fiftyTF.setPrefWidth(50); fiftyTF.setAlignment(Pos.CENTER_RIGHT);
        TextField twentyTF = new TextField("0");
        twentyTF.setPrefWidth(50); twentyTF.setAlignment(Pos.CENTER_RIGHT);
        TextField tenTF = new TextField("0");
        tenTF.setPrefWidth(50); fiftyTF.setAlignment(Pos.CENTER_RIGHT);
        TextField fiveTF = new TextField("0");
        fiveTF.setPrefWidth(50); fiveTF.setAlignment(Pos.CENTER_RIGHT);
        TextField twoTF = new TextField("0");
        twoTF.setPrefWidth(50); twoTF.setAlignment(Pos.CENTER_RIGHT);
        TextField oneTF = new TextField("0");
        oneTF.setPrefWidth(50); oneTF.setAlignment(Pos.CENTER_RIGHT);
        TextField fiftyPTF = new TextField("0");
        fiftyPTF.setPrefWidth(50); fiftyPTF.setAlignment(Pos.CENTER_RIGHT);
        TextField twentyPTF = new TextField("0");
        twentyPTF.setPrefWidth(50); twentyTF.setAlignment(Pos.CENTER_RIGHT);
        Button doneButton = new Button("Done");

        HBox cashHB = new HBox(new Label("  £50"), fiftyTF, new Label("  £20"), twentyTF, new Label("  £10"), tenTF, new Label("  £5"), fiveTF);
        cashHB.setAlignment(Pos.CENTER);
        cashHB.setPadding(new Insets(10, 10, 10, 10));
        cashHB.setSpacing(5);
        HBox coinsHB = new HBox(new Label("  £2"), twoTF, new Label("  £1"), oneTF, new Label("  50p"), fiftyPTF, new Label("  20p"), twentyPTF, new Label("    "), doneButton);
        coinsHB.setAlignment(Pos.CENTER);
        coinsHB.setPadding(new Insets(10, 10, 10, 10));
        coinsHB.setSpacing(5);

        Button giveCashButton = new Button("Give Cash");
        giveCashButton.setDisable(true);
        Button cancelButton = new Button("Cancel Transaction");
        HBox cashButtonsHBox = new HBox(giveCashButton, cancelButton);
        cashButtonsHBox.setAlignment(Pos.CENTER);
        cashButtonsHBox.setSpacing(20);
        cashButtonsHBox.setPadding(new Insets(10, 10, 10, 10));

        Label totalGiven = new Label("Total Received: " + gb.format(0));

        VBox moneyVBox = new VBox(new Label("Please enter number of notes"), cashHB, coinsHB, totalGiven, cashButtonsHBox);
        moneyVBox.setAlignment(Pos.CENTER);
        moneyVBox.setSpacing(10);
        moneyVBox.setVisible(false);

        checkOutButton.setOnAction(actionEvent -> {
            if (customerBasket.isBookingsEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "PLease add at least one film to basket", ButtonType.OK);
                alert.showAndWait();
                this.close();
            } else {
                moneyVBox.setVisible(true);
                topMiddleVBox.setDisable(true);
                bottomMiddleVBox.setDisable(true);
                snacksButton.setDisable(true);
                buttonsHBox.setDisable(true);
                cashHB.setDisable(false);
                coinsHB.setDisable(false);
                giveCashButton.setDisable(true);
            }
        });
        cancelButton.setOnAction(actionEvent -> {
            moneyVBox.setVisible(false);
            topMiddleVBox.setDisable(false);
            bottomMiddleVBox.setDisable(false);
            snacksButton.setDisable(false);
            buttonsHBox.setDisable(false);
            //totalGiven.setText("Cash Given: " + gb.format(0));
        });
        doneButton.setOnAction(actionEvent -> {
            totalCashGiven = 0.0;
            if (!(fiftyTF.getText() == null) && fiftyTF.getText().matches("[0-9]+"))
                totalCashGiven += Integer.parseInt(fiftyTF.getText()) * 50;
            if (!(twentyTF.getText() == null) && twentyTF.getText().matches("[0-9]+"))
                totalCashGiven += Integer.parseInt(twentyTF.getText()) * 20;
            if (!(tenTF.getText() == null) && tenTF.getText().matches("[0-9]+"))
                totalCashGiven += Integer.parseInt(tenTF.getText()) * 10;
            if (!(fiveTF.getText() == null) && fiveTF.getText().matches("[0-9]+"))
                totalCashGiven += Integer.parseInt(fiveTF.getText()) * 5;
            if (!(twoTF.getText() == null) && twoTF.getText().matches("[0-9]+"))
                totalCashGiven += Integer.parseInt(twoTF.getText()) * 2;
            if (!(oneTF.getText() == null) && oneTF.getText().matches("[0-9]+"))
                totalCashGiven += Integer.parseInt(oneTF.getText());
            if (!(fiftyPTF.getText() == null) && fiftyPTF.getText().matches("[0-9]+"))
                totalCashGiven += Integer.parseInt(fiftyPTF.getText()) * 0.5;
            if (!(twentyPTF.getText() == null) && twentyPTF.getText().matches("[0-9]+"))
                totalCashGiven += Integer.parseInt(twentyPTF.getText()) * 0.2;

            totalGiven.setText("Total Received: " + gb.format(totalCashGiven));
            if (totalCashGiven >= customerBasket.getTotal()) {
                giveCashButton.setDisable(false);
                cashHB.setDisable(true); coinsHB.setDisable(true);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Not enough cash given", ButtonType.OK);
                alert.showAndWait();
            }
        });
        giveCashButton.setOnAction(actionEvent -> {
            ReceiptGUI receiptGUI = new ReceiptGUI(customerBasket, totalCashGiven -customerBasket.getTotal());
            receiptGUI.show();
            this.close();
            mainStage.close();
        });


        VBox mainVBox = new VBox(topVBox, topMiddleVBox, bottomMiddleVBox, bottomVBox, moneyVBox);
        Scene scene = new Scene(mainVBox, 560, 750);
        this.setScene(scene);
    }

    private VBox showSnacks(CustomerBasket customerBasket) {
        NumberFormat gb = NumberFormat.getCurrencyInstance(Locale.UK);
        VBox snacksVBox = new VBox();
        for (int i = 0; i< customerBasket.getNumberOfSnacks(); i++) {
            SnackPurchased snackPurchased = customerBasket.getSnackPurchased(i);
            snackLabel = new Label(snackPurchased.getName());
            snackLabel.setAlignment(Pos.CENTER_LEFT); snackLabel.setPrefWidth(200);

            sizeLabel = new Label("" + snackPurchased.getSize());
            sizeLabel.setAlignment(Pos.CENTER_LEFT); sizeLabel.setPrefWidth(100);

            quantityLabel = new Label("" + snackPurchased.getQuantity());
            quantityLabel.setAlignment(Pos.CENTER_RIGHT); quantityLabel.setPrefWidth(80);

            costLabel = new Label("" + gb.format(snackPurchased.getTotal()));
            costLabel.setAlignment(Pos.CENTER_RIGHT); costLabel.setPrefWidth(80);

            ImageView redCross = new ImageView(new Image("file:cancelIcon.jpg"));
            redCross.setFitWidth(20); redCross.setFitHeight(20);
            VBox removeVBox = new VBox(redCross);
            removeVBox.setOnMouseEntered(mouseEvent -> removeVBox.setBorder(new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, null, null))));
            removeVBox.setOnMouseExited(mouseEvent -> removeVBox.setBorder(new Border(new BorderStroke(null, null, null, null))));

            HBox oneSnackHBox = new HBox(snackLabel, sizeLabel, quantityLabel, costLabel, new Label("  "), removeVBox);
            removeVBox.setOnMouseClicked(mouseEvent -> {
                snacksVBox.getChildren().remove(oneSnackHBox);
                customerBasket.removeSnack(snackPurchased);
                totalCostL2.setText("Total Cost: " + gb.format(customerBasket.getSnackTotal()));
                totalCostL3.setText("Total: " + gb.format(customerBasket.getTotal()));
            });
            snacksVBox.getChildren().add(oneSnackHBox);
        }
        snacksVBox.setSpacing(3);
        snacksVBox.setPadding(new Insets(10, 10, 10, 10));
        return snacksVBox;
    }

}
