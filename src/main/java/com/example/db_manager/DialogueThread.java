package com.example.db_manager;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;


public class DialogueThread extends Thread {
    private String message;

    public DialogueThread(String message) {
        this.message = message;
    }

    private DialogueCallback callback;

    public DialogueThread(String message, DialogueCallback callback) {
        this.message = message;
        this.callback = callback;
    }


    @Override
    public void run() {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(message);
            alert.setHeaderText(null);
            alert.getButtonTypes().clear();
            alert.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
            Optional<ButtonType> result = alert.showAndWait();
//            if (result.isPresent() && result.get() == ButtonType.OK) {
//                System.out.println("ok");
//            } else {
//                System.out.println("cancel");
//            }
            if (result.isPresent() && result.get() == ButtonType.OK) {
                callback.onOkClicked();
            } else {
                callback.onCancelClicked();
            }

//            System.out.println("non");

        });
    }

    public interface DialogueCallback {
        void onOkClicked();
        void onCancelClicked();
    }
}