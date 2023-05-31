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
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class GetController implements Initializable {

    public TableView table;
    public VBox textFieldContainer;
    public TextField idTextField;
    public TextField nameTextField;
    public TextField salaryTextField;
    @FXML private TableColumn<User, Integer> idColumn;
    @FXML private TableColumn<User, String> nameColumn;
    @FXML private TableColumn<User, Integer> salaryColumn;


    // invoke a function when the controller is loaded
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        importData();
    }

    void importData(){
        ObservableList<User> data = FXCollections.observableArrayList();
        table.setItems(data);

        Connection connection = Db.connection;

        String sql = "SELECT id, name, salary FROM users";
        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            ObservableList<User> Users = FXCollections.observableArrayList();

            while (rs.next()){
                User user = new User(rs.getInt("id"), rs.getString("name"), rs.getInt("salary"));
                Users.add(user);
            }

            table.setItems(Users);
            idColumn.setCellValueFactory(f -> new SimpleObjectProperty<>(f.getValue().getId()));
            nameColumn.setCellValueFactory(f -> new SimpleStringProperty(f.getValue().getName()));
            salaryColumn.setCellValueFactory(f -> new SimpleObjectProperty<>(f.getValue().getSalary()));
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @FXML
    void get(ActionEvent event){
        String query = "select id, name, salary from users where ";
        boolean isFirst = true;

        TextField [] textFields = new TextField[3];
        textFields[0] = idTextField;
        textFields[1] = nameTextField;
        textFields[2] = salaryTextField;

        // create dynamic sql query
        for (int i = 0; i < 3; i++){
            if (!textFields[i].getText().equals("")) {
                if (i != 0 && !isFirst){
                    query += "and ";
                }
                query += textFields[i].getPromptText().toLowerCase() + " = " + "'" + textFields[i].getText() + "'" + " ";
                isFirst = false;
            }
        }
        Connection connection = Db.connection;
        try {
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(query);
            ObservableList<User> data = FXCollections.observableArrayList();
            while(rs.next()){
                User user = new User(rs.getInt("id"), rs.getString("name"), rs.getInt("salary"));
                data.add(user);
            }
            table.setItems(data);
            rs.close();
            stm.close();

        } catch(SQLException e) {
            e.printStackTrace();
        }
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
}

