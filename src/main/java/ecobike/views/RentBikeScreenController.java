package ecobike.views;

import ecobike.controllers.RentBikeController;
import ecobike.entities.Bike;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.util.Pair;

public class RentBikeScreenController {
    @FXML
    private TextField barcode;

    public void handleRentBike() {
        RentBikeController rentBikeController = new RentBikeController();
        rentBikeController.handleRentBike(barcode);
    }
}
