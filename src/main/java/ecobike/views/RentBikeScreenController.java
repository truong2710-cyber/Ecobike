package ecobike.views;

import ecobike.controllers.RentBikeController;
import ecobike.entities.Bike;
import ecobike.entities.ParkingLot;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.util.Pair;

public class RentBikeScreenController {
    private ParkingLot parkingLot;

    @FXML
    private TextField barcode;

    public ParkingLot getParkingLot() {
        return this.parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public void handleRentBike() {
        RentBikeController rentBikeController = new RentBikeController(parkingLot);
        rentBikeController.handleRentBike(barcode.getText());
    }
}
