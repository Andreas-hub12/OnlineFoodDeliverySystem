package com.example.onlinefooddeliverysystem.controller;

import com.example.onlinefooddeliverysystem.util.NavigationManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class TrackingController {
    @FXML private ProgressBar progStatus;
    @FXML private TextArea txtStatusUpdates;

    @FXML
    public void initialize() {
        progStatus.setProgress(0.45);
        txtStatusUpdates.setText("Order Placed Successfully.\nRestaurant is preparing your food.\nDriver assigned.");
    }

    @FXML
    private void handleHome(ActionEvent event) {
        NavigationManager.navigateTo(event, "home.fxml");
    }

    @FXML
    private void handleViewReview(ActionEvent event) {
        NavigationManager.navigateTo(event, "review.fxml");
    }
}