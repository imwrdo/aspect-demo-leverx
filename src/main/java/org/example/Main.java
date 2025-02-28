package org.example;

import org.example.config.BeanConfig;
import org.example.logic.ShoppingCardLogic;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
        ShoppingCardLogic card = context.getBean(ShoppingCardLogic.class);

        // Test existing functionality
        try{
            // If invalid, throws exception
            card.checkout("VALID");
        }catch(RuntimeException e){
            System.out.println("\nError: " + e.getMessage());
        }


        // Test new functionality
        double total = card.calculateTotal(29.99, 3);
        System.out.println("\nTotal price: " + total);

        try {
            card.updateInventory("PROD-001", 50);
        } catch (RuntimeException e) {
            System.out.println("\nError: " + e.getMessage());
        }

        Optional<String> status = card.getOrderStatus("ORD-001");
        System.out.println("\nOrder status: " + status.orElse("Not found"));
    }
}