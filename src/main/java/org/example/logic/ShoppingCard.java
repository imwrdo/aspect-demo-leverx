package org.example.logic;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ShoppingCard {
    public void checkout(String status) {

        if ("INVALID".equals(status)) {
            throw new RuntimeException("Invalid checkout status");
        }
        System.out.println("\nCheckout Method from ShoppingCard is called\n");
    }


    public double calculateTotal(double price, int quantity) {
        return price * quantity;
    }

    public void updateInventory(String productId, int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        System.out.println("\nUpdating inventory for product: " + productId);
    }

    public Optional<String> getOrderStatus(String orderId) {
        if (orderId == null) {
            return Optional.empty();
        }
        return "Processing".describeConstable();
    }
}
