package com.GemQSS;

import javafx.application.Application;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Application.launch(PromptInterface.class);
        if (args[0] != null && args[0].equals("screenshot")) {
            try {
                BufferedImage placeholderImage = ImageUtils.takeScreenshot();
            } catch (IOException e) {
                System.err.println("Error taking screenshot: " + e.getMessage());
            } catch (UnsupportedOperationException e) {
                System.err.println("Unsupported operating system: " + e.getMessage());
            }
        }
    }
}