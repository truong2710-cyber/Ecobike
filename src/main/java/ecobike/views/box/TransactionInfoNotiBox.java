package ecobike.views.box;

import javafx.scene.control.Alert;

public class TransactionInfoNotiBox {
    public static void displayNotificationErrorCode(String code, String command){

        switch (code) {
            case "00" -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Giao dịch");
                if (command.equals("deposit")) {
                    alert.setContentText("Bạn đã thuê xe thành công!");
                } else if (command.equals("refund")) {
                    alert.setContentText("Thanh toán thành công!");
                }
                alert.showAndWait();
            }
            case "01" -> {
                ErrorBox.show("Lỗi giao dịch", "Thẻ không hợp lệ!");
//                Alert error_alert_1 = new Alert(Alert.AlertType.ERROR);
//                error_alert_1.setTitle("Lỗi giao dịch");
//                error_alert_1.setContentText("Thẻ không hợp lệ!");
//                error_alert_1.showAndWait();
            }
            case "02" -> {
                ErrorBox.show("Lỗi giao dịch", "Thẻ không đủ số dư!");
//                Alert error_alert_2 = new Alert(Alert.AlertType.ERROR);
//                error_alert_2.setTitle("Lỗi giao dịch");
//                error_alert_2.setContentText("Thẻ không đủ số dư!");
//                error_alert_2.showAndWait();
            }
            case "03" -> {
                ErrorBox.show("Lỗi giao dịch", "Lỗi server nội bộ!");
//                Alert error_alert_3 = new Alert(Alert.AlertType.ERROR);
//                error_alert_3.setTitle("Lỗi giao dịch");
//                error_alert_3.setContentText("Lỗi server nội bộ!");
//                error_alert_3.showAndWait();
            }
            case "04" -> {
                ErrorBox.show("Lỗi giao dịch", "Giao dịch bị nghi ngờ gian lận!");
//                Alert error_alert_4 = new Alert(Alert.AlertType.ERROR);
//                error_alert_4.setTitle("Lỗi giao dịch");
//                error_alert_4.setContentText("Giao dịch bị nghi ngờ gian lận!");
//                error_alert_4.showAndWait();
            }
            case "05" -> {
                ErrorBox.show("Lỗi giao dịch", "Không đủ thông tin giao dịch!");
//                Alert error_alert_5 = new Alert(Alert.AlertType.ERROR);
//                error_alert_5.setTitle("Lỗi giao dịch");
//                error_alert_5.setContentText("Không đủ thông tin giao dịch!");
//                error_alert_5.showAndWait();
            }
            case "06" -> {
                ErrorBox.show("Lỗi giao dịch", "Thiếu thông tin version!");
//                Alert error_alert_6 = new Alert(Alert.AlertType.ERROR);
//                error_alert_6.setTitle("Lỗi giao dịch");
//                error_alert_6.setContentText("Thiếu thông tin version!");
//                error_alert_6.showAndWait();
            }
            case "07" -> {
                ErrorBox.show("Lỗi giao dịch", "Số lượng không hợp lệ!");
//                Alert error_alert_7 = new Alert(Alert.AlertType.ERROR);
//                error_alert_7.setTitle("Lỗi giao dịch");
//                error_alert_7.setContentText("Số lượng không hợp lệ!");
//                error_alert_7.showAndWait();
            }
            default -> {
                ErrorBox.show("Lỗi giao dịch", "404 Not found!");
//                Alert error_alert_8 = new Alert(Alert.AlertType.ERROR);
//                error_alert_8.setTitle("Lỗi giao dịch");
//                error_alert_8.setContentText("404 Not found!");
//                error_alert_8.showAndWait();
            }
        }
    }
}
