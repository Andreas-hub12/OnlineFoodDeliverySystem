package com.example.onlinefooddeliverysystem.controller;

import com.example.onlinefooddeliverysystem.model.FoodItem;
import com.example.onlinefooddeliverysystem.util.CartManager;
import com.example.onlinefooddeliverysystem.util.NavigationManager;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class CheckoutController {
    @FXML private ChoiceBox<String> cmbPayment;
    @FXML private ListView<String> listOrderSummary;
    @FXML private Label lblTotal;
    @FXML private TextField txtAddress;

    @FXML
    public void initialize() {
        cmbPayment.setItems(FXCollections.observableArrayList("Cash on Delivery", "GCash", "Credit Card"));

        for (FoodItem item : CartManager.getCartItems()) {
            listOrderSummary.getItems().add(item.getName() + " - $" + String.format("%.2f", item.getPrice()));
        }
        lblTotal.setText(String.format("Total: $%.2f", CartManager.getTotal()));
    }

    @FXML
    private void handleConfirmOrder(ActionEvent event) {
        if (cmbPayment.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Payment Error");
            alert.setContentText("Please select a payment method.");
            alert.showAndWait();
            return;
        }
        CartManager.clearCart();
        NavigationManager.navigateTo(event, "tracking.fxml");
    }

    @FXML
    private void handleBack(ActionEvent event) {
        NavigationManager.navigateTo(event, "cart.fxml");
    }
}