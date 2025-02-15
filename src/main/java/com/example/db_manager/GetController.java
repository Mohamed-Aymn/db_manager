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

    // control all fxml elements that should be controlled
    public TableView table;
    public VBox textFieldContainer;
    public TextField idTextField;
    public TextField nameTextField;
    public TextField salaryTextField;
    @FXML private TableColumn<User, Integer> idColumn;
    @FXML private TableColumn<User, String> nameColumn;
    @FXML private TableColumn<User, Integer> salaryColumn;


    // invoke a function when the controller is loaded to import data in the table one the scene is loaded
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        importData();
    }

    // import data function that will be called one the scene is loaded
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

        // don't do anything if input fields are empty
        if (idTextField.getText().equals("") && nameTextField.getText().equals("") && salaryTextField.getText().equals(""))
            return;

        String query = "select id, name, salary from users where ";
        boolean isFirst = true;

        // create an array of input fields to make manipulation easy with array methods
        TextField [] textFields = new TextField[3];
        textFields[0] = idTextField;
        textFields[1] = nameTextField;
        textFields[2] = salaryTextField;

        // create dynamic sql query to ignore empty fields
        for (int i = 0; i < 3; i++){
            if (!textFields[i].getText().equals("")) {
                if (i != 0 && !isFirst){
                    query += "and ";
                }
                query += textFields[i].getPromptText().toLowerCase() + " = " + "'" + textFields[i].getText() + "'" + " ";
                isFirst = false;
            }
        }

        // call database static variable from Db class and execute the query
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

    // just switch to create view
    @FXML
    void goToCreatePage (ActionEvent event) throws IOException{
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("create.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("create view not found");
        }
    }
}

