package ecobike.views;

import ecobike.entities.Bike;
import ecobike.views.box.NotificationBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.util.Pair;

public class RentBikeScreenController {
    @FXML
    private TextField barcode;

    void handleRentBike(ActionEvent event) {
        String message = barcode.getText();
        if (message.isEmpty()) {
            NotificationBox.display("NotificationBox", "Vui lòng nhập barcode của xe!");
        } else {

        }
    }
}
