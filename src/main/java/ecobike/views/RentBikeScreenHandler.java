package ecobike.views;

import java.io.IOException;

import ecobike.controllers.RentBikeController;
import ecobike.database_services.BikeDatabaseService;
import ecobike.entities.Bike;
import ecobike.entities.ParkingLot;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RentBikeScreenHandler {
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
        boolean RentConfirmation = rentBikeController.handleRentBike(barcode.getText());
        if (RentConfirmation) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ecobike/DepositScreen.fxml"));
                Parent root = loader.load();

                DepositScreenHandler depositScreenHandler = loader.getController();
                depositScreenHandler.setBikeID(rentBikeController.getBikeID());
                Bike bike = BikeDatabaseService.getBikeByID(rentBikeController.getBikeID());
                depositScreenHandler.getText4().setText(String.valueOf(bike.getDeposit()));

                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(new Scene(root));
                stage.setTitle("Deposit Screen");
                stage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
