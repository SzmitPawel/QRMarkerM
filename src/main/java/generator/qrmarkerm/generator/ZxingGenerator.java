package generator.qrmarkerm.generator;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;

import java.awt.image.BufferedImage;

public class ZxingGenerator {
    private final QRCodeWriter qrCodeWriter = new QRCodeWriter();

    public BufferedImage generateQRCodeImage(
            final String barcodeText,
            final ColorPicker qrColorPicker,
            final ColorPicker backgroudColorPicker
    ) throws WriterException {


        Color qrCodeColor = qrColorPicker.getValue();
        if (qrCodeColor == null) {
            qrCodeColor = Color.BLACK;
        }

        Color backgroundColor = backgroudColorPicker.getValue();
        if (backgroundColor == null) {
            backgroundColor = Color.WHITE;
        }

        int onQrCodeRBG = colorToInt(qrCodeColor);
        int offBackgroundRBG = colorToInt(backgroundColor);

        MatrixToImageConfig conf = new MatrixToImageConfig(onQrCodeRBG, offBackgroundRBG);
        BitMatrix bitMatrix = qrCodeWriter.encode(barcodeText, BarcodeFormat.QR_CODE, 200, 200);

        return MatrixToImageWriter.toBufferedImage(bitMatrix, conf);
    }

    private int colorToInt(Color color) {
        int red = (int) (color.getRed() * 255);
        int green = (int) (color.getGreen() * 255);
        int blue = (int) (color.getBlue() * 255);
        int alpha = 255;
        return (alpha << 24) | (red << 16) | (green << 8) | blue;
    }
}