package GUI;

import Domain.CustomerOrder;
import javafx.stage.Stage;

public class BasketDialog extends Stage {
    public BasketDialog(CustomerOrder customerOrder) {
        this.setTitle("Your Basket");
    }
}
