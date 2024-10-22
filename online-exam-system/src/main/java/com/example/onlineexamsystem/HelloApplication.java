package com.example.onlineexamsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.question;
import model.quiz;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600,550);
        stage.setTitle("Sign UP");
        stage.setScene(scene);

        stage.show();

        createTables();

    }

    public static void createTables(){
        quiz.createTable();
        question.createTable();
    }
    public static void main(String[] args) {
        launch();
    }
}

