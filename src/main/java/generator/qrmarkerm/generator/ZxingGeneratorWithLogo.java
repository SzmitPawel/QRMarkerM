package generator.qrmarkerm.generator;

import com.google.zxing.WriterException;
import generator.qrmarkerm.fx.controller.pattern.Generator;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.ColorPicker;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ZxingGeneratorWithLogo extends ZxingGenerator implements Generator {

    private final ImageView logoImageView;

    public ZxingGeneratorWithLogo(ImageView logoImageView) {
        this.logoImageView = logoImageView;
    }

    @Override
    public BufferedImage generate(
            final String barcodeText,
            final ColorPicker qrColorPicker,
            final ColorPicker backgroudColorPicker,
            final String resolution
    ) throws WriterException, IOException {

        BufferedImage qrCodeImage = super.generate(barcodeText, qrColorPicker, backgroudColorPicker, resolution);

        BufferedImage scaledLogo = prepareScaledLogo(logoImageView, qrCodeImage.getWidth(), qrCodeImage.getHeight());

        prepareQRCodeWithLogo(qrCodeImage, scaledLogo);

        return qrCodeImage;
    }

    private BufferedImage prepareScaledLogo(
            final ImageView logoImageView,
            final int qrCodeWidth,
            final int qrCodeHeight
    ) throws IOException {

        // Load the logo image
        BufferedImage logoImage = SwingFXUtils.fromFXImage(logoImageView.getImage(), null);

        // Maximum dimensions for the logo (20% of QR code size)
        int maxLogoWidth = qrCodeWidth / 5;
        int maxLogoHeight = qrCodeHeight / 5;

        // Original dimensions of the logo
        int logoWidth = logoImage.getWidth();
        int logoHeight = logoImage.getHeight();

        // Calculate scaling factor to maintain aspect ratio
        double scaleFactor = Math.min((double) maxLogoWidth / logoWidth, (double) maxLogoHeight / logoHeight);

        // Scaled dimensions
        int scaledWidth = (int) (logoWidth * scaleFactor);
        int scaledHeight = (int) (logoHeight * scaleFactor);

        // Create a new scaled logo image
        BufferedImage resizedLogo = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_ARGB);

        // Draw the scaled logo
        Graphics2D g2d = resizedLogo.createGraphics();

        // Apply rendering hints for smooth scaling and colors
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        // Draw the logo onto the image with scaling
        g2d.drawImage(logoImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH), 0, 0, null);

        g2d.dispose();

        return resizedLogo;
    }

    private void prepareQRCodeWithLogo(BufferedImage qrCodeImage, BufferedImage logoImage) {
        Graphics2D qrCodeGraphics = qrCodeImage.createGraphics();

        // Calculate the position to center the logo on the QR code
        int centerX = (qrCodeImage.getWidth() - logoImage.getWidth()) / 2;
        int centerY = (qrCodeImage.getHeight() - logoImage.getHeight()) / 2;

        qrCodeGraphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        // Draw the logo centered on the QR code
        qrCodeGraphics.drawImage(logoImage, centerX, centerY, null);
        qrCodeGraphics.dispose();
    }
}