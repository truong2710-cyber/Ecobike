package ecobike.controllers;

import ecobike.database_services.EventDatabaseService;
import ecobike.database_services.RentalDatabaseService;
import ecobike.entities.Bike;
import ecobike.utils.CostCalculatorBoundary;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Callback;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class ViewBikeController {
    public static int[] getRentedInfo(String rental_id) {
        LocalDateTime returnTime = LocalDateTime.now();
        LocalDateTime startTime = EventDatabaseService.getRentalStartTime(rental_id);
        long rentTimeSec = returnTime.toEpochSecond(ZoneOffset.UTC) - startTime.toEpochSecond(ZoneOffset.UTC);
        int rentTime = (int) rentTimeSec / 60 ;
        Bike bike = RentalDatabaseService.getRentalBike(rental_id);
        CostCalculatorBoundary costCalculatorBoundary = new CostCalculatorBoundary();
        costCalculatorBoundary.setCostCalculationMethod(bike);
        int rentBikeCost = costCalculatorBoundary.calculateCost(rentTime);
        int[] result = {rentTime, rentBikeCost};
        return result;
    }

    public static void setupListView(ListView<String> listView) {
        listView.setCellFactory(new Callback<>() {
            @Override
            public ListCell<String> call(ListView<String> stringListView) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(String s, boolean b) {
                        super.updateItem(s, b);
                        if (s != null) {
                            Label label = new Label(s);
                            label.setTextFill(Color.BLACK);
                            label.setFont(Font.font("System", 24));

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
    }
}
