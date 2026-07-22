package com.example.onlinefooddeliverysystem;

import com.example.onlinefooddeliverysystem.model.User;
import com.example.onlinefooddeliverysystem.session.SessionData;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.example.onlinefooddeliverysystem.session.SessionManager;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        SessionData session = SessionManager.loadSession();
        FXMLLoader loader;

        if (session != null && session.getUser() != null) {
            User user = session.getUser();
            String currentScreen = session.getCurrentScreen();
            System.out.println("DEBUG: Loaded session screen -> " + currentScreen);


            if (currentScreen != null && !currentScreen.trim().isEmpty()) {
                loader = new FXMLLoader(getClass().getResource("/fxml/" + currentScreen));
            }

            else if ("ADMIN".equals(user.getRole())) {
                loader = new FXMLLoader(getClass().getResource("/fxml/admin.fxml"));
            } else {
                loader = new FXMLLoader(getClass().getResource("/fxml/home.fxml"));
            }

        } else {

            loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
        }

        Scene scene = new Scene(loader.load(), 800, 600);
        stage.setTitle("Online Food Delivery System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}