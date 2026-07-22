package com.example.onlinefooddeliverysystem.facade;

import com.example.onlinefooddeliverysystem.strategy.PaymentStrategy;
import com.example.onlinefooddeliverysystem.util.CartManager;

public class OrderFacade {

    public boolean placeOrder(PaymentStrategy paymentStrategy) {

        double total = CartManager.getTotal();

        paymentStrategy.processPayment(total);

        // Future enhancements:
        // Save order to database
        // Send confirmation
        // Notify restaurant

        CartManager.clearCart();

        return true;
    }
}