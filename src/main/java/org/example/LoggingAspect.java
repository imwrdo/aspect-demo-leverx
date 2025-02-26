package org.example;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* org.example.ShoppingCard.checkout(..))")
    public void beforeLogger(JoinPoint joinPoint) {
        String arg = joinPoint.getArgs()[0].toString();
        System.out.println("[Before] Method is about to execute with argument: " + arg);
    }

    @After("execution(* *.*.*.*(..))")
    public void afterLogger(){
        System.out.println("[After] Method has finished execution");
    }

    @Pointcut("execution(* org.example.ShoppingCard.calculateTotal(..))")
    public void afterReturningPointCut(){

    }

    @AfterReturning(pointcut = "afterReturningPointCut()", returning = "retValue")
    public void afterReturningLogger(Object retValue) {
        System.out.println("[After Returning] Method returned: " + retValue);
    }

    @Around("execution(* org.example.ShoppingCard.*(..))")
    public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();
        System.out.println("[Around] " + joinPoint.getSignature().getName() + 
                         " took " + (end - start) + "ms to execute");
        return result;
    }

    @AfterThrowing(pointcut = "execution(* org.example.ShoppingCard.*(..))", 
                   throwing = "exception")
    public void afterThrowingLogger(JoinPoint joinPoint, Exception exception) {
        System.out.println("[Exception] Method " + joinPoint.getSignature().getName() + 
                         " threw exception: " + exception.getMessage());
    }

    @Before("execution(* org.example.ShoppingCard.calculateTotal(..))")
    public void logCalculation(JoinPoint joinPoint) {
        System.out.println("[Calculation] Calculating total with args: " + 
            Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(
        pointcut = "execution(* org.example.ShoppingCard.calculateTotal(..))",
        returning = "result"
    )
    public void logCalculationResult(Object result) {
        System.out.println("[Calculation Result] Total calculated: " + result);
    }

    @Around("execution(* org.example.ShoppingCard.updateInventory(..))")
    public Object logInventoryUpdate(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("[Inventory] Starting inventory update...");
        try {
            Object result = joinPoint.proceed();
            System.out.println("[Inventory] Successfully updated inventory");
            return result;
        } catch (Exception e) {
            System.out.println("[Inventory] Failed to update inventory: " + e.getMessage());
            throw e;
        }
    }
}
