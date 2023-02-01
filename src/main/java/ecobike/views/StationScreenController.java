package ecobike.views;

import ecobike.entities.Bike;
import ecobike.entities.ParkingLot;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StationScreenController implements Initializable {
    private ParkingLot parkingLot;

    @FXML
    private ListView<String> bikesView;

    @FXML
    private TextField parkingLotName, parkingLotLocation, numberPlace, numberBike;

    @FXML
    private ImageView imageBike;

    @FXML
    private Button rentBIkeBtn;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Show Station Screen");

        bikesView.setOnMouseClicked(click -> {
            if (click.getClickCount() == 2){
                handleClickBike();
            }
        });

    }

    public void initData(ParkingLot parkingLot){
        System.out.println("Initilize parkingLot info and bikes info");
        this.parkingLot = parkingLot;
        parkingLotName.setText(parkingLot.getName());
        parkingLotLocation.setText(parkingLot.getLocation());
        numberPlace.setText(Integer.toString(parkingLot.getTotalSlots()));
        numberBike.setText(Integer.toString(parkingLot.getNumberOfBikes()));
        ArrayList<Bike> bikes = parkingLot.getBikes();
        for (Bike bike: bikes) {
            if (!bike.isIs_rented())
                bikesView.getItems().add(bike.getGeneralInfo());
        }
    }
    private void handleClickBike() {
        System.out.println("User click to view bike detail");
        String bikeInfo = bikesView.getSelectionModel().getSelectedItem();
        Bike bike = getBikeFromString(bikeInfo);

        showDetailBikeScreen(bike);

    }
    private void showDetailBikeScreen(Bike bike){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ecobike/BikeDetailScreen.fxml"));
            Parent root = loader.load();

            BikeDetailScreenController viewDetailBikeController = loader.getController();
            viewDetailBikeController.init(bike);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.setTitle("Detail Bike Screen");
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private Bike getBikeFromString(String bikeInfo) {
        for (Bike bike: parkingLot.getBikes()){
            String s = bike.getGeneralInfo();
            if (bikeInfo.equals(s)) {
                return bike;
            }
        }
        return null;
    }
    public void handleClickRentBikeBtn() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ecobike/RentBikeScreen.fxml"));
            Parent root = loader.load();

            RentBikeScreenController rentBikeScreenController = loader.getController();
            rentBikeScreenController.setParkingLot(parkingLot);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.setTitle("Rent Bike Screen");
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
