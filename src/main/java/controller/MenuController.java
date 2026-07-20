package com.example.onlinefooddeliverysystem.controller;

import com.example.onlinefooddeliverysystem.dao.FoodDAO;
import com.example.onlinefooddeliverysystem.model.FoodItem;
import com.example.onlinefooddeliverysystem.util.AppState;
import com.example.onlinefooddeliverysystem.util.CartManager;
import com.example.onlinefooddeliverysystem.util.NavigationManager;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class MenuController {

    @FXML
    private Label lblRestaurantName;

    @FXML
    private TableView<FoodItem> tableMenu;

    @FXML
    private TableColumn<FoodItem, String> colName;

    @FXML
    private TableColumn<FoodItem, Double> colPrice;

    @FXML
    private TableColumn<FoodItem, String> colCategory;

    @FXML
    private Label lblCartCount;

    private final FoodDAO foodDAO = new FoodDAO();

    @FXML
    public void initialize() {
        String currentRestaurant = AppState.getSelectedRestaurant();

        if (lblRestaurantName != null) {
            lblRestaurantName.setText(currentRestaurant != null && !currentRestaurant.isEmpty() ? currentRestaurant : "All Restaurants");
        }

        if (colName != null) colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        if (colCategory != null) colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));

        if (colPrice != null) {
            colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
            colPrice.setCellFactory(col -> new TableCell<FoodItem, Double>() {
                @Override
                protected void updateItem(Double price, boolean empty) {
                    super.updateItem(price, empty);
                    if (empty || price == null) {
                        setText(null);
                    } else {
                        setText(String.format("$%.2f", price));
                    }
                }
            });
        }


        loadMenuData(currentRestaurant);
        updateCartCount();
    }

    private void loadMenuData(String restaurant) {
        if (tableMenu == null) return;

        List<FoodItem> items;
        if (restaurant != null && !restaurant.trim().isEmpty()) {
            items = foodDAO.getFoodByRestaurant(restaurant);
        } else {
            items = foodDAO.getAllFood();
        }

        tableMenu.setItems(FXCollections.observableArrayList(items));
    }

    @FXML
    private void handleAddToCart(ActionEvent event) {
        FoodItem selectedItem = tableMenu.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            CartManager.addItem(selectedItem);
            updateCartCount();

            showAlert(Alert.AlertType.INFORMATION, "Item Added", selectedItem.getName() + " has been added to your cart!");
        } else {
            showAlert(Alert.AlertType.WARNING, "No Selection", "Please select a food item from the menu first.");
        }
    }

    @FXML
    private void handleViewCart(ActionEvent event) {
        NavigationManager.navigateTo(event, "cart.fxml");
    }

    @FXML
    private void handleBack(ActionEvent event) {
        NavigationManager.navigateTo(event, "home.fxml");
    }

    private void updateCartCount() {
        if (lblCartCount != null) {
            lblCartCount.setText("Cart: " + CartManager.getCartItems().size());
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}