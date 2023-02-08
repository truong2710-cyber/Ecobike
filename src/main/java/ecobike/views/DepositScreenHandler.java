package ecobike.views;

import ecobike.controllers.RentBikeController;
import ecobike.controllers.ViewBikeController;
import ecobike.entities.Card;
import ecobike.validators.CardInfoValidator;
import ecobike.views.box.ErrorBox;
import ecobike.views.box.TransactionInfoNotiBox;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class DepositScreenHandler implements Initializable {
    @FXML
    private TextField text1, text2, text3, text4;

    @FXML
    private DatePicker datePicker;

    String bikeID;

    @FXML
    private Button huy, xacnhan;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ViewBikeController.setupButton(huy);
        ViewBikeController.setupButton(xacnhan);
    }

    public String getBikeID() {
        return bikeID;
    }
    public void setBikeID(String bikeID) {
        this.bikeID = bikeID;
    }

    public TextField getText4() {
        return text4;
    }
    public void setText4(TextField text4) {
        this.text4 = text4;
    }

    public void handlePayment() {
        RentBikeController rentBikeController = new RentBikeController();
        rentBikeController.setBikeID(bikeID);
        if (rentBikeController.checkCardOnRental(text1.getText())){
            ErrorBox.show("Error", "Thẻ đang được sử dụng trong giao dịch thuê khác!");
            //NotificationBox.display("Error", "Thẻ đang được sử dụng trong giao dịch thuê khác!");
            return;
        }
        if (!(CardInfoValidator.validateCardCode(text1.getText()) && CardInfoValidator.validateOwner(text2.getText()) && CardInfoValidator.validateCvv(text3.getText()) && CardInfoValidator.validateExpireDate(datePicker.getValue()))){
            ErrorBox.show("Error", "Thông tin thẻ không hợp lệ!");
            return;
        }
        Card card = new Card(text1.getText(), text2.getText(), text3.getText(), datePicker.getValue());
        String amount = text4.getText();
        String respondCode = rentBikeController.handlePayment(card, amount);
        TransactionInfoNotiBox.displayNotificationErrorCode(respondCode, "deposit");

    }

    public void back() {
        Stage stage = (Stage) huy.getScene().getWindow();
        stage.close();
    }
}
