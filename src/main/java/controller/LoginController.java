package com.example.onlinefooddeliverysystem.controller;

import com.example.onlinefooddeliverysystem.dao.UserDAO;
import com.example.onlinefooddeliverysystem.model.User;
import com.example.onlinefooddeliverysystem.session.SessionData;
import com.example.onlinefooddeliverysystem.session.SessionManager;
import com.example.onlinefooddeliverysystem.util.NavigationManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private void handleLogin(ActionEvent event) {

        String username = txtUsername.getText();
        String password = txtPassword.getText();

        UserDAO dao = new UserDAO();
        User user = dao.login(username, password);

        if (user == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Failed");
            alert.setHeaderText(null);
            alert.setContentText("Invalid username or password.");
            alert.showAndWait();
            return;
        }

        String targetFxml = "ADMIN".equals(user.getRole()) ? "admin.fxml" : "home.fxml";
        SessionManager.saveSession(new SessionData(user, targetFxml));
        NavigationManager.navigateTo(event, targetFxml);
    }

    @FXML
    private void handleRegister(ActionEvent event) {
        NavigationManager.navigateTo(event, "register.fxml");
    }
}