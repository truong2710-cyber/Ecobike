package ecobike.views;

import ecobike.controllers.ReturnBikeController;
import ecobike.controllers.ViewBikeController;
import ecobike.database_services.CardDatabaseService;
import ecobike.entities.Card;
import ecobike.entities.ParkingLot;
import ecobike.views.box.TransactionInfoNotiBox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class ReturnBikeScreenHandler implements Initializable {

    private ArrayList<ParkingLot> parkingLots;
    private String rental_id;

    @FXML
    private ListView<String> parkingLotView;

    @FXML
    private Button returnBtn;
    @Override
    public void initialize(URL url, ResourceBundle rb){
        System.out.println("default initialize ReturnBikeScreen");
    }
    public void init(ArrayList<ParkingLot> parkingLots, String rental_id){
        System.out.println("initialize ReturnBikeScreen");
        this.rental_id = rental_id;
        this.parkingLots = parkingLots;
        //show list of docks
        for (ParkingLot parkingLot : parkingLots) {
            parkingLotView.getItems().add(parkingLot.getGeneralInfo());
        }

        parkingLotView.setOnMouseClicked(click -> {
            if (click.getClickCount() == 2) {
                handleDoubleClickOnParkingLotList();
            }
        });

        ViewBikeController.setupButton(returnBtn);
        ViewBikeController.setupListView(parkingLotView);
    }
    public void handleDoubleClickOnParkingLotList(){
        System.out.println("User double click on parking lot list");
        String parkingLotInfo = parkingLotView.getSelectionModel().getSelectedItem();
        ParkingLot parkingLot = getParkingLotFromInfo(parkingLotInfo);
        assert parkingLot != null;
        if(checkParkingLotFull(parkingLot)) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setContentText("Bãi đỗ này đã đầy!");
            errorAlert.showAndWait();
        }
        else{
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmAlert.setTitle("Confirm");
            confirmAlert.setContentText("Bạn có chắc chắn muốn trả xe không ạ?");
            Optional<ButtonType> option = confirmAlert.showAndWait();
            if (option.get() == ButtonType.OK){
                System.out.println("confirmed Return Bike");
                Card card = CardDatabaseService.getCardRentalTransaction(rental_id);
                if(card != null){
                    processReturnBike(parkingLot, rental_id, card);
                }
            }
        }
    }

    private void processReturnBike(ParkingLot parkingLot, String rental_id, Card card) {
        String respondCode = ReturnBikeController.processReturnBike(rental_id, card, parkingLot.getID());
        TransactionInfoNotiBox.displayNotificationErrorCode(respondCode, "refund");

        if (respondCode.equals("00")){
            MainScreenHandler.reset = true;
            showRentBikeTransactionInfo(rental_id);
            Stage stage = (Stage)parkingLotView.getScene().getWindow();
            stage.close();
        }
    }

    private void showRentBikeTransactionInfo(String rental_id) {
        System.out.println("showRentBikeTransactionInfo");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ecobike/RentBikeTransactionScreen.fxml"));
            Parent root = loader.load();

            RentBikeTransactionScreenHandler rentBikeTransactionScreenHandler = loader.getController();
            rentBikeTransactionScreenHandler.initData(rental_id);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.setTitle("RentBike Transaction Info");
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ParkingLot getParkingLotFromInfo(String parkingLotInfo){
        for(ParkingLot parkingLot: parkingLots){
            String s = parkingLot.getGeneralInfo();
            if(parkingLotInfo.equals(s)){
                return parkingLot;
            }
        }
        return null;
    }
    public boolean checkParkingLotFull(ParkingLot parkingLot){
        return parkingLot.getTotalSlots() == parkingLot.getBikes().size();
    }

    public void back() {
        Stage stage = (Stage) returnBtn.getScene().getWindow();
        stage.close();
    }
}
