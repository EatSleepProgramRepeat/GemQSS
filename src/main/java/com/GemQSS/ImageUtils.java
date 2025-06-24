package com.GemQSS;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class ImageUtils {
    public static String encodeToBase64(String imagePath) {
        try {
            // convert file to byte code array
            byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));

            // encode to base 64
            return Base64.getEncoder().encodeToString(imageBytes);

        } catch (IOException e) {
            // prints the error to the console and returns a failure message.
            System.err.println("Error reading the image file: " + e.getMessage());
            return "Failed to encode the image.";
        }
    }
}