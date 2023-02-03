package ecobike.views;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class Main extends Application {
    public static int user_id = 3;
    @Override
    public void start(Stage stage) throws IOException {
        // Initialize the scene
        Parent root = FXMLLoader.load(getClass().getResource("/ecobike/SplashScreen.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        // Load splash screen with fade in effect
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(2), root);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.setCycleCount(1);

        // Finish splash with fade out effect
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), root);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.setCycleCount(1);

        // After fade in, start fade out
        fadeIn.play();
        fadeIn.setOnFinished((e) -> fadeOut.play());

        // After fade out, load actual content
        fadeOut.setOnFinished((e) -> {
            try {
                Parent root1 = FXMLLoader.load(getClass().getResource("/ecobike/MainScreen.fxml"));
                stage.setTitle("EcoBike");
                stage.setScene(new Scene(root1));
                stage.show();

            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

    }

    public static void main(String[] args) {
        launch();
    }
}