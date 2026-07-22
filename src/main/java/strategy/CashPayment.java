package com.example.onlinefooddeliverysystem.strategy;

public class CashPayment implements PaymentStrategy {

    @Override
    public void processPayment(double amount) {
        System.out.println("Cash on Delivery selected. Amount: $" + amount);
    }
}