package ecobike.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class TransactionInfoNotiBox {
    public static void displayNotificationErrorCode(String code, String command){

        switch (code) {
            case "00":
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Transaction");
                if (command.equals("deposit")) {
                    alert.setContentText("Rent bike transaction successfully!");
                } else if (command.equals("refund")) {
                    alert.setContentText("Rent bike transaction successfully!");
                }
                alert.showAndWait();
                break;
            case "01":
                Alert error_alert_1 = new Alert(Alert.AlertType.ERROR);
                error_alert_1.setTitle("Transaction Error");
                error_alert_1.setContentText("Invalid card!");
                error_alert_1.showAndWait();
                break;
            case "02":
                Alert error_alert_2 = new Alert(Alert.AlertType.ERROR);
                error_alert_2.setTitle("Transaction Error");
                error_alert_2.setContentText("Not enough balance!");
                error_alert_2.showAndWait();
                break;
            case "03":
                Alert error_alert_3 = new Alert(Alert.AlertType.ERROR);
                error_alert_3.setTitle("Transaction Error");
                error_alert_3.setContentText("Internal server error!");
                error_alert_3.showAndWait();
                break;
            case "04":
                Alert error_alert_4 = new Alert(Alert.AlertType.ERROR);
                error_alert_4.setTitle("Transaction Error");
                error_alert_4.setContentText("Suspicious transaction!");
                error_alert_4.showAndWait();
                break;
            case "05":
                Alert error_alert_5 = new Alert(Alert.AlertType.ERROR);
                error_alert_5.setTitle("Transaction Error");
                error_alert_5.setContentText("Not enough transaction information!");
                error_alert_5.showAndWait();
                break;
            case "06":
                Alert error_alert_6 = new Alert(Alert.AlertType.ERROR);
                error_alert_6.setTitle("Transaction Error");
                error_alert_6.setContentText("Lack of version information!");
                error_alert_6.showAndWait();
                break;
            case "07":
                Alert error_alert_7 = new Alert(Alert.AlertType.ERROR);
                error_alert_7.setTitle("Transaction Error");
                error_alert_7.setContentText("Invalid amount!");
                error_alert_7.showAndWait();
                break;
            default:
                Alert error_alert_8 = new Alert(Alert.AlertType.ERROR);
                error_alert_8.setTitle("Transaction Error");
                error_alert_8.setContentText("404 Not found!");
                error_alert_8.showAndWait();
                break;
        }
    }
}
