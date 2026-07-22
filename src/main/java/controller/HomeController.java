package com.example.onlinefooddeliverysystem.controller;

import com.example.onlinefooddeliverysystem.dao.FoodDAO;
import com.example.onlinefooddeliverysystem.util.AppState;
import com.example.onlinefooddeliverysystem.util.NavigationManager;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import com.example.onlinefooddeliverysystem.session.SessionManager;

public class HomeController {

    @FXML
    private ListView<String> listRestaurants;

    private final FoodDAO foodDAO = new FoodDAO();

    @FXML
    public void initialize() {

        listRestaurants.setItems(FXCollections.observableArrayList(foodDAO.getAllRestaurants()));


        listRestaurants.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                AppState.setSelectedRestaurant(newVal);

            }
        });
    }

    @FXML
    private void handleViewMenu(ActionEvent event) {
        String selected = listRestaurants.getSelectionModel().getSelectedItem();
        if (selected != null) {
            AppState.setSelectedRestaurant(selected);
        }
        NavigationManager.navigateTo(event, "menu.fxml");
    }

    @FXML
    private void handleLogout(ActionEvent event) {
        SessionManager.clearSession();
        NavigationManager.navigateTo(event, "login.fxml");
    }
}