package com.GemQSS;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
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

        Scene scene = new Scene(root, primaryStage.getWidth(), primaryStage.getHeight());
        // Create and add the text field
        TextField textField = qualifiedTextField(scene.getWidth(), scene.getHeight()); // Example width and height
        root.getChildren().add(textField);

        primaryStage.setScene(scene);
        scene.setFill(new Color(0.1, 0.1, 0.1, 0.85));

        primaryStage.show();
    }

    private TextField qualifiedTextField(double width, double height) {
        TextField qualifiedTextField = new TextField();
        qualifiedTextField.setPrefWidth(width);
        qualifiedTextField.setPrefHeight(height);
        qualifiedTextField.setMaxWidth(width);
        qualifiedTextField.setMaxHeight(height);
        qualifiedTextField.setFont(Font.font("monospace", height / 2.5));
        qualifiedTextField.setStyle("-fx-background-color: rgba(0,0,0,0.68); -fx-text-fill: white; -fx-border-color: transparent; -fx-border-width: 0px; -fx-background-radius: 0; -fx-background-insets: 0; -fx-control-inner-background: transparent; -fx-padding: 0 8 0 8;");
        StackPane.setMargin(qualifiedTextField, new Insets(0));
        return qualifiedTextField;
    }
}