package generator.qrmarkerm.fx.controller.button;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LoadLogoButtonHandle {
    public void execute(
            final Alert alert,
            final ImageView logoImageView
    ) {
        FileChooser fileChooser = new FileChooser();
        BufferedImage logoImage;
        fileChooser.setTitle("Wybierz plik z logo");

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("GIF", "*.gif"),
                new FileChooser.ExtensionFilter("BMP", "*.bmp"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg"));

        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            try {
                logoImage = ImageIO.read(file);
                logoImageView.setImage(SwingFXUtils.toFXImage(logoImage, null));
            } catch (IOException e) {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setContentText("Błąd pobierania pliku " + e.getMessage());
                alert.show();
            }
        }
    }
}