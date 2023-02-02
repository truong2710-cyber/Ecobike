package ecobike.views;

import ecobike.databaseservices.BikeDA;
import ecobike.databaseservices.ParkingLotDA;
import ecobike.entities.Bike;
import ecobike.entities.ParkingLot;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;


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

        parkingLotView.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> stringListView) {
                return new ListCell<String>() {
                    @Override
                    protected void updateItem(String s, boolean b) {
                        super.updateItem(s, b);
                        if (s != null) {
//                            Rectangle rectangle = new Rectangle(100, 20);
//                            rectangle.setArcHeight(10);
//                            rectangle.setArcWidth(10);
//                            rectangle.setFill(Color.DARKCYAN);

                            Label label = new Label(s);
                            label.setTextFill(Color.BLACK);
                            label.setFont(Font.font("System",24));

                            VBox vBox = new VBox();
                            vBox.setPadding(new Insets(10, 10, 10, 10));
                            vBox.getChildren().addAll(label);
                            vBox.setStyle("-fx-background-color: lightgrey; -fx-background-radius: 10; -fx-padding: 10;");

                            vBox.setOnMouseClicked(event -> {
                                if (vBox.getStyle().contains("lightblue")) {
                                    vBox.setStyle("-fx-background-color: lightgrey; -fx-background-radius: 10; -fx-padding: 10;");
                                } else {
                                    vBox.setStyle("-fx-background-color: lightblue; -fx-background-radius: 10; -fx-padding: 10;");
                                }
                            });
                            StackPane stackPane = new StackPane();
                            stackPane.getChildren().add(vBox);
                            setGraphic(stackPane);
                        }
                    }
                };
            }
        });
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
        ArrayList<Bike> s = BikeDA.getRentingBikes(Main.user_id);
        if (s.size() == 1) {
            String bikeID = Integer.toString(s.get(0).getBikeCode());
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
        } else if (s.size() > 1) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ecobike/ListRentedBikeScreen.fxml"));
                Parent root = loader.load();

                ListRentedBikeScreenController listRentedBikeScreenController = loader.getController();

                listRentedBikeScreenController.init(s);
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
            alert.setContentText("No rented bikes available!");
            alert.showAndWait();
        }

    }
}

