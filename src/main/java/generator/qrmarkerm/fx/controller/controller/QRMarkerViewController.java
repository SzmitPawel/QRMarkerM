package generator.qrmarkerm.fx.controller.controller;

import generator.qrmarkerm.fx.controller.button.CopyButtonHandle;
import generator.qrmarkerm.fx.controller.button.GenerateButtonHandle;
import generator.qrmarkerm.fx.controller.button.LoadLogoButtonHandle;
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
    private ColorPicker backGroundColorPicker;

    @FXML
    private Button uploadLogoButton;

    @FXML
    private ImageView logoPreview;

    private final Alert alert = new Alert(Alert.AlertType.NONE);
    private final GenerateButtonHandle generateButtonHandle = new GenerateButtonHandle();
    private final CopyButtonHandle copyButtonHandle = new CopyButtonHandle();
    private final LoadLogoButtonHandle loadLogoButtonHandle = new LoadLogoButtonHandle();

    @FXML
    public void initialize() {
        setDefaultColorForColorPickers();
        copyButton.setDisable(true);

        configureActionsForGenerateButton();
        configureActionsForCopyToClipboardButton();
        configureActionsForUploadLogoButton();
    }

    private void configureActionsForGenerateButton() {
        generateButton.setOnAction(event -> {
            generateButtonHandle
                    .execute(linkField.getText(),
                            qrImageView,
                            logoPreview,
                            alert,
                            qrCodeColorPicker,
                            backGroundColorPicker
                    );

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
        backGroundColorPicker.setValue(Color.WHITE);
    }

    private void configureActionsForUploadLogoButton() {
        uploadLogoButton.setOnAction(event -> {
            loadLogoButtonHandle.execute(alert, logoPreview);
        });
    }
}