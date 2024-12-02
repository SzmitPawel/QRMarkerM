package generator.qrmarkerm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class QRMarker extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(QRMarker.class.getResource("qrmarker-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 300, 420);
        stage.setTitle("QRMarker");
        stage.setScene(scene);
        stage.show();
    }
}