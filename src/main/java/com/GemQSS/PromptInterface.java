package com.GemQSS;

import javax.swing.*;
import java.awt.*;

public class PromptInterface {
    public PromptInterface() {
        // No title because it's a prompt
        JFrame frame = new JFrame("GemQSS");
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // TODO: Make the prompt interface size react to a config file

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width / 16;
        int height = 50;
        int x = (screenSize.width - width) / 2;
        int y = 100;
        frame.setBounds(x, y, width, height);

        frame.setBackground(new Color(56, 55, 55, 217));
        frame.getRootPane().setBorder(BorderFactory.createLineBorder(new Color(0, 104, 80), 6));
        System.out.println("Prompt Interface Launched");

        frame.setAlwaysOnTop(true);
        frame.setVisible(true);
    }
}