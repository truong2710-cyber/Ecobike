package ecobike.views;

import ecobike.databaseservices.BikeDA;
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

public class ListRentedBikeScreenController implements Initializable {
    @FXML
    private Button returnAllBtn;

    @FXML
    private ListView<String> listRentedBikeView;

    private ArrayList<Bike> bikes;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void init(ArrayList<Bike> bikes) {
        this.bikes = bikes;
        System.out.println("Initialize list rented bike screen");

        for (Bike bike: this.bikes){
            listRentedBikeView.getItems().add(bike.getGeneralInfo());
        }

        listRentedBikeView.setOnMouseClicked(click -> {
            if (click.getClickCount() == 2) {
                handleClickRentedBike();
            }
        });
    }

    public void returnAll(){

    }
    private void handleClickRentedBike() {
        System.out.println("User click view a rented bike");
        String bikeInfo = listRentedBikeView.getSelectionModel().getSelectedItem();
        Bike s = getBikeFromString(bikeInfo);
        String bikeID = Integer.toString(s.getBikeCode());
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
