package com.GemQSS;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import javax.imageio.ImageIO;

public class ImageUtils {
        public static String encodeToString(BufferedImage image) {
            if (image == null) {
                return "Failed to encode: input image is null.";
            }

            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                // Write the image data to an in-memory stream as a PNG
                ImageIO.write(image, "png", outputStream);

                // Get the raw byte data from the stream
                byte[] imageBytes = outputStream.toByteArray();

                // Encode the byte data to a Base64 string
                return Base64.getEncoder().encodeToString(imageBytes);

            } catch (IOException e) {
                // If an error occurs during the in-memory write process
                System.err.println("Error converting BufferedImage to bytes: " + e.getMessage());
                return "Failed to encode the image.";
            }
        }
    }