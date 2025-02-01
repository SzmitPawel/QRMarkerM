package generator.qrmarkerm.generator;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import generator.qrmarkerm.dto.ResolutionDto;
import generator.qrmarkerm.fx.controller.pattern.Generator;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

public class ZxingGenerator implements Generator {

    private final QRCodeWriter qrCodeWriter = new QRCodeWriter();

    @Override
    public BufferedImage generate(
            final String barcodeText,
            final ColorPicker qrColorPicker,
            final ColorPicker backgroudColorPicker,
            final String resolution
    ) throws WriterException, IOException {

        ResolutionDto qrCodeResolutionDto = parseResolution(resolution);

        BitMatrix bitMatrix = qrCodeWriter.encode(
                barcodeText,
                BarcodeFormat.QR_CODE,
                qrCodeResolutionDto.getWidth(),
                qrCodeResolutionDto.getHeight(),
                getErrorCorrectionHints());

        return MatrixToImageWriter.toBufferedImage(bitMatrix, setQrCodeColors(qrColorPicker, backgroudColorPicker));
    }

    private int colorToInt(Color color) {
        int red = (int) (color.getRed() * 255);
        int green = (int) (color.getGreen() * 255);
        int blue = (int) (color.getBlue() * 255);
        int alpha = (int) (color.getOpacity() * 255);
        return (alpha << 24) | (red << 16) | (green << 8) | blue;
    }

    private MatrixToImageConfig setQrCodeColors(
            final ColorPicker qrColorPicker,
            final ColorPicker backgroudColorPicker
    ) {
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

        return new MatrixToImageConfig(onQrCodeRBG, offBackgroundRBG);
    }

    private ResolutionDto parseResolution(String resolution) {
        String[] split = resolution.split("x");

        return new ResolutionDto(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
    }

    private Map<EncodeHintType, Object> getErrorCorrectionHints() {
        return Map.of(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
    }
}