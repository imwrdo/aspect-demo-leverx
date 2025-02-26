package org.example;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class AuthenticationAspect {

    private static String currentUser;
    private static String userRole;

    @Pointcut("within(org.example..*)")
    public void authenticatingPointCut() {}

    @Pointcut("within(org.example..*)")
    public void authorizationPointCut() {}

    @Before("authenticatingPointCut() && authorizationPointCut()")
    public void authenticate(){
        System.out.println("[Before] Authenticating & authorization request");
    }

    @Before("execution(* org.example.ShoppingCard.checkout(..))")
    public void checkRole() {
        if (!"ADMIN".equals(userRole)) {
            throw new RuntimeException("Unauthorized access: admin role required");
        }
        System.out.println("[Auth] User " + currentUser + " with role " + userRole + " authorized");
    }

    @Before("execution(* org.example.ShoppingCard.updateInventory(..))")
    public void checkInventoryAccess() {
        if (!"ADMIN".equals(userRole)) {
            throw new RuntimeException("Inventory updates require admin privileges");
        }
        System.out.println("[Security] Inventory update authorized for user: " + currentUser);
    }

    @Before("execution(* org.example.ShoppingCard.calculateTotal(..))")
    public void logPriceCheck() {
        System.out.println("[Security] Price calculation requested by user: " + currentUser);
    }

    public static void setCurrentUser(String user, String role) {
        currentUser = user;
        userRole = role;
    }
}
