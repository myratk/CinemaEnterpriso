package GUI;

import Domain.Customer.CustomerBasket;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ReceiptGUI extends Stage{
    public ReceiptGUI(CustomerBasket customerBasket, double change) {
        this.setTitle("Receipt");

        Button doneButton = new Button("Done");
        doneButton.setOnAction(actionEvent -> {
            CinemaEnterpriseMainGUI gui = new CinemaEnterpriseMainGUI();
            gui.start(new Stage());
            this.close();
        });
        VBox mainVBox = new VBox(new Label("" + customerBasket.getTotal()), new Label("" + change), doneButton);
        Scene scene = new Scene(mainVBox, 300, 600);
        this.setScene(scene);
    }
}
