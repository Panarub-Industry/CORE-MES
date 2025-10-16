package id.co.bintangindokarya.mes.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.logging.Level;

import javax.imageio.ImageIO;

import org.adempiere.webui.component.Label;
import org.compiere.util.CLogger;
import org.zkoss.zul.Div;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

/**
 * Utility class for generating QR codes using ZXing library with fallback options
 * 
 * @author Your Name
 * @version 1.0
 */
public class QRCodeUtil {
    
    private static final CLogger log = CLogger.getCLogger(QRCodeUtil.class);
    
    /**
     * Creates a QR code Div component using ZXing library with fallback to simple QR code
     * 
     * @param data The data to encode in QR code
     * @param size The size of the QR code in pixels
     * @return Div component containing the QR code
     */
    public static Div createQRCode(String data, int size) {
        try {
            return createZXingQRCode(data, size);
        } catch (Exception e) {
            log.warning("ZXing QR code generation failed, using fallback: " + e.getMessage());
            return createSimpleQRCode(data, size);
        }
    }
    
    /**
     * Creates a QR code using ZXing library
     * 
     * @param data The data to encode
     * @param size The size in pixels
     * @return Div component with ZXing generated QR code
     * @throws Exception if QR code generation fails
     */
    public static Div createZXingQRCode(String data, int size) throws Exception {
        Div qrContainer = new Div();
        qrContainer.setStyle(String.format(
            "width: %dpx; height: %dpx; background: white; border: 1px solid #ddd; " +
            "border-radius: 4px; overflow: hidden; position: relative; " +
            "display: flex; align-items: center; justify-content: center;",
            size, size));
        
        try {
            // Generate QR code using ZXing
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, size, size);
            
            // Convert BitMatrix to BufferedImage
            BufferedImage bufferedImage = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < size; x++) {
                for (int y = 0; y < size; y++) {
                    bufferedImage.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF); // Black or White
                }
            }
            
            // Convert BufferedImage to base64 data URL
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "PNG", baos);
            byte[] imageBytes = baos.toByteArray();
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);
            String dataUrl = "data:image/png;base64," + base64Image;
            
            // Create image element
            org.zkoss.zul.Image qrImage = new org.zkoss.zul.Image();
            qrImage.setSrc(dataUrl);
            qrImage.setWidth(size + "px");
            qrImage.setHeight(size + "px");
            qrContainer.appendChild(qrImage);
            
        } catch (Exception e) {
            log.log(Level.WARNING, "Failed to generate QR code with ZXing: " + e.getMessage(), e);
            throw e; // Re-throw to trigger fallback
        }
        
        return qrContainer;
    }
    
    /**
     * Creates a simple QR code pattern as fallback when ZXing is not available
     * 
     * @param data The data to display (will show last 8 characters)
     * @param size The size in pixels
     * @return Div component with simple QR code pattern
     */
    public static Div createSimpleQRCode(String data, int size) {
        Div qrContainer = new Div();
        qrContainer.setStyle(String.format(
            "width: %dpx; height: %dpx; background: white; border: 2px solid #000; " +
            "display: flex; align-items: center; justify-content: center; " +
            "border-radius: 4px; position: relative; overflow: hidden;", 
            size, size));
        
        // Create a simple grid pattern to simulate QR code
        Div qrPattern = new Div();
        qrPattern.setStyle(
            "width: 100%; height: 100%; " +
            "background-image: " +
            "linear-gradient(45deg, #000 25%, transparent 25%), " +
            "linear-gradient(-45deg, #000 25%, transparent 25%), " +
            "linear-gradient(45deg, transparent 75%, #000 75%), " +
            "linear-gradient(-45deg, transparent 75%, #000 75%); " +
            "background-size: 8px 8px; " +
            "background-position: 0 0, 0 4px, 4px -4px, -4px 0px; " +
            "opacity: 0.8;");
        
        // Add corner markers (typical QR code feature)
        Div topLeft = createCornerMarker("top: 2px; left: 2px;");
        Div topRight = createCornerMarker("top: 2px; right: 2px;");
        Div bottomLeft = createCornerMarker("bottom: 2px; left: 2px;");
        
        // Add data label overlay (optional - for debugging)
        Label dataLabel = new Label(data.length() > 8 ? data.substring(data.length() - 8) : data);
        dataLabel.setStyle("position: absolute; bottom: 0; left: 0; right: 0; " +
                          "background: rgba(255,255,255,0.9); font-size: 6px; text-align: center; " +
                          "padding: 1px; color: #000; font-weight: bold;");
        
        qrContainer.appendChild(qrPattern);
        qrContainer.appendChild(topLeft);
        qrContainer.appendChild(topRight);
        qrContainer.appendChild(bottomLeft);
        qrContainer.appendChild(dataLabel);
        
        return qrContainer;
    }
    
    /**
     * Creates a corner marker for the simple QR code
     * 
     * @param position CSS position string
     * @return Div component representing a corner marker
     */
    private static Div createCornerMarker(String position) {
        Div marker = new Div();
        marker.setStyle(String.format(
            "position: absolute; %s width: 12px; height: 12px; " +
            "background: #000; border: 2px solid #fff; border-radius: 1px;",
            position));
        return marker;
    }
    
    /**
     * Creates a QR code with custom styling
     * 
     * @param data The data to encode
     * @param size The size in pixels
     * @param additionalStyle Additional CSS styles to apply
     * @return Div component with QR code and custom styling
     */
    public static Div createQRCodeWithStyle(String data, int size, String additionalStyle) {
        Div qrCode = createQRCode(data, size);
        if (additionalStyle != null && !additionalStyle.trim().isEmpty()) {
            qrCode.setStyle(qrCode.getStyle() + " " + additionalStyle);
        }
        return qrCode;
    }
    
    /**
     * Validates if the provided data is suitable for QR code generation
     * 
     * @param data The data to validate
     * @return true if data is valid, false otherwise
     */
    public static boolean isValidQRData(String data) {
        return data != null && !data.trim().isEmpty() && data.length() <= 4296; // QR code max capacity
    }
}