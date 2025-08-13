/*
Business Entity Library (BEL)
Copyright (C) 2025  D P Bennett & Associates Limited

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU Affero General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.

Email: info@dpbennett.com.jm
 */
package jm.com.dpbennett.business.entity.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import javax.imageio.ImageIO;
import org.apache.batik.util.Base64EncoderStream;

public class QRCodeGenerator {

    public static void main(String[] args) throws WriterException, IOException {
        System.out.println(QRCodeGenerator.getQRCodeImageData("getQRCodeData()", 
                125)); // tk make system option
    }
    
    public static String getQRCodeImageData(String qrCodeText, int size) throws WriterException, IOException {
        return base64Encode(convertBufferedImageToByteArray(createQRBufferedImage(qrCodeText, size)));
    }
    
    private static BufferedImage createQRBufferedImage(String qrCodeText, int size)
            throws WriterException, IOException {
        // Create the ByteMatrix for the QR-Code that encodes the given String
        Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<>();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix byteMatrix = qrCodeWriter.encode(qrCodeText, BarcodeFormat.QR_CODE, size, size, hintMap);
        // Make the BufferedImage that is to hold the QRCode
        int matrixWidth = byteMatrix.getWidth();
        BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
        image.createGraphics();

        Graphics2D graphics = (Graphics2D) image.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, matrixWidth, matrixWidth);
        // Paint and save the image using the ByteMatrix
        graphics.setColor(Color.BLACK);

        for (int i = 0; i < matrixWidth; i++) {
            for (int j = 0; j < matrixWidth; j++) {
                if (byteMatrix.get(i, j)) {
                    graphics.fillRect(i, j, 1, 1);
                }
            }
        }
        
        return image;

    }

    private static String base64Encode(final byte[] data) {
        ByteArrayOutputStream b64out = new ByteArrayOutputStream();
        Base64EncoderStream enc = new Base64EncoderStream(b64out);
        try {
            enc.write(data);
        } catch (IOException ex) {

        } finally {
            try {
                enc.close();
            } catch (IOException ex) {

            }
        }
        return b64out.toString();
    }

    private static byte[] convertBufferedImageToByteArray(BufferedImage bi) {

        byte[] bytes = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {

            ImageIO.write(bi, "png", baos);
            bytes = baos.toByteArray();

        } catch (IOException ex) {
            System.out.println(ex);
        }

        return bytes;
    }

}
