package ecobike.views;

import ecobike.controllers.ViewBikeController;
import ecobike.database_services.BikeDatabaseService;
import ecobike.entities.Bike;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Show all bike that rented by user for now
 * The Screen will showed when you click View Rented Bike Info but you is renting more than 1 bike
 * After you double click one bike in List View Bike, this Screen initialize Rented Bike Infor of that biek
 *
 */
public class ListRentedBikeScreenHandler implements Initializable {
    @FXML
    private Button returnBtn;

    @FXML
    private ListView<String> listRentedBikeView;

    private ArrayList<Bike> bikes;

    public int park_id;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ViewBikeController.setupButton(returnBtn);
    }

    public void init(ArrayList<Bike> bikes) {
        this.bikes = bikes;
        System.out.println("Initialize list rented bike screen");

        for (Bike bike: this.bikes){
            listRentedBikeView.getItems().add(bike.getGeneralInfo());
        }

        ViewBikeController.setupListView(listRentedBikeView);
        listRentedBikeView.setOnMouseClicked(click -> {
            if (click.getClickCount() == 2) {
                handleClickRentedBike();
            }
        });

    }

    public void back(){
        Stage stage = (Stage) returnBtn.getScene().getWindow();
        stage.close();
    }

    private void handleClickRentedBike() {
        System.out.println("User click view a rented bike");
        String bikeInfo = listRentedBikeView.getSelectionModel().getSelectedItem();
        if (bikeInfo != null) {
            Bike s = getBikeFromString(bikeInfo);
            assert s != null;
            String bikeID = Integer.toString(s.getBikeCode());
            Bike bike = BikeDatabaseService.getBikeByID(bikeID);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ecobike/RentedBikeInfoScreen.fxml"));
                Parent root = loader.load();

                RentedBikeInfoScreenHandler viewBikeController = loader.getController();

                viewBikeController.init(bike);

                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(new Scene(root));
                stage.setTitle("Rented Bike Screen");
                stage.showAndWait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get Bike from bike Info String when you clicked in the List View
     * Iterate all bikes and match 2 general info
     * @param bikeInfo
     * @return
     */
    private Bike getBikeFromString(String bikeInfo) {
        for (Bike bike: this.bikes){
            String s = bike.getGeneralInfo();
            if (bikeInfo.equals(s)) {
                return bike;
            }
        }
        return null;
    }

}
