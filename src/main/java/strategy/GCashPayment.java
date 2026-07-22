package com.example.onlinefooddeliverysystem.strategy;

public class GCashPayment implements PaymentStrategy {

    @Override
    public void processPayment(double amount) {
        System.out.println("GCash payment processed. Amount: $" + amount);
    }
}