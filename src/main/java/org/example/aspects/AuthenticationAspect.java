package org.example.aspects;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class AuthenticationAspect {

    @Pointcut("within(org.example..*)")
    public void authenticatingPointCut() {}

    @Pointcut("within(org.example..*)")
    public void authorizationPointCut() {}

    @Before("authenticatingPointCut() && authorizationPointCut()")
    public void authenticate(){
        log.info("[Before] Authenticating & authorization request");
    }

    @Before("execution(* org.example.logic.ShoppingCardLogic.checkout(..)) && args(status)")
    public void checkRole(String status) {
        System.out.println("[Security] Checkout operation requested with status: " + status);
    }

    @Before(value = "execution(* org.example.logic.ShoppingCardLogic.updateInventory(..)) && args(productId)", argNames = "productId")
    public void checkInventoryAccess(String productId) {
        System.out.println("[Security] Inventory update requested for product: " + productId);
    }

    @Before(value = "execution(* org.example.logic.ShoppingCardLogic.calculateTotal(..)) && args(price, quantity)", argNames = "price,quantity")
    public void logPriceCheck(double price, int quantity) {
        System.out.println("[Security] Price calculation requested for " + quantity + " items at " + price + " each");
    }
}
