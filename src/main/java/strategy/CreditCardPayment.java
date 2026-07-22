package com.example.onlinefooddeliverysystem.strategy;

public class CreditCardPayment implements PaymentStrategy {

    @Override
    public void processPayment(double amount) {
        System.out.println("Credit Card payment processed. Amount: $" + amount);
    }
}