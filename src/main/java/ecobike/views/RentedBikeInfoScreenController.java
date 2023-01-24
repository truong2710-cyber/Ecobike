package ecobike.views;

import ecobike.databaseservices.BikeDA;
import ecobike.databaseservices.ParkingLotDA;
import ecobike.entities.Bike;
import ecobike.entities.ParkingLot;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
public class RentedBikeInfoScreenController implements Initializable{
    @FXML
    private Button returnButton;
    private String rental_id;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void init(Bike bike){
        rental_id = BikeDA.getRentalID(bike.getBikeCode());
        //TODO: get info to show
    }
    public void pressReturnButton() throws IOException {
        Stage stage = (Stage) returnButton.getScene().getWindow();
        stage.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ecobike/ReturnBikeScreen.fxml"));
        Parent root = loader.load();
        ReturnBikeScreenController returnBikeScreenController = loader.getController();
        ArrayList<ParkingLot> parkingLots = ParkingLotDA.getAllParkingLots();
        returnBikeScreenController.init(parkingLots, rental_id);

        Stage new_stage = new Stage();
        new_stage.initModality(Modality.APPLICATION_MODAL);
        new_stage.setScene(new Scene(root));
        new_stage.setTitle("Return Bike Screen");
        new_stage.showAndWait();
    }
}
