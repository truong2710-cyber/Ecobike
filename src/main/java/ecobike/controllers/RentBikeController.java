package ecobike.controllers;

import ecobike.databaseconnection.MySQLDB;
import ecobike.subsystems.barcodesubsystem.BarcodeConverterController;
import ecobike.views.box.NotificationBox;
import ecobike.views.box.ConfirmBox;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class RentBikeController {
    public void handleRentBike(TextField barcode) {
        String barcodeString = barcode.getText();
        BarcodeConverterController barcodeConverterController = new BarcodeConverterController();
        if (barcodeString.isEmpty()) {
            NotificationBox.display("NotificationBox", "Vui lòng nhập barcode của xe!");
        } else {
            int bikecode = barcodeConverterController.convertBarcodeToBikeCode(barcodeString);
            String command = "SELECT * FROM bike WHERE id = " + bikecode;
            ArrayList<ArrayList<String>> result = MySQLDB.query(command);
            if (result.isEmpty()) {
                NotificationBox.display("NotificationBox", "Xe không tồn tại!");
            } else {
                boolean RentConfirmation = ConfirmBox.display("ConfirmBox", "Thuê xe " + barcodeString + " ?");

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
}
