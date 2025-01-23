package generator.qrmarkerm.generator;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import generator.qrmarkerm.fx.controller.pattern.Generator;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ZxingGenerator implements Generator {

    private final QRCodeWriter qrCodeWriter = new QRCodeWriter();

    @Override
    public BufferedImage generate(
            final String barcodeText,
            final ColorPicker qrColorPicker,
            final ColorPicker backgroudColorPicker
    ) throws WriterException, IOException {


        Color qrCodeColor = qrColorPicker.getValue();
        if (qrCodeColor == null || qrCodeColor.equals(Color.BLACK)) {
            qrCodeColor = Color.valueOf("#1a1a1a");
        }

        Color backgroundColor = backgroudColorPicker.getValue();
        if (backgroundColor == null) {
            backgroundColor = Color.WHITE;
        }

        int onQrCodeRBG = colorToInt(qrCodeColor);
        int offBackgroundRBG = colorToInt(backgroundColor);

        MatrixToImageConfig conf = new MatrixToImageConfig(onQrCodeRBG, offBackgroundRBG);

        // Error correction
        ErrorCorrectionLevel level = ErrorCorrectionLevel.H;
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, level);

        BitMatrix bitMatrix = qrCodeWriter.encode(barcodeText, BarcodeFormat.QR_CODE, 200, 200, hints);

        return MatrixToImageWriter.toBufferedImage(bitMatrix, conf);
    }

    private int colorToInt(Color color) {
        int red = (int) (color.getRed() * 255);
        int green = (int) (color.getGreen() * 255);
        int blue = (int) (color.getBlue() * 255);
        int alpha = (int) (color.getOpacity() * 255);
        return (alpha << 24) | (red << 16) | (green << 8) | blue;
    }
}