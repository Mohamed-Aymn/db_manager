module com.example.db_manager {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.db_manager to javafx.fxml;
    exports com.example.db_manager;
}