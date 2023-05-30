package com.example.db_manager;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SigninController {

        @FXML
        private Label errorMessageLabel;
        @FXML
        private TextField password;

        @FXML
        void close(ActionEvent event) {
            Platform.exit();
        }

        @FXML
        void signin(ActionEvent event) throws IOException, SQLException  {
            Connection connection = Db.connection;
            boolean isAdmin = false;

            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from users where name = 'admin' AND password = '"+ password.getText() +"'");

                if(resultSet.next()){
                    if (resultSet.getString("name").equals("admin")) isAdmin = true;
                }

                resultSet.close();
                statement.close();

            }catch(SQLException e){
                e.printStackTrace();
            }



            // don't continue the function if the user don't pass auth
            if (!isAdmin) {
                errorMessageLabel.setText("Invalid password");
                return;
            };

            // switch to get scene
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