package ecobike.views;

import ecobike.controllers.RentBikeController;
import ecobike.utils.TransactionInfoNotiBox;
import ecobike.views.box.NotificationBox;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class DepositScreenController {
    @FXML
    private TextField text1, text2, text3, text4, text5;

    String bikeID;

    public String getBikeID() {
        return bikeID;
    }
    public void setBikeID(String bikeID) {
        this.bikeID = bikeID;
    }

    public TextField getText4() {
        return text4;
    }
    public void setText4(TextField text4) {
        this.text4 = text4;
    }

    public void handlePayment() {
        RentBikeController rentBikeController = new RentBikeController();
        rentBikeController.setBikeID(bikeID);
        if (rentBikeController.checkCardOnRental(text1.getText())){
            NotificationBox.display("Error", "Card is being used in another rental.");
            return;
        }
        String respondCode = rentBikeController.handlePayment(text1.getText(), text2.getText(), text3.getText(), text4.getText(), text5.getText());
        TransactionInfoNotiBox.displayNotificationErrorCode(respondCode, "deposit");

    }

}
