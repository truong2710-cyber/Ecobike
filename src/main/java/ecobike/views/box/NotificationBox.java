package ecobike.views.box;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Box for notification
 */
public class NotificationBox {
    /**
     * Display notification box
     *
     * @param title:   box title
     * @param message: box message
     */
    public static void display(String title, String message){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setHeight(200);
        window.setWidth(300);
        Region region = new Region();
        Label label = new Label();

        label.setText(message);
        Label label1 = new Label();
        label1.setText("EcoBike System");
        Button button = new Button("OK");
        button.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label1, label, button, region);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
