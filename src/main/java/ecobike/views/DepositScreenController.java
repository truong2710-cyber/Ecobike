package ecobike.views;

import ecobike.controllers.RentBikeController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class DepositScreenController {
    @FXML
    private TextField text1, text2, text3, text4;

    public void handlePayment() {
        RentBikeController rentBikeController = new RentBikeController();
        rentBikeController.handlePayment();
    }

}
