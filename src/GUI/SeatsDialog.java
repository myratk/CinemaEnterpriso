package GUI;

import Domain.LectureTheater;
import Domain.Show;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

public class SeatsDialog extends Stage {
    private static final double SEATS_IN_A_ROW = 20;
    private Button doneButton;
    //private final Border outlineBorder = new Border(new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, null, null));
    //private final Border notOutlineBorder = new Border(new BorderStroke(null, null, null, null));
    private ArrayList<Integer> seatsChosen = new ArrayList<>();
    private boolean doneChosen = false;

    public SeatsDialog(ArrayList<Integer> seatNumbers, LectureTheater lectureTheater, int tickets, Show show) {
        this.setTitle("Choose your seats");

        if (!(seatNumbers == null))
            seatsChosen = seatNumbers;
        int totalSeats = lectureTheater.getTotalSeats();
        int count = 0;


        Image blueSeatIcon = new Image("file:blueIcon.png");
        Image redSeatIcon = new Image("file:redIcon.png");
        Image graySeatIcon = new Image("file:grayIcon.png");

        VBox allSeatsVBox = new VBox();
        for(int i=0; i<(totalSeats/20); i++) {
            HBox rowHBox = new HBox();
            for(int j=0; j<SEATS_IN_A_ROW; j++) {
                VBox seatVBox = new VBox();
                int seatNum = count;
                if (!(seatsChosen == null) && seatsChosen.contains(Integer.valueOf(count))) {
                    ImageView seatIV = new ImageView(graySeatIcon);
                    seatIV.setFitWidth(20); seatIV.setFitHeight(20);
                    seatVBox.getChildren().setAll(seatIV);
                    seatVBox.setOnMouseClicked(mouseEvent -> {
                        if (seatsChosen.contains(seatNum)) {
                            ImageView seatChosenIV = new ImageView(blueSeatIcon);
                            seatChosenIV.setFitWidth(20);
                            seatChosenIV.setFitHeight(20);
                            seatVBox.getChildren().setAll(seatChosenIV);
                            seatsChosen.remove(Integer.valueOf(seatNum));
                            if (seatsChosen.size() == tickets)
                                doneButton.setDisable(false);
                            else
                                doneButton.setDisable(true);
                        } else if (!seatsChosen.contains(seatNum) && !(seatsChosen.size() >= tickets)) {
                            seatVBox.getChildren().setAll(seatIV);
                            seatsChosen.add(seatNum);
                            if (seatsChosen.size() == tickets)
                                doneButton.setDisable(false);
                            else
                                doneButton.setDisable(true);
                    }
                    });
                }
                else if (show.getSeatFull(lectureTheater, count)) {
                    ImageView seatIV = new ImageView(redSeatIcon);
                    seatIV.setFitWidth(20); seatIV.setFitHeight(20);
                    seatVBox.getChildren().setAll(seatIV);
                }
                else {
                    ImageView seatIV = new ImageView(blueSeatIcon);
                    seatIV.setFitWidth(20); seatIV.setFitHeight(20);
                    seatVBox.getChildren().setAll(seatIV);
                    seatVBox.setOnMouseClicked(mouseEvent -> {
                        if (!seatsChosen.contains(seatNum) && !(seatsChosen.size() >= tickets)) {
                            ImageView seatChosenIV = new ImageView(graySeatIcon);
                            seatChosenIV.setFitWidth(20);
                            seatChosenIV.setFitHeight(20);
                            seatVBox.getChildren().setAll(seatChosenIV);
                            seatsChosen.add(seatNum);
                            if (seatsChosen.size() == tickets)
                                doneButton.setDisable(false);
                            System.out.println("first if");
                        }
                        else if (seatsChosen.contains(seatNum)){
                            seatVBox.getChildren().setAll(seatIV); //chnage to blue seat icon
                            seatsChosen.remove(Integer.valueOf(seatNum));
                            if (seatsChosen.size() == tickets)
                                doneButton.setDisable(false);
                            else
                                doneButton.setDisable(true);
                            System.out.println("sec if");
                        }
                        if (seatsChosen.size() == tickets)
                            doneButton.setDisable(false);
                    });
                }

                rowHBox.getChildren().add(seatVBox);
                if (j==9) rowHBox.getChildren().add(new Label("              "));
                count++;
            }
            rowHBox.setSpacing(5);
            rowHBox.setPadding(new Insets(10, 10, 10, 10));
            rowHBox.setAlignment(Pos.CENTER);
            allSeatsVBox.getChildren().add(rowHBox);
        }
        ImageView screenIV = new ImageView(new Image("file:screen.png"));
        screenIV.setFitHeight(50); screenIV.setFitWidth(350);
        allSeatsVBox.getChildren().addAll(new Label(""), screenIV, new Label("Screen"));
        allSeatsVBox.setSpacing(10);
        allSeatsVBox.setPadding(new Insets(10, 10, 10, 10));
        allSeatsVBox.setAlignment(Pos.CENTER);

        doneButton = new Button("Done");
        doneButton.setOnAction(actionEvent -> {
            doneChosen = true;
            this.close();
        } );
        if (!(seatsChosen.size() == tickets))
            doneButton.setDisable(true);

        VBox mainVBox = new VBox(new Label("Choose your seats"), allSeatsVBox, doneButton);
        mainVBox.setAlignment(Pos.CENTER);
        mainVBox.setPadding(new Insets(20, 20, 20, 20));
        mainVBox.setSpacing(15);

        Scene scene = new Scene(mainVBox);
        this.setScene(scene);
    }

    public ArrayList<Integer> getSeatsChosen() {
        return seatsChosen;
    }

    public boolean getDoneChosen() {
        return doneChosen;
    }
}
