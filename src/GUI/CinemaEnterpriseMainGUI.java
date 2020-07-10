package GUI;

import Domain.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class CinemaEnterpriseMainGUI extends Application {
    private GregorianCalendar sat1 = new GregorianCalendar(2020, Calendar.JULY, 4);
    private GregorianCalendar sat2 = new GregorianCalendar(2020, Calendar.JULY, 11);
    private GregorianCalendar sat3 = new GregorianCalendar(2020, Calendar.JULY, 18);
    private GregorianCalendar sat4 = new GregorianCalendar(2020, Calendar.JULY, 25);

    private Film theDarkKnightFilm1, inceptionFilm2, endgameFilm3, titanicFilm4, avengersFilm5, avatarFilm6,
            getOutFilm7, blackPantherFilm8, callMeByYourNameFilm9, interstellarFilm10;
    private LectureTheater lt1, lt2, lt3, lt4, lt5, lt6, lt7, lt8, lt9, lt10;
    private Show week1Show;

    ComboBox<String> dateSelectionCombo;
    ArrayList<VBox> filmVBoxes = new ArrayList<>();;

    Stage mainStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Cinema Enterprise");
        setShow();

        dateSelectionCombo = new ComboBox<>();
        dateSelectionCombo.getItems().addAll("4th July", "11th July", "18th July");

        HBox topHBox = new HBox(new Label("Please select a date" ), dateSelectionCombo);
        topHBox.setAlignment(Pos.CENTER);
        topHBox.setSpacing(5);
        topHBox.setPadding(new Insets(10));

        ScrollPane middleScrollPane = new ScrollPane(showFilms(week1Show));

        Button checkoutButton = new Button("Check Out");
        HBox bottomHBox = new HBox(checkoutButton);
        bottomHBox.setAlignment(Pos.CENTER);
        bottomHBox.setPadding(new Insets(10));

        VBox mainGUI = new VBox(topHBox, middleScrollPane, bottomHBox);
        Scene scene = new Scene(mainGUI, 730, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
        mainStage = primaryStage;
    }

    private void setShow(){
        //Initialize the films
        theDarkKnightFilm1 = new Film("The Dark Knight", "2008",5.80, "12");
        theDarkKnightFilm1.setPoster(new Image("file:TheDarkKnight.jpg"));

        inceptionFilm2 = new Film("Inception", "2010", 6.00, "12");
        inceptionFilm2.setPoster(new Image("file:Inception.jpg"));

        endgameFilm3 = new Film("Avengers: End Game", "2019", 7.50, "12");
        endgameFilm3.setPoster(new Image("file:Endgame.jpg"));

        titanicFilm4 = new Film("Titanic", "1997", 4.50, "12");
        titanicFilm4.setPoster(new Image("file:titanic.jpg"));

        avengersFilm5 = new Film("The Avengers", "2012", 4.50, "12A");
        avengersFilm5.setPoster(new Image("file:Avengers.jpg"));

        avatarFilm6 = new Film("Avatar", "2009", 5.00, "12A");
        avatarFilm6.setPoster(new Image("file:Avatar.jpg"));

        getOutFilm7 = new Film("Get Out", "2017", 6.00, "15");
        getOutFilm7.setPoster(new Image("file:GetOut.png"));

        blackPantherFilm8 = new Film("Black Panther", "2018", 6.00, "!2A");
        blackPantherFilm8.setPoster(new Image("file:BlackPanther.jpg"));

        callMeByYourNameFilm9 = new Film("Call Me By Your Name", "2017", 4.50, "15");
        callMeByYourNameFilm9.setPoster(new Image("file:CallMeByYourName.jpg"));

        interstellarFilm10 = new Film("Interstellar", "2014", 3.50, "12");
        interstellarFilm10.setPoster(new Image("file:Interstellar.jpg"));

        //Initialize the lecture halls
        lt1 = new LectureTheater("LT1", 200);
        lt2 = new LectureTheater("LT2", 130);
        lt3 = new LectureTheater("LT3", 250);
        lt4 = new LectureTheater("LT4", 300);
        lt5 = new LectureTheater("LT5", 350);
        lt6 = new LectureTheater("LT6", 220);
        lt7 = new LectureTheater("LT7", 280);
        lt8 = new LectureTheater("LT8", 190);
        lt9 = new LectureTheater("LT9", 310);
        lt10 = new LectureTheater("LT10", 275);

        //initialize the shows
        week1Show = new Show(sat1);

        //Add theaters to the show
        week1Show.addLectureTheaters(lt1, lt2, lt3, lt4, lt5, lt6, lt7, lt8, lt9, lt10);

        //Set films in the lecture theater
        week1Show.addFilms(theDarkKnightFilm1, inceptionFilm2, endgameFilm3, titanicFilm4, avengersFilm5, avatarFilm6,
                getOutFilm7, blackPantherFilm8, callMeByYourNameFilm9, interstellarFilm10);
    }

    private VBox showFilms(Show show) {
        ArrayList<Film> filmsToDisplay = show.getAllFilms();
        ArrayList<LectureTheater> lectureTheaters = show.getLectureTheaters();
        NumberFormat gb = NumberFormat.getCurrencyInstance(Locale.UK);

        for(int i=0; i<Show.SHOWS_PER_WEEK; i++) {
            VBox filmVBox;
            Border outlineBorder = new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, null, null));
            Border noOutlineBorder = new Border(new BorderStroke(null, null, null, null));
            Film film = filmsToDisplay.get(i);
            LectureTheater lt = lectureTheaters.get(i);

            ImageView imageView = new ImageView(film.getPoster());
            imageView.setFitWidth(200);
            imageView.setFitHeight(300);

            filmVBox = new VBox(imageView, new Label(film.getFilmName() + ", " + film.getFilmReleaseYear()), new Label(lt.getName()),
                    new Label("" + gb.format(film.getPrice())));
            filmVBox.setAlignment(Pos.CENTER);
            filmVBox.setSpacing(5);
            filmVBox.setPadding(new Insets(10, 10, 10, 10));
            filmVBox.setOnMouseEntered(mouseEvent -> filmVBox.setBorder(outlineBorder) );
            filmVBox.setOnMouseExited(mouseEvent -> filmVBox.setBorder(noOutlineBorder) );
            filmVBox.setOnMouseClicked(mouseEvent -> filmSelected(film));

            filmVBoxes.add(filmVBox);
        }

        HBox middleHBox1 = new HBox(filmVBoxes.get(0), filmVBoxes.get(1), filmVBoxes.get(2));
        middleHBox1.setAlignment(Pos.CENTER);
        middleHBox1.setSpacing(2);
        middleHBox1.setPadding(new Insets(10, 10, 10, 10));

        HBox middleHBox2 = new HBox(filmVBoxes.get(3), filmVBoxes.get(4), filmVBoxes.get(5));
        middleHBox2.setAlignment(Pos.CENTER);
        middleHBox2.setSpacing(2);
        middleHBox2.setPadding(new Insets(10, 10, 10, 10));

        HBox middleHBox3 = new HBox(filmVBoxes.get(6), filmVBoxes.get(7), filmVBoxes.get(8));
        middleHBox3.setAlignment(Pos.CENTER);
        middleHBox3.setSpacing(2);
        middleHBox3.setPadding(new Insets(10, 10, 10, 10));

        HBox middleHBox4 = new HBox(filmVBoxes.get(9));
        middleHBox1.setSpacing(2);
        middleHBox1.setPadding(new Insets(10, 10, 10, 10));

        VBox middleVBox = new VBox(middleHBox1, middleHBox2, middleHBox3, middleHBox4);
        middleVBox.setAlignment(Pos.CENTER);
        return middleVBox;
    }

    private void filmSelected(Film film) {
        FilmDialog filmDialog = new FilmDialog(film);
        filmDialog.initModality(Modality.APPLICATION_MODAL);
        filmDialog.initOwner(mainStage);
        filmDialog.showAndWait();
    }
}
