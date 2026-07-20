package com.example.onlinefooddeliverysystem.controller;

import com.example.onlinefooddeliverysystem.session.SessionManager;
import com.example.onlinefooddeliverysystem.util.NavigationManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class AdminController {

    @FXML
    private void handleManageFood(ActionEvent event) {
        NavigationManager.navigateTo(event, "manageFood.fxml");
    }

    @FXML
    private void handleLogout(ActionEvent event) {
        SessionManager.clearSession();
        NavigationManager.navigateTo(event, "login.fxml");
    }
}