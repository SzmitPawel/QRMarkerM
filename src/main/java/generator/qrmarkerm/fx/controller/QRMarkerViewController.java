package generator.qrmarkerm.fx.controller;

import generator.qrmarkerm.generator.ZxingGenerator;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

import java.awt.image.BufferedImage;

public class QRMarkerViewController {
    @FXML
    private TextField linkField;

    @FXML
    private Button generateButton;

    @FXML
    private ImageView qrImageView;

    @FXML
    private Button copyButton;

    private final Alert alert = new Alert(Alert.AlertType.NONE);
    private final ZxingGenerator zxingGenerator = new ZxingGenerator();

    @FXML
    public void initialize() {
        generateButtonHandle();
        copyButtonHandle();
    }

    private void generateButtonHandle() {
        copyButton.setDisable(true);
        generateButton.setOnAction(event -> {
            if (linkField.getText() == null || linkField.getText().isEmpty()) {
                alert.setAlertType(Alert.AlertType.WARNING);
                alert.setContentText("Pole linku nie może być puste.");
                alert.show();
                return;
            }

            try {
                BufferedImage bufferedImage = zxingGenerator.generateQRCodeImage(linkField.getText());
                qrImageView.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
                copyButton.setDisable(false);
            } catch (Exception e) {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setContentText("Błąd generowania kodu QR: " + e.getMessage());
                alert.show();
            }
        });
    }

    private void copyButtonHandle() {
        copyButton.setOnAction(event -> {
            ClipboardContent clipboardContent = new ClipboardContent();
            clipboardContent.putImage(qrImageView.getImage());

            Clipboard clipboard = Clipboard.getSystemClipboard();
            clipboard.setContent(clipboardContent);
        });
    }
}