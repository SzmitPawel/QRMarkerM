package generator.qrmarkerm.generator;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.awt.image.BufferedImage;

public class ZxingGenerator {
    private final QRCodeWriter qrCodeWriter = new QRCodeWriter();

    public BufferedImage generateQRCodeImage(final String barcodeText) throws WriterException {
        BitMatrix bitMatrix = qrCodeWriter.encode(barcodeText, BarcodeFormat.QR_CODE, 200,200);

        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }
}