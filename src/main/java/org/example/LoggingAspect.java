package org.example;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class LoggingAspect {
    @Before("execution(* org.example.ShoppingCard.checkout())")
    public void logger(){
        System.out.println("Loggers");
    }
}
