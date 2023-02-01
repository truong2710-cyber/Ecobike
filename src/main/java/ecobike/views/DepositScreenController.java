package ecobike.views;

import ecobike.controllers.RentBikeController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class DepositScreenController {
    @FXML
    private TextField text1, text2, text3, text4;

    String bikeID;

    public String getBikeID() {
        return bikeID;
    }
    public void setBikeID(String bikeID) {
        this.bikeID = bikeID;
    }

    public void handlePayment() {
        RentBikeController rentBikeController = new RentBikeController();
        rentBikeController.handlePayment(text1.getText(), text2.getText(), text3.getText(), text4.getText(), bikeID);
    }

}
