package GUI;

import Domain.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

public class FilmDialog extends Stage {
    private Show show;
    private LectureTheater lectureTheater;
    private Film film;
    private CustomerOrder customerOrder;
    TextField ticketsTF;

    public FilmDialog(Show show, LectureTheater lectureTheater, Film film, CustomerOrder customerOrder) {
        this.show = show;
        this.lectureTheater = lectureTheater;
        this.film = film;
        this.customerOrder = customerOrder;

        ImageView imageView = new ImageView(film.getPoster());
        imageView.setFitWidth(200);
        imageView.setFitHeight(300);

        Label ageLabel = new Label("This film has an age rating of " + film.getAgeRestriction());

        VBox leftVBox = new VBox(imageView, ageLabel);
        leftVBox.setAlignment(Pos.CENTER);
        leftVBox.setPadding(new Insets(20, 20, 20, 20));
        leftVBox.setSpacing(15);

        Label titleLabel = new Label(film.getFilmName() + ", " + film.getFilmReleaseYear());
        titleLabel.setFont(new Font(24));

        NumberFormat gb = NumberFormat.getCurrencyInstance(Locale.UK);
        Label priceLabel = new Label("" + gb.format(film.getPrice()));
        priceLabel.setFont(new Font(16));

        VBox topRightVBox = new VBox(titleLabel, priceLabel);
        topRightVBox.setAlignment(Pos.TOP_CENTER);
        topRightVBox.setPadding(new Insets(50, 20, 20, 20));
        topRightVBox.setSpacing(20);
        topRightVBox.setPrefWidth(370);

        Label synopsisText = new Label(film.getSynopsis());
        synopsisText.setWrapText(true);
        synopsisText.setAlignment(Pos.CENTER);
        synopsisText.setMaxWidth(320);

        Label movieLabel = new Label("Playing in " + lectureTheater.getName() + " on " + show.getDate());
        movieLabel.setAlignment(Pos.CENTER);
        movieLabel.setPrefWidth(320);

        VBox middleRightVBox = new VBox(synopsisText, movieLabel);
        middleRightVBox.setAlignment(Pos.CENTER);
        middleRightVBox.setSpacing(10);
        middleRightVBox.setPadding(new Insets(10, 10, 10, 10));
        middleRightVBox.setPrefWidth(370);

        Button addButton = new Button("+");
        addButton.setOnAction(actionEvent -> addTicketsPressed() );
        Button minusButton = new Button("-");
        minusButton.setOnAction(actionEvent -> minusTicketPressed() );
        ticketsTF = new TextField("1");
        ticketsTF.setEditable(false);
        ticketsTF.setAlignment(Pos.CENTER);
        ticketsTF.setPrefWidth(35);

        HBox bottomRightHBox = new HBox(new Label("Tickets: "), minusButton, ticketsTF, addButton);
        bottomRightHBox.setSpacing(7);
        bottomRightHBox.setAlignment(Pos.CENTER);
        bottomRightHBox.setPadding(new Insets(25));

        VBox rightVBox = new VBox(topRightVBox, middleRightVBox, bottomRightHBox);

        HBox topHBox = new HBox(leftVBox, rightVBox);


        Button bookButton = new Button("Book Ticket");
        bookButton.setPrefWidth(100);
        bookButton.setOnAction(actionEvent -> bookTicketPressed() );
        Button cancelButton = new Button("Cancel");
        cancelButton.setPrefWidth(100);
        cancelButton.setOnAction(actionEvent -> this.close() );
        HBox bottomHBox = new HBox(cancelButton, bookButton);
        bottomHBox.setAlignment(Pos.CENTER);
        bottomHBox.setPadding(new Insets(10));
        bottomHBox.setSpacing(10);


        VBox mainVBox = new VBox(topHBox, bottomHBox);

        Scene filmDialogScene = new Scene(mainVBox, 600, 430);
        this.setScene(filmDialogScene);
        this.setResizable(false);
    }

    private void bookTicketPressed() {
        int tickets = Integer.parseInt(ticketsTF.getText());
        if (tickets > show.seatsAvailable(lectureTheater)) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Sorry, not enough seats available", ButtonType.OK);
            alert.showAndWait();
        }
        else {
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION,
                    "Book " + ticketsTF.getText() + " tickets for " + film.getFilmName() + " on " + show.getDate() + "?",
                    ButtonType.YES, ButtonType.NO);
            confirmationAlert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION,
                            tickets + " tickets for " + film.getFilmName() + " have been added to basket", ButtonType.OK);
                    alert.showAndWait();
                    show.bookSeats(lectureTheater, tickets);
                    customerOrder.addFilm(film, tickets);
                }
            });
        }
        ticketsTF.setText("1");
    }

    private void minusTicketPressed() {
        int ticketVal = Integer.parseInt(ticketsTF.getText()) - 1;
        if (!(ticketVal == 0))
            ticketsTF.setText("" + ticketVal);
    }

    private void addTicketsPressed() {
        int ticketVal = Integer.parseInt(ticketsTF.getText()) + 1;
        ticketsTF.setText("" + ticketVal);
    }
}
