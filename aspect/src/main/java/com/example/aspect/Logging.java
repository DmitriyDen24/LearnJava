package com.example.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

@Aspect

public class Logging {

    @Pointcut("execution(* com.example.aspect.Developer.*(..))")
    public void selectAllMethodsDeveloper() {}

    @Pointcut("execution(* com.example.aspect.Developer.getName(..))")
    public void selectMethodGetName() {}

    @Pointcut("execution(* com.example.aspect.Developer.getSpecialty(..))")
    public void selectMethodGetSpecialty() {}

    @Before("selectAllMethodsDeveloper()")
    public void beforeAdvice(JoinPoint jp) {
        System.out.println("Before call method: "+ jp.getSignature().toShortString()+ "| Args => " + Arrays.asList(jp.getArgs()));
    }

    @After("selectAllMethodsDeveloper()")
    public void afterAdvice(JoinPoint jp) {
        System.out.println("After call method: " + jp.getSignature().toShortString()+ "| Args => " + Arrays.asList(jp.getArgs()));
    }

    @AfterReturning(pointcut = "selectMethodGetName()", returning = "someValue")
    public void afterReturningName(Object someValue) {
        System.out.println("Value: " + someValue);

    }
    @AfterReturning(pointcut = "selectMethodGetSpecialty()", returning = "someValue")
    public void afterReturningSpecialty(Object someValue) {
        System.out.println("Value: " + someValue);
    }
}
