package com.example.db_manager;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;

public class SigninController {

        @FXML
        private TextField password;

        @FXML
        void close(ActionEvent event) {
            Platform.exit();
        }

        @FXML
        void signin(ActionEvent event) {
            String text = password.getText();
            System.out.println("user typed: " + text);

        }

}