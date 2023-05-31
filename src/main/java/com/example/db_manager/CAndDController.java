package com.example.db_manager;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class CAndDController implements Initializable {

    @FXML
    private Text title;
    @FXML
    private Button cOrUButton;
    @FXML
    private Text error;
    @FXML
    public TextField passwordField;
    @FXML
    public TextField nameField;
    @FXML
    public TextField salaryField;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        title.setText("Create");
        cOrUButton.setText("Create");
    }
    @FXML
    void cancel (ActionEvent event) throws IOException{
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("get.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch(IOException e){
            System.out.println("get view not found");
        }
    }

    public void CorU(ActionEvent event) {

        // check if all fields are filed and return error messaage if not
        if (passwordField.getText().equals("") || nameField.getText().equals("") || salaryField.getText().equals("")){
            error.setText("Fill all fields");
            return;
        }

        // execute the query
        try{
            Connection connection = Db.connection;
            Statement stmt = connection.createStatement();
            String sql = "insert into users (name, salary, password) values( '" + nameField.getText() + "', '" + salaryField.getText() + "' , '" + passwordField.getText() +"' )";
            stmt.executeUpdate(sql);
        }catch(SQLException e){
            e.printStackTrace();
        }

        // go back to get scene
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("get.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch(IOException e){
            System.out.println("get view not found");
        }
    }
}
