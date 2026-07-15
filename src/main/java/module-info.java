module com.example.onlinefooddeliverysystem {

    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.onlinefooddeliverysystem to javafx.fxml;
    opens com.example.onlinefooddeliverysystem.controller to javafx.fxml;

    exports com.example.onlinefooddeliverysystem;
    exports com.example.onlinefooddeliverysystem.controller;

}