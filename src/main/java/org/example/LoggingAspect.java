package org.example;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* org.example.ShoppingCard.checkout(..))")
    public void beforeLogger(JoinPoint joinPoint) {
        String arg = joinPoint.getArgs()[0].toString();
        System.out.println("Before Logger with argument: " + arg);
    }

    @After("execution(* *.*.*.*(..))")
    public void afterLogger(){
        System.out.println("After Logger");
    }

    @Pointcut("execution(* org.example.ShoppingCard.quantity(..))")
    public void afterReturningPointCut(){

    }

    @AfterReturning(pointcut = "afterReturningPointCut()", returning = "retValue")
    public void afterReturning(String retValue) {
        System.out.println("After Returning: " + retValue);
    }
}
