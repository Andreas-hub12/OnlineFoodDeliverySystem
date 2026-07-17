module com.example.onlinefooddeliverysystem {

    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.onlinefooddeliverysystem to javafx.fxml;
    opens com.example.onlinefooddeliverysystem.controller to javafx.fxml;
    opens com.example.onlinefooddeliverysystem.model to javafx.base;

    exports com.example.onlinefooddeliverysystem;
    exports com.example.onlinefooddeliverysystem.controller;
    exports com.example.onlinefooddeliverysystem.model;

}