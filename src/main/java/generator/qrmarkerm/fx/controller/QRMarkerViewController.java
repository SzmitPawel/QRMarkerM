package generator.qrmarkerm.fx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class QRMarkerViewController {
    @FXML
    private TextField linkField;

    @FXML
    private Button generateButton;

    @FXML
    private ImageView qrImageView;

    @FXML
    private Button copyButton;

    @FXML
    private ColorPicker qrCodeColorPicker;

    @FXML
    private ColorPicker backgroundColorPicker;

    private final Alert alert = new Alert(Alert.AlertType.NONE);
    private final GenerateButtonHandle generateButtonHandle = new GenerateButtonHandle();
    private final CopyButtonHandle copyButtonHandle = new CopyButtonHandle();

    @FXML
    public void initialize() {
        setDefaultColorForColorPickers();
        copyButton.setDisable(true);
        configureActionsForGenerateButton();
        configureActionsForCopyToClipboardButton();
    }

    private void configureActionsForGenerateButton() {
        generateButton.setOnAction(event -> {
            generateButtonHandle
                    .execute(linkField.getText(), qrImageView, alert, qrCodeColorPicker, backgroundColorPicker);
            copyButton.setDisable(false);
        });
    }

    private void configureActionsForCopyToClipboardButton() {
        copyButton.setOnAction(event -> {
            copyButtonHandle.execute(qrImageView);
        });
    }

    private void setDefaultColorForColorPickers() {
        qrCodeColorPicker.setValue(Color.BLACK);
        backgroundColorPicker.setValue(Color.WHITE);
    }
}