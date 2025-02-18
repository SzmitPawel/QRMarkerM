package generator.qrmarkerm.fx.controller.controller;

import generator.qrmarkerm.fx.controller.button.CopyButtonHandle;
import generator.qrmarkerm.fx.controller.button.GenerateButtonHandle;
import generator.qrmarkerm.fx.controller.button.LoadLogoButtonHandle;
import generator.qrmarkerm.fx.controller.combobox.CodeResolutionsComboBox;
import generator.qrmarkerm.fx.controller.combobox.CodeStylesCodeComboBox;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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

    @FXML
    private ComboBox<String> resolutionComboBox;

    @FXML
    private ComboBox<String> styleComboBox;

    private final Alert alert = new Alert(Alert.AlertType.NONE);
    private final GenerateButtonHandle generateButtonHandle = new GenerateButtonHandle();
    private final CopyButtonHandle copyButtonHandle = new CopyButtonHandle();
    private final LoadLogoButtonHandle loadLogoButtonHandle = new LoadLogoButtonHandle();
    private final CodeResolutionsComboBox codeResolutionsComboBox = new CodeResolutionsComboBox();
    private final CodeStylesCodeComboBox codeStylesCodeComboBox = new CodeStylesCodeComboBox();

    @FXML
    public void initialize() {
        setDefaultColorForColorPickers();
        copyButton.setDisable(true);

        configureActionsForGenerateButton();
        configureActionsForCopyToClipboardButton();
        configureActionsForUploadLogoButton();
        setupResolutionComboBox();
        setupCodeStylesCodeComboBox();
    }

    private void configureActionsForGenerateButton() {
        generateButton.setOnAction(event -> {
            generateButtonHandle
                    .execute(linkField.getText(),
                            qrImageView,
                            logoPreview,
                            alert,
                            qrCodeColorPicker,
                            backGroundColorPicker,
                            resolutionComboBox.getValue(),
                            styleComboBox.getValue()
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

    private void setupResolutionComboBox() {
        resolutionComboBox.getItems().addAll(codeResolutionsComboBox.getCodeResolutions());
        resolutionComboBox.setValue(codeResolutionsComboBox.getCodeResolutions().getFirst());
    }

    private void setupCodeStylesCodeComboBox() {
        styleComboBox.getItems().addAll(codeStylesCodeComboBox.getCodeStyles());
        styleComboBox.setValue(codeStylesCodeComboBox.getCodeStyles().getFirst());
    }
}