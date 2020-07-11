package GUI;

import Domain.CustomerOrder;
import Domain.Film;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.text.NumberFormat;
import java.util.Locale;

public class FilmDialog extends Stage {
    public FilmDialog(Film film, CustomerOrder customerOrder) {
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
        VBox middleRightVBox = new VBox(synopsisText);
        middleRightVBox.setAlignment(Pos.CENTER);
        middleRightVBox.setPadding(new Insets(10, 10, 10, 10));
        middleRightVBox.setPrefWidth(370);

        Button addButton = new Button("+");
        Button minusButton = new Button("-");
        TextField ticketsTF = new TextField("1");
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
}
