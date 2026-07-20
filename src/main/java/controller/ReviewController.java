package com.example.onlinefooddeliverysystem.controller;

import com.example.onlinefooddeliverysystem.util.NavigationManager;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ReviewController {
    @FXML private ChoiceBox<String> cmbRating;
    @FXML private TextArea txtFeedback;

    @FXML
    public void initialize() {
        cmbRating.setItems(FXCollections.observableArrayList("5 - Excellent", "4 - Good", "3 - Average", "2 - Poor", "1 - Terrible"));
    }

    @FXML
    private void handleSubmit(ActionEvent event) {
        if (cmbRating.getValue() == null) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Please select a rating.");
            a.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Thank you for your feedback!");
        alert.showAndWait();

        NavigationManager.navigateTo(event, "home.fxml");
    }

    @FXML private void handleHome(ActionEvent event) {
        NavigationManager.navigateTo(event, "home.fxml");
    }
}