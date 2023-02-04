package ecobike.views.box;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Box for confirmation
 */
public class ConfirmBox {
    private static boolean answer;

    /**
     * Display confirmation box
     *
     * @param title:   box title
     * @param message: box message
     * @return confirm result
     */
    public static boolean display(String title, String message) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setWidth(300);
        window.setHeight(200);
        Label label = new Label();
        label.setText(message);

        Button yesButton = new Button("Đồng ý");
        Button noButton = new Button("Không");
        Region region1 = new Region();
        Region region2 = new Region();
        Region region3 = new Region();
        Region region4 = new Region();


        yesButton.setOnAction(e -> {
            answer = true;
            window.close();
        });

        noButton.setOnAction(e -> {
            answer = false;
            window.close();
        });

        VBox layout = new VBox(10);
        HBox hBox = new HBox(3, yesButton, region2, region3, region4, noButton);

        hBox.setAlignment(Pos.CENTER);
        Label label1 = new Label();
        label1.setText("EcoBike System");
        layout.getChildren().addAll(label1, label, hBox, region1);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);

        window.showAndWait();

        return answer;
    }


}
