package ecobike.views;

import ecobike.controllers.ViewBikeController;
import ecobike.database_services.BikeDatabaseService;
import ecobike.database_services.ParkingLotDatabaseService;
import ecobike.entities.Bike;
import ecobike.entities.ParkingLot;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
public class RentedBikeInfoScreenHandler implements Initializable{
    @FXML
    private Button returnButton, returnScreen, pauseBtn;

    @FXML
    private TextField parkingLot, cost, deposit, rentedTime, totalFee;

    @FXML
    private ImageView imageView;
    private String rental_id;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void init(Bike bike){
        rental_id = BikeDatabaseService.getRentalID(bike.getBikeCode());
        cost.setText(String.valueOf(bike.getPrice()));
        deposit.setText(String.valueOf(bike.getDeposit()));
        ParkingLot rentedParkingLot = ParkingLotDatabaseService.getParkingLotByID(Integer.toString(bike.getParkId()));
        parkingLot.setText(rentedParkingLot.getName());

        int[] rentedInfo = ViewBikeController.getRentedInfo(rental_id);
        rentedTime.setText(String.valueOf(rentedInfo[0]));
        totalFee.setText(String.valueOf(rentedInfo[1]));
        String url = ViewBikeController.getImageURL(bike);
        Image image = new Image(getClass().getResource(url).toExternalForm());
        imageView.setImage(image);
        //TODO: get info to show
        ViewBikeController.setupButton(returnButton);
        ViewBikeController.setupButton(returnScreen);
        ViewBikeController.setupButton(pauseBtn);
    }
    public void pressReturnButton() throws IOException {
//        Stage stage = (Stage) returnButton.getScene().getWindow();
//        stage.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ecobike/ReturnBikeScreen.fxml"));
        Parent root = loader.load();
        ReturnBikeScreenHandler returnBikeScreenHandler = loader.getController();
        ArrayList<ParkingLot> parkingLots = ParkingLotDatabaseService.getAllParkingLotsNotFull();
        returnBikeScreenHandler.init(parkingLots, rental_id);

        Stage new_stage = new Stage();
        new_stage.initModality(Modality.APPLICATION_MODAL);
        new_stage.setScene(new Scene(root));
        new_stage.setTitle("Return Bike Screen");
        new_stage.showAndWait();
    }

    public void back() {
        Stage stage = (Stage) returnScreen.getScene().getWindow();
        stage.close();
    }

}
