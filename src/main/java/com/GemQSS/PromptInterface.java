package com.GemQSS;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Screen;

public class PromptInterface extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("GemQSS");
        primaryStage.setAlwaysOnTop(true);

        // Sizing
        primaryStage.setHeight(50);
        // Set the width to 1/8 of the screen
        double screenWidth = Screen.getPrimary().getBounds().getWidth();
        System.out.println(screenWidth);
        primaryStage.setWidth(screenWidth / 4);

        StackPane root = new StackPane();
        root.setBorder(new Border(new BorderStroke(
                Color.rgb(2, 134, 136),
                BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                new BorderWidths(5)
        )));

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        scene.setFill(new Color(0.1, 0.1, 0.1, 0.85));

        primaryStage.show();
    }
}