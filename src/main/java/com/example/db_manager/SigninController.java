package com.example.db_manager;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SigninController {
        @FXML
        private TextField password;

        @FXML
        void close(ActionEvent event) {
            Platform.exit();
        }

        @FXML
        void signin(ActionEvent event) throws IOException {
            String text = password.getText();
            System.out.println("user typed: " + text);

            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("get.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }catch(IOException e){
                System.out.println("Get view not found");
            }
        }
}