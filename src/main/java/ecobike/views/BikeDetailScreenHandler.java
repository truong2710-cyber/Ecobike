package ecobike.views;

import ecobike.controllers.ViewBikeController;
import ecobike.entities.Bike;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Show detail Information of Bike
 * Showed when you double click in one bike from List View Bike of Station Screen
 * Initialize Rented Bike Information when you double click in one bike in List View Bike
 */
public class BikeDetailScreenHandler implements Initializable {
    @FXML
    private TextField bikeCode, bikeType, parkingLot, licensePlate, cost, deposit;

    @FXML
    private TextArea extraInfo;

    @FXML
    private Button okBtn ;

    @FXML
    private ImageView imageBike;

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
        String url = ViewBikeController.getImageURL(bike);
        Image image = new Image(getClass().getResource(url).toExternalForm());
        imageBike.setImage(image);
        ViewBikeController.setupButton(okBtn);
    }


    /**
     * Return back screen
     */
    public void back() {
        Stage stage = (Stage) okBtn.getScene().getWindow();
        stage.close();
    }
}