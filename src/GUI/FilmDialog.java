package GUI;

import Domain.Film;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.text.NumberFormat;
import java.util.Locale;

public class FilmDialog extends Stage {
    public FilmDialog(Film film) {
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

        /*Label ageLabel = new Label("This film has an age rating of " + film.getAgeRestriction());
        VBox middleRightVBox = new VBox(ageLabel);
        middleRightVBox.setAlignment(Pos.CENTER_LEFT);
        middleRightVBox.setPadding(new Insets(20, 20, 20, 20));
        middleRightVBox.setPrefWidth(370);
        */

        VBox rightVBox = new VBox(topRightVBox);

        HBox topHBox = new HBox(leftVBox, rightVBox);

        Scene filmDialogScene = new Scene(topHBox, 600, 370);
        this.setScene(filmDialogScene);
        this.setResizable(false);
    }
}
