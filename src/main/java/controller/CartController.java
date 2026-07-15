package com.example.onlinefooddeliverysystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;

import java.io.IOException;

public class CartController {

    @FXML
    private void handleCheckout(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/checkout.fxml"));

        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();

        stage.setScene(new Scene(root));
    }

    @FXML
    private void handleBack(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/menu.fxml"));

        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();

        stage.setScene(new Scene(root));
    }

}