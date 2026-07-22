package com.example.onlinefooddeliverysystem.controller;

import com.example.onlinefooddeliverysystem.model.FoodItem;
import com.example.onlinefooddeliverysystem.util.CartManager;
import com.example.onlinefooddeliverysystem.util.NavigationManager;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.example.onlinefooddeliverysystem.strategy.PaymentStrategy;
import com.example.onlinefooddeliverysystem.strategy.CashPayment;
import com.example.onlinefooddeliverysystem.strategy.GCashPayment;
import com.example.onlinefooddeliverysystem.strategy.CreditCardPayment;
import com.example.onlinefooddeliverysystem.facade.OrderFacade;

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

        PaymentStrategy paymentStrategy;

        switch (cmbPayment.getValue()) {
            case "Cash on Delivery":
                paymentStrategy = new CashPayment();
                break;

            case "GCash":
                paymentStrategy = new GCashPayment();
                break;

            case "Credit Card":
                paymentStrategy = new CreditCardPayment();
                break;

            default:
                throw new IllegalArgumentException("Invalid payment method");
        }

        OrderFacade orderFacade = new OrderFacade();

        if (orderFacade.placeOrder(paymentStrategy)) {
            NavigationManager.navigateTo(event, "tracking.fxml");
        }
    }

    @FXML
    private void handleBack(ActionEvent event) {
        NavigationManager.navigateTo(event, "cart.fxml");
    }
}