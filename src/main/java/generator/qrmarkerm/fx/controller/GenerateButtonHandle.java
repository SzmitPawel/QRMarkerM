package generator.qrmarkerm.fx.controller;

import generator.qrmarkerm.generator.ZxingGenerator;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Alert;
import javafx.scene.control.ColorPicker;
import javafx.scene.image.ImageView;

import java.awt.image.BufferedImage;

public class GenerateButtonHandle {

    private final ZxingGenerator zxingGenerator = new ZxingGenerator();

    public void execute(
            final String link,
            final ImageView qrImageView,
            final Alert alert,
            final ColorPicker qrColorPicker,
            final ColorPicker backgroundColorPicker
    ) {

        if (link == null || link.isEmpty()) {
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setContentText("Pole linku nie może być puste.");
            alert.show();
            return;
        }

        try {
            BufferedImage bufferedImage = zxingGenerator
                    .generateQRCodeImage(link, qrColorPicker, backgroundColorPicker);
            qrImageView.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
        } catch (Exception e) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Błąd generowania kodu QR: " + e.getMessage());
            alert.show();
        }
    }
}