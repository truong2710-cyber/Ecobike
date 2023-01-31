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
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Main Screen after starting the app
 */
public class MainScreenController implements Initializable {
    public static boolean reset = false;
    ArrayList<ParkingLot> parkingLots;

    @FXML
    private ListView<String> parkingLotView;

    public void handleRentButtonClick() {
        try {
            System.out.println("user click RentBikeButton");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ecobike/RentBikeScreen.fxml"));
            Parent root = (Parent) loader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.setTitle("RentBikeScreen");
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initialize main screen");

        parkingLots = ParkingLotDA.getAllParkingLots();

        //show list of parking lots
        for (ParkingLot parkingLot : parkingLots) {
            parkingLotView.getItems().add(parkingLot.getGeneralInfo());
        }

        //listen when user double-click on the parking lot in listview => show ViewParkingLotScreen
        parkingLotView.setOnMouseClicked(click -> {
            if (click.getClickCount() == 2) {
                handleDoubleClickOnDockList();
            }
        });
    }

    private void handleDoubleClickOnDockList() {
        System.out.println("User double on a dock");
        String parkInfo = parkingLotView.getSelectionModel().getSelectedItem();
        ParkingLot parkingLot = getParkFromString(parkInfo);

        showViewDockScreen(parkingLot);
    }

    /**
     * Khi người dùng click vào một dòng trong danh sách bãi xe, hàm sẽ tiến hành tìm kiếm đối tượng bãi xe tương
     * ứng bằng cách so sánh string từ giao diện gửi về và string của bãi xe trong danh sách bãi xe docks
     * @param parkInfo: string của bãi xe gửi về từ giao diện
     * @return: bãi xe tương ứng hoặc null trong trường hợp không tồn tại
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
     * Hiển thị giao diện xem thông tin bãi xe
     * @param parkingLot: đối tượng bãi xe chứa thông tin cần hiển thị
     */
    public void showViewDockScreen(ParkingLot parkingLot) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ecobike/StationScreen.fxml"));
            Parent root = loader.load();

            StationScreenController viewStationController = loader.getController();

            viewStationController.initData(parkingLot);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.setTitle("ViewDockScreen");
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleViewBikeButtonClick(){
        ArrayList<ArrayList<String>> s = BikeDA.getRentingBikes();
        if (s.size() > 0) {
            String bikeID = s.get(0).get(0);
            Bike bike = BikeDA.getBikeByID(bikeID);
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ecobike/RentedBikeInfoScreen.fxml"));
                Parent root = loader.load();

                RentedBikeInfoScreenController viewBikeController = loader.getController();

                viewBikeController.init(bike);

                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(new Scene(root));
                stage.setTitle("Rented Bike Screen");
                stage.showAndWait();
            }catch (IOException e){
                e.printStackTrace();
            }

        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("No rented bikes available!");
            alert.showAndWait();
        }

    }
}

