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

public class LoginController {

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private void handleLogin(ActionEvent event) throws IOException {

        String username = txtUsername.getText();
        String password = txtPassword.getText();

        UserDAO dao = new UserDAO();

        String role = dao.login(username, password);

        if (role == null) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Failed");
            alert.setHeaderText(null);
            alert.setContentText("Invalid username or password.");
            alert.showAndWait();
            return;
        }

        Parent root;

        if (role.equals("ADMIN")) {
            root = FXMLLoader.load(getClass().getResource("/fxml/admin.fxml"));
        } else {
            root = FXMLLoader.load(getClass().getResource("/fxml/home.fxml"));
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void handleRegister(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/register.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}