package GUI;

import Domain.Customer.CustomerOrder;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SnacksDialog extends Stage {
    public SnacksDialog(CustomerOrder customerOrder) {
        this.setTitle("Snacks Menu");

        Label titleLabel = new Label("Movie Snacks Menu");
        titleLabel.setFont(new Font(24));
        VBox topVBox = new VBox(titleLabel);
        topVBox.setAlignment(Pos.CENTER);
        topVBox.setPadding(new Insets(10, 10, 10, 10));

        VBox mainVBox = new VBox(topVBox);
        Scene scene = new Scene(mainVBox, 600, 400);
        this.setScene(scene);
    }

    private void setSnack() {

    }
}
