package com.example.onlinefooddeliverysystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;

import java.io.IOException;

public class TrackingController {

    @FXML
    private void handleHome(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/review.fxml"));

        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();

        stage.setScene(new Scene(root));
    }

    @FXML
    private void handleRefresh(ActionEvent event) {

        System.out.println("Status Updated");
    }

}