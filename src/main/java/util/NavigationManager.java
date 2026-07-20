package com.example.onlinefooddeliverysystem.util;

import com.example.onlinefooddeliverysystem.session.SessionData;
import com.example.onlinefooddeliverysystem.session.SessionManager;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class NavigationManager {

    public static void navigateTo(Event event, String fxmlFile) {
        try {

            SessionData session = SessionManager.loadSession();
            if (session != null && session.getUser() != null) {
                SessionManager.saveSession(new SessionData(session.getUser(), fxmlFile));
            }


            Parent root = FXMLLoader.load(NavigationManager.class.getResource("/fxml/" + fxmlFile));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 800, 600));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}