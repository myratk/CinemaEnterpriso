package GUI;

import Domain.*;
import Domain.Customer.CustomerOrder;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class CinemaEnterpriseMainGUI extends Application {
    private MovieDate sat1 = new MovieDate(2020, Calendar.JULY, 4);
    private MovieDate sat2 = new MovieDate(2020, Calendar.JULY, 11);
    private MovieDate sat3 = new MovieDate(2020, Calendar.JULY, 18);
    private MovieDate sat4 = new MovieDate(2020, Calendar.JULY, 25);

    private Film theDarkKnightFilm1, inceptionFilm2, endgameFilm3, titanicFilm4, avengersFilm5, avatarFilm6,
            getOutFilm7, blackPantherFilm8, callMeByYourNameFilm9, interstellarFilm10;
    private LectureTheater lt1, lt2, lt3, lt4, lt5, lt6, lt7, lt8, lt9, lt10;
    private Show week1Show;

    CustomerOrder customerOrder;

    ComboBox<MovieDate> dateSelectionCombo;
    ArrayList<VBox> filmVBoxes = new ArrayList<>();;

    Stage mainStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Cinema Enterprise");
        setShow();
        customerOrder = new CustomerOrder();

        dateSelectionCombo = new ComboBox<>();
        dateSelectionCombo.getItems().addAll(sat1, sat2, sat3, sat4);
        dateSelectionCombo.getSelectionModel().select(sat1);

        HBox topHBox = new HBox(new Label("Please select a date" ), dateSelectionCombo);
        topHBox.setAlignment(Pos.CENTER);
        topHBox.setSpacing(5);
        topHBox.setPadding(new Insets(10));

        ScrollPane middleScrollPane = new ScrollPane(showFilms(week1Show));

        Button checkoutButton = new Button();
        Image basketImage = new Image("file:cart.png");
        ImageView basketImageView = new ImageView(basketImage);
        basketImageView.setFitHeight(30);
        basketImageView.setFitWidth(60);
        checkoutButton.setGraphic(basketImageView);
        checkoutButton.setOnAction(actionEvent -> checkoutPressed() );
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
        theDarkKnightFilm1.setSynopsis("With the help of allies Lt. Jim Gordon (Gary Oldman) and DA Harvey Dent (Aaron Eckhart), " +
                "Batman (Christian Bale) has been able to keep a tight lid on crime in Gotham City. But when a vile young criminal " +
                "calling himself the Joker (Heath Ledger) suddenly throws the town into chaos, the caped Crusader begins to tread a " +
                "fine line between heroism and vigilantism.");

        inceptionFilm2 = new Film("Inception", "2010", 6.00, "12");
        inceptionFilm2.setPoster(new Image("file:Inception.jpg"));
        inceptionFilm2.setSynopsis("Dom Cobb (Leonardo DiCaprio) is a thief with the rare ability to enter people's dreams and " +
                "steal their secrets from their subconscious. His skill has made him a hot commodity in the world of corporate " +
                "espionage but has also cost him everything he loves. Cobb gets a chance at redemption when he is offered a " +
                "seemingly impossible task: Plant an idea in someone's mind. If he succeeds, it will be the perfect crime, but a " +
                "dangerous enemy anticipates Cobb's every move.");

        endgameFilm3 = new Film("Avengers: End Game", "2019", 7.50, "12");
        endgameFilm3.setPoster(new Image("file:Endgame.jpg"));
        endgameFilm3.setSynopsis("Adrift in space with no food or water, Tony Stark sends a message to Pepper Potts as his oxygen supply " +
                "starts to dwindle. Meanwhile, the remaining Avengers -- Thor, Black Widow, Captain America and Bruce Banner -- must " +
                "figure out a way to bring back their vanquished allies for an epic showdown with Thanos -- the evil demigod who " +
                "decimated the planet and the universe.");

        titanicFilm4 = new Film("Titanic", "1997", 4.50, "12");
        titanicFilm4.setPoster(new Image("file:titanic.jpg"));
        titanicFilm4.setSynopsis("James Cameron's \"Titanic\" is an epic, action-packed romance set against the ill-fated maiden " +
                "voyage of the R.M.S. Titanic; the pride and joy of the White Star Line and, at the time, the largest moving object " +
                "ever built. She was the most luxurious liner of her era -- the \"ship of dreams\" -- which ultimately carried over " +
                "1,500 people to their death in the ice cold waters of the North Atlantic in the early hours of April 15, 1912.");

        avengersFilm5 = new Film("The Avengers", "2012", 4.50, "12A");
        avengersFilm5.setPoster(new Image("file:Avengers.jpg"));
        avengersFilm5.setSynopsis("When Thor's evil brother, Loki (Tom Hiddleston), gains access to the unlimited power of the energy " +
                "cube called the Tesseract, Nick Fury (Samuel L. Jackson), director of S.H.I.E.L.D., initiates a superhero recruitment " +
                "effort to defeat the unprecedented threat to Earth. Joining Fury's \"dream team\" are Iron Man (Robert Downey Jr.), " +
                "Captain America (Chris Evans), the Hulk (Mark Ruffalo), Thor (Chris Hemsworth), the Black Widow (Scarlett Johansson) " +
                "and Hawkeye (Jeremy Renner).");

        avatarFilm6 = new Film("Avatar", "2009", 5.00, "12A");
        avatarFilm6.setPoster(new Image("file:Avatar.jpg"));
        avatarFilm6.setSynopsis("On the lush alien world of Pandora live the Na'vi, beings who appear primitive but are highly evolved. " +
                "Because the planet's environment is poisonous, human/Na'vi hybrids, called Avatars, must link to human minds to " +
                "allow for free movement on Pandora. Jake Sully (Sam Worthington), a paralyzed former Marine, becomes mobile again " +
                "through one such Avatar and falls in love with a Na'vi woman (Zoe Saldana). As a bond with her grows, he is drawn " +
                "into a battle for the survival of her world.");

        getOutFilm7 = new Film("Get Out", "2017", 6.00, "15");
        getOutFilm7.setPoster(new Image("file:GetOut.png"));
        getOutFilm7.setSynopsis("Now that Chris (Daniel Kaluuya) and his girlfriend, Rose (Allison Williams), have reached the meet-" +
                "the-parents milestone of dating, she invites him for a weekend getaway upstate with Missy and Dean. At first, Chris " +
                "reads the family's overly accommodating behavior as nervous attempts to deal with their daughter's interracial relationship, " +
                "but as the weekend progresses, a series of increasingly disturbing discoveries lead him to a truth that he never could have " +
                "imagined.");

        blackPantherFilm8 = new Film("Black Panther", "2018", 6.00, "!2A");
        blackPantherFilm8.setPoster(new Image("file:BlackPanther.jpg"));
        blackPantherFilm8.setSynopsis("After the death of his father, T'Challa returns home to the African nation of Wakanda to take " +
                "his rightful place as king. When a powerful enemy suddenly reappears, T'Challa's mettle as king -- and as Black Panther" +
                " -- gets tested when he's drawn into a conflict that puts the fate of Wakanda and the entire world at risk. Faced with " +
                "treachery and danger, the young king must rally his allies and release the full power of Black Panther to defeat his foes " +
                "and secure the safety of his people.");

        callMeByYourNameFilm9 = new Film("Call Me By Your Name", "2017", 4.50, "15");
        callMeByYourNameFilm9.setPoster(new Image("file:CallMeByYourName.jpg"));
        callMeByYourNameFilm9.setSynopsis("It's the summer of 1983, and precocious 17-year-old Elio Perlman is spending the days with " +
                "his family at their 17th-century villa in Lombardy, Italy. He soon meets Oliver, a handsome doctoral student who's working " +
                "as an intern for Elio's father. Amid the sun-drenched splendor of their surroundings, Elio and Oliver discover the heady " +
                "beauty of awakening desire over the course of a summer that will alter their lives forever.");

        interstellarFilm10 = new Film("Interstellar", "2014", 3.50, "12");
        interstellarFilm10.setPoster(new Image("file:Interstellar.jpg"));
        interstellarFilm10.setSynopsis("In Earth's future, a global crop blight and second Dust Bowl are slowly rendering the planet " +
                "uninhabitable. Professor Brand (Michael Caine), a brilliant NASA physicist, is working on plans to save mankind by " +
                "transporting Earth's population to a new home via a wormhole. But first, Brand must send former NASA pilot Cooper " +
                "(Matthew McConaughey) and a team of researchers through the wormhole and across the galaxy to find out which of " +
                "three planets could be mankind's new home.");

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
            filmVBox.setOnMouseClicked(mouseEvent -> filmSelected(show, lt, film) );

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

    private void filmSelected(Show show, LectureTheater lectureTheater, Film film) {
        FilmDialog filmDialog = new FilmDialog(show, lectureTheater, film, customerOrder);
        filmDialog.initModality(Modality.APPLICATION_MODAL);
        filmDialog.initOwner(mainStage);
        filmDialog.showAndWait();
    }

    private void checkoutPressed() {
        if(customerOrder.isBookingsEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Your basket is empty", ButtonType.OK);
            alert.show();
        }
        else {
            customerOrder.setDate(new MovieDate());
            BasketDialog basketDialog = new BasketDialog(customerOrder);
            basketDialog.initModality(Modality.APPLICATION_MODAL);
            basketDialog.initOwner(mainStage);
            basketDialog.showAndWait();
        }
    }

}
