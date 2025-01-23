package generator.qrmarkerm.fx.controller.pattern;

import com.google.zxing.WriterException;
import javafx.scene.control.ColorPicker;

import java.awt.image.BufferedImage;
import java.io.IOException;

public interface Generator {
    BufferedImage generate(String barcodeText, ColorPicker qrColorPicker, ColorPicker backgroudColorPicker)
            throws WriterException, IOException;
}
