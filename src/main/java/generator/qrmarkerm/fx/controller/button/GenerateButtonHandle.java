package generator.qrmarkerm.fx.controller.button;

import generator.qrmarkerm.fx.controller.pattern.Generator;
import generator.qrmarkerm.fx.controller.pattern.GeneratorFactory;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Alert;
import javafx.scene.control.ColorPicker;
import javafx.scene.image.ImageView;

import java.awt.image.BufferedImage;

public class GenerateButtonHandle {

    public void execute(
            final String barcodeText,
            final ImageView qrImageView,
            final ImageView logoPreview,
            final Alert alert,
            final ColorPicker qrColorPicker,
            final ColorPicker backgroundColorPicker,
            final String qrCodeResolution
    ) {
        Generator generator = GeneratorFactory.getGenerator(logoPreview);

        try {
            BufferedImage bufferedImage = generator
                    .generate(barcodeText, qrColorPicker, backgroundColorPicker, qrCodeResolution);

            qrImageView.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
        } catch (Exception e) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Błąd generowania kodu QR: " + e.getMessage());
            alert.show();
        }
    }
}