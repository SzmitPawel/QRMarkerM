package generator.qrmarkerm.styles;

import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.encoder.Encoder;
import com.google.zxing.qrcode.encoder.QRCode;
import javafx.scene.control.ColorPicker;

import java.awt.*;
import java.awt.image.BufferedImage;

public final class RoundedQrCodeStyle {
    private final BufferedImage qrImage;
    private final String barcodeText;
    private final ColorPicker backgroundColorPicker;
    private final ColorPicker qrColorPicker;
    private final ColorPicker finderInnerColorPicker;

    public static class RoundedQrCodeStyleBuilder {
        private BufferedImage qrImage;
        private String barcodeText;
        private ColorPicker backgroundColorPicker;
        private ColorPicker qrColorPicker;
        private ColorPicker finderInnerColorPicker;

        public RoundedQrCodeStyleBuilder qrImage(BufferedImage qrImage) {
            this.qrImage = qrImage;
            return this;
        }

        public RoundedQrCodeStyleBuilder barcodeText(String barcodeText) {
            this.barcodeText = barcodeText;
            return this;
        }

        public RoundedQrCodeStyleBuilder backgroundColorPicker(ColorPicker backgroundColorPicker) {
            this.backgroundColorPicker = backgroundColorPicker;
            return this;
        }

        public RoundedQrCodeStyleBuilder qrColorPicker(ColorPicker qrColorPicker) {
            this.qrColorPicker = qrColorPicker;
            return this;
        }

        public RoundedQrCodeStyleBuilder finderInnerColorPicker(ColorPicker finderInnerColorPicker) {
            this.finderInnerColorPicker = finderInnerColorPicker;
            return this;
        }

        public RoundedQrCodeStyle build() {
            return new RoundedQrCodeStyle(
                    qrImage,
                    barcodeText,
                    backgroundColorPicker,
                    qrColorPicker,
                    finderInnerColorPicker
            );
        }
    }

    private RoundedQrCodeStyle(
            final BufferedImage qrImage,
            final String barcodeText,
            final ColorPicker backgroundColorPicker,
            final ColorPicker qrColorPicker,
            final ColorPicker finderInnerColorPicker
    ) {
        this.qrImage = qrImage;
        this.barcodeText = barcodeText;
        this.backgroundColorPicker = backgroundColorPicker;
        this.qrColorPicker = qrColorPicker;
        this.finderInnerColorPicker = finderInnerColorPicker;
    }

    public BufferedImage roundFinderPatterns() throws WriterException {

        int width = qrImage.getWidth();
        int height = qrImage.getHeight();

        // creating copy qrCode image
        BufferedImage roundedQR = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = roundedQR.createGraphics();
        g2d.drawImage(qrImage, 0, 0, null);

        // turning on antialiasing
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Single qrModule size
        int finderSingleModuleSize = calculateModuleSize(width, readVersion(barcodeText)) * 7;

        // draw new round search patterns
        // upper left corner
        processFinderPattern(
                g2d,
                0,
                0,
                finderSingleModuleSize,
                backgroundColorPicker,
                qrColorPicker,
                finderInnerColorPicker);

        // upper right corner
        processFinderPattern(
                g2d,
                width - finderSingleModuleSize,
                0,
                finderSingleModuleSize,
                backgroundColorPicker,
                qrColorPicker,
                finderInnerColorPicker);

        // lower left corner
        processFinderPattern(
                g2d,
                0,
                height - finderSingleModuleSize,
                finderSingleModuleSize,
                backgroundColorPicker,
                qrColorPicker,
                finderInnerColorPicker);

        g2d.dispose();
        return roundedQR;
    }

    private void processFinderPattern(
            final Graphics2D g2d,
            final int x,
            final int y,
            final int size,
            final ColorPicker backgroundColorPicker,
            final ColorPicker qrColorPicker,
            final ColorPicker finderInnerColorPicker
    ) {
        int moduleSize = Math.round(size / 7.0f);

        // set background color
        g2d.setColor(getColor(backgroundColorPicker));

        // we erase old search patterns
        g2d.fillRect(x - moduleSize, y - moduleSize, size + 2 * moduleSize, size + 2 * moduleSize);

        // we draw new round search patterns
        // set color for code
        g2d.setColor(getColor(qrColorPicker));
        g2d.fillOval(x, y, size, size);

        // set color for background
        g2d.setColor(getColor(backgroundColorPicker));
        g2d.fillOval(x + moduleSize, y + moduleSize, size - 2 * moduleSize, size - 2 * moduleSize);

        // set color for round inner code in the search patterns
        g2d.setColor(getColor(finderInnerColorPicker));
        g2d.fillOval(x + 2 * moduleSize, y + 2 * moduleSize, size - 4 * moduleSize, size - 4 * moduleSize);
    }

    private Color getColor(ColorPicker picker) {

        var fxColor = picker.getValue();

        return new Color(
                (float) fxColor.getRed(),
                (float) fxColor.getGreen(),
                (float) fxColor.getBlue(),
                (float) fxColor.getOpacity()
        );
    }

    private int calculateModuleSize(int imageSize, int qrVersion) {
        int qrCodeModules = (qrVersion * 4) + 17;

        return imageSize / qrCodeModules;
    }

    private int readVersion(String barcodeText) throws WriterException {
        QRCode qrCode = Encoder.encode(barcodeText, ErrorCorrectionLevel.H);

        return qrCode.getVersion().getVersionNumber();
    }
}