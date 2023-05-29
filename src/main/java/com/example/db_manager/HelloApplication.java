package com.example.db_manager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // database connection
        Db db = new Db();
        db.connect();



        // load first fxml file
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("signin.fxml"));

        // scene config logic
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("db Manager");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}