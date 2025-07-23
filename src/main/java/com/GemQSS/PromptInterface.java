package com.GemQSS;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.Screen;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class PromptInterface extends Application {
    private Timeline timeline;
    private boolean animationStatus = false;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("GemQSS");
        primaryStage.setAlwaysOnTop(true);

        // Sizing
        double screenWidth = Screen.getPrimary().getBounds().getWidth();
        double screenHeight = Screen.getPrimary().getBounds().getHeight();

        // Create the root pane with border
        StackPane root = new StackPane();
        root.setBorder(new Border(new BorderStroke(
                Color.rgb(2, 134, 136),
                BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                new BorderWidths(5)
        )));

        double startingWidth = screenWidth / 4;
        double startingHeight = 50;
        root.setPrefSize(screenWidth / 4, 50);

        Scene scene = new Scene(root, startingWidth, startingHeight);

        // Create and add the text field
        TextField textField = qualifiedTextField(root.getPrefWidth(), root.getPrefHeight());

        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.UNDECORATED); // Changed from TRANSPARENT
        scene.setFill(Color.rgb(60,60,60,0.9)); // Changed from TRANSPARENT
        root.setBackground(new Background(new BackgroundFill(
                Color.TRANSPARENT, // Make root background transparent
                CornerRadii.EMPTY,
                Insets.EMPTY
        )));
        primaryStage.setX(100);
        primaryStage.setY(100);

        root.getChildren().addAll(textField);

        // Animations to smoothly resize when the user presses enter
        double targetWidth = screenWidth / 3;
        double targetHeight = screenHeight / 1.25;
        System.out.println("start width and height: " + root.getPrefWidth() + " " + root.getPrefHeight());
        final double startWidth = root.getPrefWidth();
        final double startHeight = root.getPrefHeight();

        // Create properties to animate stage size
        SimpleDoubleProperty animatedWidth = new SimpleDoubleProperty(startingWidth);
        SimpleDoubleProperty animatedHeight = new SimpleDoubleProperty(startingHeight);
        animatedWidth.addListener((_, _, newVal) -> primaryStage.setWidth(newVal.doubleValue()));
        animatedHeight.addListener((_, _, newVal) -> primaryStage.setHeight(newVal.doubleValue()));

        primaryStage.setWidth(startingWidth);
        primaryStage.setHeight(startingHeight);
        root.setPrefSize(startingWidth, startingHeight);
        textField.setPrefSize(startingWidth, startingHeight);

        timeline = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(root.prefWidthProperty(), startWidth),
                        new KeyValue(root.prefHeightProperty(), startHeight),
                        new KeyValue(textField.prefWidthProperty(), startWidth),
                        new KeyValue(textField.prefHeightProperty(), startHeight),
                        new KeyValue(animatedWidth, startWidth),
                        new KeyValue(animatedHeight, startHeight)
                ),
                new KeyFrame(Duration.seconds(1.5),
                        new KeyValue(root.prefWidthProperty(), targetWidth, Interpolator.EASE_BOTH),
                        new KeyValue(root.prefHeightProperty(), targetHeight, Interpolator.EASE_BOTH),
                        new KeyValue(textField.prefWidthProperty(), targetWidth, Interpolator.EASE_BOTH),
                        new KeyValue(textField.prefHeightProperty(), targetHeight, Interpolator.EASE_BOTH),
                        new KeyValue(animatedWidth, targetWidth, Interpolator.EASE_BOTH),
                        new KeyValue(animatedHeight, targetHeight, Interpolator.EASE_BOTH)
                )
        );

        primaryStage.show();
    }

    private TextField qualifiedTextField(double width, double height) {
        TextField qualifiedTextField = new TextField();
        qualifiedTextField.setPrefWidth(width);
        qualifiedTextField.setPrefHeight(height);
        qualifiedTextField.setMaxWidth(width);
        qualifiedTextField.setMaxHeight(height);
        qualifiedTextField.setFont(Font.font("monospace", height / 2.5));
        qualifiedTextField.setBackground(Background.EMPTY);
        qualifiedTextField.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-control-inner-background: transparent;" +
                        "-fx-text-box-border: transparent;" +
                        "-fx-focus-color: transparent;" +
                        "-fx-faint-focus-color: transparent;" +
                        "-fx-highlight-fill: transparent;" +
                        "-fx-highlight-text-fill: white;" +
                        "-fx-prompt-text-fill: white;" +
                        "-fx-text-fill: white;"
        );

        qualifiedTextField.setOnAction(_ -> {
            if (!animationStatus) {
                timeline.play();
                animationStatus = true;
            }
        });
        StackPane.setMargin(qualifiedTextField, new Insets(0));
        return qualifiedTextField;
    }
}