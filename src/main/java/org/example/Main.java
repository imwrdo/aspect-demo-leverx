package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
        ShoppingCard card = context.getBean(ShoppingCard.class);

        // Root permissions. If you don't have admin user, program will throw exception :)
        AuthenticationAspect.setCurrentUser("admin","ADMIN");

        // Test existing functionality
        try{
            // If invalid, throws exeption
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