package com.starwars.BeyondStars.UserInterface;

import com.starwars.BeyondStars.service.SwapiService;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StarWarsUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Star Wars Data Viewer");

        TextField searchField = new TextField();
        searchField.setPromptText("Enter character name or ID");

        Button searchButton = new Button("Search");

        TextArea resultArea = new TextArea();
        resultArea.setEditable(false);

        searchButton.setOnAction(e -> {
            String searchText = searchField.getText();
            resultArea.setText("Results for: " + searchText);
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(searchField, searchButton, resultArea);

        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
