package ecobike.views;

import ecobike.controllers.ViewBikeController;
import ecobike.entities.Bike;
import ecobike.entities.ParkingLot;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Hanler of Station Screen
 * Screen will showed when user double click in one station in List View Station of Main Screen
 * The Screen will show Rent Bike SCreen when you click Rent Bike Buttion
 * The Screen will show Bike Detail SCreen when you double click one bike in List View Bike
 */
public class StationScreenHandler implements Initializable {
    private ParkingLot parkingLot;

    @FXML
    private ListView<String> bikesView;

    @FXML
    private TextField parkingLotName, parkingLotLocation, numberPlace, numberBike;

    @FXML
    private ImageView imageBike;

    @FXML
    private Button rentBIkeBtn, returnBtn;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Show ParkingLot Screen");

        bikesView.setOnMouseClicked(click -> {
            if (click.getClickCount() == 2){
                handleClickBike();
            }

            if (click.getClickCount() == 1) {
                setupImage();
            }
        });
    }

    /**
     * Initialize data for Screen
     * @param parkingLot
     *
     */
    public void initData(ParkingLot parkingLot){
        System.out.println("Initilize parkingLot info and bikes info");
        this.parkingLot = parkingLot;
        parkingLotName.setText(parkingLot.getName());
        parkingLotLocation.setText(parkingLot.getLocation());
        numberPlace.setText(Integer.toString(parkingLot.getTotalSlots()));
        numberBike.setText(Integer.toString(parkingLot.getNumberOfAvailableBikes()));
        ArrayList<Bike> bikes = parkingLot.getBikes();
        for (Bike bike: bikes) {
            if (!bike.isRented())
                bikesView.getItems().add(bike.getGeneralInfo());
        }

        ViewBikeController.setupListView(bikesView);
        ViewBikeController.setupButton(returnBtn);
        ViewBikeController.setupButton(rentBIkeBtn);
    }

    /**
     * Handler when user double click in one element of List View Bike
     */
    private void handleClickBike() {
        System.out.println("User click to view bike detail");
        String bikeInfo = bikesView.getSelectionModel().getSelectedItem();
        if (bikeInfo != null) {
            Bike bike = getBikeFromString(bikeInfo);

            showDetailBikeScreen(bike);
        }
    }
    private void setupImage() {
        String bikeInfo = bikesView.getSelectionModel().getSelectedItem();
        if (bikeInfo != null) {
            Bike bike = getBikeFromString(bikeInfo);
            String url = ViewBikeController.getImageURL(bike);
            Image image = new Image(getClass().getResource(url).toExternalForm());
            imageBike.setImage(image);
        }
    }
    /**
     * Loading Detail Bike SCreen and initialize data in SCreen
     * @param bike
     */
    private void showDetailBikeScreen(Bike bike){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ecobike/BikeDetailScreen.fxml"));
            Parent root = loader.load();

            BikeDetailScreenHandler viewDetailBikeController = loader.getController();
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

    /**
     * Get Bike by general Info String
     * Iterate all bike in list bike of this parking lot and find match
     * @param bikeInfo
     * @return
     */
    private Bike getBikeFromString(String bikeInfo) {
        for (Bike bike: parkingLot.getBikes()){
            String s = bike.getGeneralInfo();
            if (bikeInfo.equals(s)) {
                return bike;
            }
        }
        return null;
    }

    /**
     * Loading Rent Bike Screen and init data for it
     */
    public void handleClickRentBikeBtn() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ecobike/RentBikeScreen.fxml"));
            Parent root = loader.load();

            RentBikeScreenHandler rentBikeScreenHandler = loader.getController();
            rentBikeScreenHandler.setParkingLot(parkingLot);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.setTitle("Rent Bike Screen");
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void back() {
        Stage stage = (Stage) returnBtn.getScene().getWindow();
        stage.close();
    }
}
