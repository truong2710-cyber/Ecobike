package ecobike.views.box;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * Box for confirmation
 */
public class ConfirmBox {
    /**
     * Display confirmation box
     *
     * @param title:   box title
     * @param message: box message
     * @return confirm result
     */
    public static boolean show(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

        return alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES;
    }
}


