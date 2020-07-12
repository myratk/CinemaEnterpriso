package GUI;

import Domain.Customer.CustomerOrder;
import javafx.stage.Stage;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Random;

public class ReceiptDialog extends Stage {
    public ReceiptDialog(CustomerOrder customerOrder) {
        this.setTitle("Receipt");

        NumberFormat gb = NumberFormat.getCurrencyInstance(Locale.UK);
        /*
        Label uniLabel = new Label("Enterprising University");
        uniLabel.setFont(new Font(20));
        HBox detailHBox = new HBox(new Label("Invoice no: " + generateInvoiceNum()), new Label("Date: " + customerOrder.getDate()));
        detailHBox.setAlignment(Pos.CENTER);
        detailHBox.setSpacing(40);
        detailHBox.setPadding(new Insets(10, 10, 10, 10));

        VBox topVBox = new VBox(uniLabel, detailHBox);
        topVBox.setAlignment(Pos.CENTER);
        topVBox.setSpacing(10);
        topVBox.setPadding(new Insets(20));

        Label filmLabel = new Label("Film");
        filmLabel.setPrefWidth(200);
        filmLabel.setAlignment(Pos.CENTER_LEFT);
        Label ticketLabel = new Label("Tickets");
        ticketLabel.setPrefWidth(70);
        ticketLabel.setAlignment(Pos.CENTER_LEFT);
        Label costLabel = new Label("Amount");
        costLabel.setPrefWidth(70);
        costLabel.setAlignment(Pos.CENTER_RIGHT);
        HBox headingOrderHBox = new HBox(filmLabel, ticketLabel, costLabel);

        VBox orderVBox = new VBox();
        for(int i=0; i<customerOrder.getFilmsPurchased().size(); i++) {
            filmLabel = new Label(customerOrder.getFilmsPurchased(i).getFilmName());
            filmLabel.setPrefWidth(200);
            filmLabel.setAlignment(Pos.CENTER_LEFT);

            ticketLabel = new Label("" + customerOrder.getTicketsPurchased(i));
            ticketLabel.setPrefWidth(70);
            ticketLabel.setAlignment(Pos.CENTER_LEFT);

            costLabel = new Label(gb.format(customerOrder.getTotalOne(i)));
            costLabel.setPrefWidth(70);
            costLabel.setAlignment(Pos.CENTER_RIGHT);

            HBox itemHBox = new HBox(filmLabel, ticketLabel, costLabel);

            orderVBox.getChildren().add(itemHBox);
        }
        orderVBox.setSpacing(10);
        orderVBox.setAlignment(Pos.CENTER);

        ScrollPane middleScrollPane = new ScrollPane(orderVBox);
        middleScrollPane.setPrefWidth(350);

        VBox middleVBox = new VBox(headingOrderHBox, middleScrollPane);
        middleVBox.setPadding(new Insets(10, 10, 10, 10));

        VBox mainVBox = new VBox (topVBox, middleVBox);
        Scene scene = new Scene(mainVBox, 400, 500);
        this.setScene(scene);

         */
    }

    private String generateInvoiceNum() {
        String randomNum = "";
        for (int i=0; i<7; i++) {
            Random rand = new Random();
            randomNum += rand.nextInt(10);
        }
        return randomNum;
    }
}
