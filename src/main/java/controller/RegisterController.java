package com.example.onlinefooddeliverysystem.controller;

import com.example.onlinefooddeliverysystem.dao.UserDAO;
import com.example.onlinefooddeliverysystem.util.NavigationManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterController {

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private void handleRegisterUser(ActionEvent event) {
        String username = txtUsername.getText().trim();
        String password = txtPassword.getText().trim();
        String defaultRole = "CUSTOMER";

        if (username.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Registration Failed", "Please fill in all fields.");
            return;
        }

        UserDAO dao = new UserDAO();
        boolean isRegistered = dao.register(username, password, defaultRole);

        if (isRegistered) {
            showAlert(Alert.AlertType.INFORMATION, "Success", "Account created successfully! Redirecting to Login.");
            NavigationManager.navigateTo(event, "login.fxml");
        } else {
            showAlert(Alert.AlertType.ERROR, "Registration Failed", "Could not register user. Username might be taken.");
        }
    }

    @FXML
    private void handleBackToLogin(ActionEvent event) {
        NavigationManager.navigateTo(event, "login.fxml");
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}