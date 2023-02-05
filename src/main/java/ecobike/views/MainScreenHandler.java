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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Main Screen after starting the app
 * Main Screen show list of available station
 * Initialize Station SCreen when double click one station in List View Station
 */
public class MainScreenHandler implements Initializable {
    public static boolean reset = false;
    ArrayList<ParkingLot> parkingLots;

    @FXML
    private ListView<String> parkingLotView;

    @FXML
    private Button rentedBikeBtn;
    public void handleRentButtonClick() {
        try {
            System.out.println("user click RentBikeButton");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ecobike/RentBikeScreen.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.setTitle("Rent Bike Screen");
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initialize main screen");

        parkingLots = ParkingLotDatabaseService.getAllParkingLotsWithBike();

        //show list of parking lots
        for (ParkingLot parkingLot : parkingLots) {
            parkingLotView.getItems().add(parkingLot.getGeneralInfo());
        }

        ViewBikeController.setupListView(parkingLotView);
        //listen when user double-click on the parking lot in listview => show ViewParkingLotScreen
        parkingLotView.setOnMouseClicked(click -> {
            if (click.getClickCount() == 2) {
                handleDoubleClickOnDockList();
            }
        });
        ViewBikeController.setupButton(rentedBikeBtn);
    }

    private void handleDoubleClickOnDockList() {
        System.out.println("User double on a dock");
        String parkInfo = parkingLotView.getSelectionModel().getSelectedItem();
        if (parkInfo != null) {
            ParkingLot parkingLot = getParkFromString(parkInfo);
            showViewDockScreen(parkingLot);
        }
    }

    /**
     * Get the parkingLot when user click on a row
     * @param parkInfo: parkingLot info from the UI
     * @return: the corresponding parkingLot
     */
    private ParkingLot getParkFromString(String parkInfo) {

        for (ParkingLot parkingLot : parkingLots) {
            String info = parkingLot.getGeneralInfo();
            if (parkInfo.equals(info)) {
                return parkingLot;
            }
        }
        return null;
    }

    /**
     * Show the StationScreen
     * @param parkingLot: parkingLot object
     */
    public void showViewDockScreen(ParkingLot parkingLot) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ecobike/StationScreen.fxml"));
            Parent root = loader.load();

            StationScreenHandler viewStationController = loader.getController();

            viewStationController.initData(parkingLot);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.setTitle("View ParkingLot Screen");
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Purpose: Show rented bike screen
     * If user rented 1 bike, the system will show directly to Rented Bike Information
     * If user rented more than 1 bike, the system will show you list of rented bike. Then you choose one bike in list and the sustem show Rented Bike Information
     */
    public void handleViewBikeButtonClick(){
        ArrayList<Bike> s = BikeDatabaseService.getRentingBikes(Main.user_id);
        if (s.size() == 1) {
            String bikeID = Integer.toString(s.get(0).getBikeCode());
            Bike bike = BikeDatabaseService.getBikeByID(bikeID);
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ecobike/RentedBikeInfoScreen.fxml"));
                Parent root = loader.load();

                RentedBikeInfoScreenHandler viewBikeController = loader.getController();

                viewBikeController.init(bike);

                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(new Scene(root));
                stage.setTitle("Rented Bike Screen");
                stage.showAndWait();
            }catch (IOException e){
                e.printStackTrace();
            }
        } else if (s.size() > 1) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ecobike/ListRentedBikeScreen.fxml"));
                Parent root = loader.load();

                ListRentedBikeScreenHandler listRentedBikeScreenHandler = loader.getController();

                listRentedBikeScreenHandler.init(s);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(new Scene(root));
                stage.setTitle("Rented Bike Screen");
                stage.showAndWait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Bạn đang không thuê xe!");
            alert.showAndWait();
        }
    }
}

