package com.example.onlinefooddeliverysystem.controller;

import com.example.onlinefooddeliverysystem.dao.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterController {

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private void handleRegisterUser(ActionEvent event) throws IOException {
        String username = txtUsername.getText().trim();
        String password = txtPassword.getText().trim();
        String defaultRole = "CUSTOMER"; // Matches your login check structure

        if (username.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Registration Failed", "Please fill in all fields.");
            return;
        }

        UserDAO dao = new UserDAO();
        boolean isRegistered = dao.register(username, password, defaultRole);

        if (isRegistered) {
            showAlert(Alert.AlertType.INFORMATION, "Success", "Account created successfully! Redirecting to Login.");

            // Redirect back to login screen
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml")); // Verify this matches your login fxml file name
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } else {
            showAlert(Alert.AlertType.ERROR, "Registration Failed", "Could not register user. Username might be taken.");
        }
    }

    @FXML
    private void handleBackToLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml")); // Verify file name matches
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}