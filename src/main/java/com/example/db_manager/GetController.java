package com.example.db_manager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class GetController implements Initializable {

    @FXML
    private VBox textFieldContainer;

    @FXML
    private ComboBox<String> tablesComboBox;

    // to invoke this function while initializing the component
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        getTables();
    }

    @FXML
    void get(ActionEvent event){

    }

    @FXML
    void goToCreatePage (ActionEvent event) throws IOException{
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("cAndD.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch(IOException e){
            System.out.println("cAndD view not found");
        }
    }

    public void getTables() {
        Connection connection = Db.connection;

        try {
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("show tables from db_manager");

            // what is observable list
            ObservableList<String> tableNames = FXCollections.observableArrayList();

            while (rs.next()) {
                tableNames.add(rs.getString("Tables_in_db_manager"));
            }

            tablesComboBox.setItems(tableNames);

            rs.close();
            stm.close();

        } catch(SQLException e) {
            e.printStackTrace();
        }

        // call get cols function it the user has chosen a table name
        if (tablesComboBox.getValue() != null) getTableColumns(tablesComboBox.getValue());
    }

    public void getTableColumns(String tableName){

        Connection connection = Db.connection;

        textFieldContainer.getChildren().clear();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM "+ tableName);

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                TextField textField = new TextField();
                textField.setPromptText(columnName);
                textFieldContainer.getChildren().add(textField);
            }

            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
