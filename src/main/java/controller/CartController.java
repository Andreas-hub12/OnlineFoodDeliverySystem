package com.example.onlinefooddeliverysystem.controller;

import com.example.onlinefooddeliverysystem.model.FoodItem;
import com.example.onlinefooddeliverysystem.util.CartManager;
import com.example.onlinefooddeliverysystem.util.NavigationManager;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class CartController {

    @FXML
    private TableView<FoodItem> tableCart;

    @FXML
    private TableColumn<FoodItem, String> colName;

    @FXML
    private TableColumn<FoodItem, Double> colPrice;

    @FXML
    private TableColumn<FoodItem, String> colCategory;

    @FXML
    private Label lblTotal;

    @FXML
    public void initialize() {
        if (colName != null) colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        if (colCategory != null) colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));

        if (colPrice != null) {
            colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
            colPrice.setCellFactory(tc -> new TableCell<FoodItem, Double>() {
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

        refreshCart();
    }

    private void refreshCart() {
        if (tableCart != null) {
            tableCart.setItems(FXCollections.observableArrayList(CartManager.getCartItems()));
        }
        if (lblTotal != null) {
            lblTotal.setText(String.format("Total: $%.2f", CartManager.getTotal()));
        }
    }

    @FXML
    private void handleRemove(ActionEvent event) {
        FoodItem selected = tableCart.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "No Selection", "Please select an item to remove.");
            return;
        }

        CartManager.getCartItems().remove(selected);
        refreshCart();
    }

    @FXML
    private void handleClear(ActionEvent event) {
        CartManager.clearCart();
        refreshCart();
    }

    @FXML
    private void handleCheckout(ActionEvent event) {
        if (CartManager.getCartItems().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Cart Empty", "Please add items to your cart before checking out.");
            return;
        }
        NavigationManager.navigateTo(event, "checkout.fxml");
    }

    @FXML
    private void handleBack(ActionEvent event) {
        NavigationManager.navigateTo(event, "menu.fxml");
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}