package ecobike.views;

import ecobike.entities.Bike;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class BikeDetailScreenHandler implements Initializable {
    @FXML
    private TextField bikeCode, bikeType, parkingLot, licensePlate, cost, deposit;

    @FXML
    private TextArea extraInfo;

    @FXML
    private Button okBtn ;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void init(Bike bike){
        bikeCode.setText(String.valueOf(bike.getBikeCode()));
        bikeType.setText(bike.getType());
        cost.setText(String.valueOf(bike.getPrice()));
        parkingLot.setText(String.valueOf(bike.getParkId()));
        licensePlate.setText(bike.getLicensePlateNumber());
        deposit.setText(String.valueOf(bike.getDeposit()));

    }

    public void back() {
        Stage stage = (Stage) okBtn.getScene().getWindow();
        stage.close();
    }
}