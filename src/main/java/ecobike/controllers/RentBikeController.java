package ecobike.controllers;

import ecobike.databaseconnection.MySQLDB;
import ecobike.entities.ParkingLot;
import ecobike.subsystems.barcodesubsystem.BarcodeConverterController;
import ecobike.views.box.NotificationBox;
import ecobike.views.box.ConfirmBox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class RentBikeController {
    private ParkingLot parkingLot;

    @FXML
    private TextField barcode;

    public RentBikeController(ParkingLot parkingLot, TextField barcode) {
        this.parkingLot = parkingLot;
        this.barcode = barcode;
    }

    public RentBikeController() {

    }

    public void handleRentBike() {
        String barcodeString = barcode.getText();
        BarcodeConverterController barcodeConverterController = new BarcodeConverterController();
        if (barcodeString.isEmpty()) {
            NotificationBox.display("NotificationBox", "Vui lòng nhập barcode của xe!");
        } else {
            int bikecode = barcodeConverterController.convertBarcodeToBikeCode(barcodeString);
            String command = "SELECT * FROM bike WHERE id = " + bikecode;
            ArrayList<ArrayList<String>> result = MySQLDB.query(command);
            System.out.println(result);
            if (result.isEmpty()) {
                NotificationBox.display("NotificationBox", "Xe không tồn tại!");
            } else if (result.get(0).get(12).equals("1")) {
                NotificationBox.display("NotificationBox", "Xe đang được thuê!");
            } else {
                boolean RentConfirmation;
                if (!result.get(0).get(11).equals(parkingLot.getID())) {
                    RentConfirmation = ConfirmBox.display("ConfirmBox", "Xe " + barcodeString + " đang ở bãi xe khác. Bạn có muốn thuê?");
                } else {
                    RentConfirmation = ConfirmBox.display("ConfirmBox", "Thuê xe " + barcodeString + " ?");
                }

                if(RentConfirmation) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ecobike/DepositScreen.fxml"));
                        Parent root = loader.load();

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
    }

    public void handlePayment() {
        System.out.println(0);
    }
}
