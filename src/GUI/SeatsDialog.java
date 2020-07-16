package GUI;

import Domain.Customer.CustomerBasket;
import Domain.Film;
import Domain.LectureTheater;
import Domain.Show;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class SeatsDialog extends Stage {
    private ArrayList<Integer> seatsChosen = new ArrayList<>();
    private ArrayList<CheckBox> checkBoxes = new ArrayList<>();
    private int noOfSeatsChosen = 0;

    private Button doneButton;
    private boolean doneChosen = false;

    public SeatsDialog(ArrayList<Integer> seatNumbers, LectureTheater lectureTheater, int tickets) {
        this.setTitle("Choose your seats");

        if (!(seatNumbers == null))
            seatsChosen = seatNumbers;
        HBox topHBox = new HBox();
        for (int i=0; i<lectureTheater.getTotalSeats(); i++) {
            CheckBox checkBox = new CheckBox("" + (i+1));
            checkBoxes.add(checkBox);
            if (seatsChosen.contains(Integer.valueOf(i))) {
                checkBox.setSelected(true);
            }
            checkBox.setOnMouseClicked(mouseEvent -> {
                int seatNum = checkBoxes.indexOf(checkBox);
                if (checkBox.isSelected()) {
                    seatsChosen.add(Integer.valueOf(seatNum));
                    if (seatsChosen.size() == tickets) {
                        doneButton.setDisable(false);
                    } else if (seatsChosen.size() > tickets) {
                        checkBox.setSelected(false);
                        seatsChosen.remove(Integer.valueOf(seatNum));
                    }
                } else {
                    seatsChosen.remove(Integer.valueOf(seatNum));
                    if (seatsChosen.size() == tickets)
                        doneButton.setDisable(false);
                    else
                        doneButton.setDisable(true);
                }
            });
            topHBox.getChildren().add(checkBox);
        }
        topHBox.setSpacing(10);

        doneButton = new Button("Done");
        doneButton.setOnAction(actionEvent -> {
            doneChosen = true;
            this.close();
        } );
        if (seatsChosen.size() == tickets) {
            doneButton.setDisable(false);
        }
        else {
            doneButton.setDisable(true);
        }

        VBox mainVBox = new VBox(topHBox, doneButton);
        Scene scene = new Scene(mainVBox, 500, 500);
        this.setScene(scene);
    }

    public ArrayList<Integer> getSeatsChosen() {
        return seatsChosen;
    }

    public boolean getDoneChosen() {
        return doneChosen;
    }
}
