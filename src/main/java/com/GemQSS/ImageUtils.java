package com.GemQSS;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import javax.imageio.ImageIO;
import javax.swing.*;

public class ImageUtils {
    /**
     * Encodes a BufferedImage to a Base64 string.
     *
     * @param image the BufferedImage to encode
     * @return a Base64 encoded string of the image, or an error message if encoding fails
     */
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

    /**
     * Main method for taking screenshots.
     *
     * @return a BufferedImage of the screenshot.
     * @throws UnsupportedOperationException if the operating system is not supported.
     * @throws IOException if an error occurs while taking the screenshot.
     */
    public static BufferedImage takeScreenshot() throws UnsupportedOperationException, IOException {
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("win") || osName.contains("mac")) {
            return null;
        } else if (osName.contains("nix") || osName.contains("nux")) {
            return takeScreenshotWithLinuxTools();
        } else {
            throw new UnsupportedOperationException("Unsupported operating system: " + osName);
        }
    }

    /**
     * Takes a screenshot with the Robot class.
     *
     * @return a BufferedImage of the screenshot.
     * @throws IOException if an error occurs while taking the screenshot.
     */
    private static BufferedImage takeScreenshotWithRobot() throws IOException {
        try {
            java.awt.Robot robot = new java.awt.Robot();
            java.awt.Rectangle screenRect = new java.awt.Rectangle(java.awt.Toolkit.getDefaultToolkit().getScreenSize());
            return robot.createScreenCapture(screenRect);
        } catch (java.awt.AWTException e) {
            System.err.println("Error taking screenshot: " + e.getMessage());
            throw new IOException("Failed to take screenshot.", e);
        }
    }

    /**
     * Takes a screenshot using Linux tools like scrot or gri.
     *
     * @return a BufferedImage of the screenshot.
     * @throws UnsupportedOperationException if no screenshot tool is found.
     * @throws IOException if an error occurs while taking the screenshot.
     */
    private static BufferedImage takeScreenshotWithLinuxTools() throws UnsupportedOperationException, IOException {
        if (checkBinaryExists("grim")) {
            try {
                String command = "grim /tmp/screenshot.png";
                Process process = new ProcessBuilder("grim", "/tmp/screenshot.png").start();
                boolean finished = process.waitFor(5, java.util.concurrent.TimeUnit.SECONDS);
                if (!finished || process.exitValue() != 0) {
                    throw new IOException("Failed to take screenshot with grim.");
                }
                // Load the screenshot from the file
                File screenshotFile = new File("/tmp/screenshot.png");
                BufferedImage screenFullImage = ImageIO.read(screenshotFile);
                if (!screenshotFile.delete())
                    System.err.println("Failed to delete temporary screenshot file: " + screenshotFile.getAbsolutePath());
                return screenFullImage;
            } catch (IOException | InterruptedException e) {
                System.err.println("Error taking screenshot with grim: " + e.getMessage());
                throw new IOException("Failed to take screenshot with grim.", e);
            }
        } else {
            throw new UnsupportedOperationException("No suitable screenshot tool found on Linux system.");
        }
    }

    /**
     * Checks if a binary exists on a Linux system.
     *
     * @param binaryName the name of the binary to check.
     * @return true if the binary exists, false otherwise.
     */
    private static boolean checkBinaryExists(String binaryName) {
        try {
            Process process = new ProcessBuilder("which", binaryName).start();
            int exitCode = process.waitFor();
            return exitCode == 0;
        } catch (IOException | InterruptedException e) {
            System.err.println("Error checking for binary " + binaryName + ": " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("test program");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        BufferedImage screenshot = new BufferedImage(300, 300, BufferedImage.TYPE_INT_RGB);
        try {
            screenshot = takeScreenshot();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        JLabel screenshotLabel = new JLabel(encodeToString(screenshot));
        System.out.println(encodeToString(screenshot));
//        frame.getContentPane().add(screenshotLabel);
        frame.setVisible(true);
    }
}