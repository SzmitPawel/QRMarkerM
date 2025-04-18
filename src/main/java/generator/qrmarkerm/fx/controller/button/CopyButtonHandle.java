package generator.qrmarkerm.fx.controller.button;

import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

public class CopyButtonHandle {
    public void execute(final ImageView qrImageView) {

        ClipboardContent clipboardContent = new ClipboardContent();
        clipboardContent.putImage(qrImageView.getImage());

        Clipboard clipboard = Clipboard.getSystemClipboard();
        clipboard.setContent(clipboardContent);
    }
}