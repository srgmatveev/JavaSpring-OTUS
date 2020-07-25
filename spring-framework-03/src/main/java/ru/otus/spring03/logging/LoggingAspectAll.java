package ru.otus.spring03.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspectAll {

    @Around("within(ru.otus.spring03..*)")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("logAround() is rinning!");
        System.out.println("hijacking method " + joinPoint.getSignature().getName());
        System.out.println("hijacking arguments: " + Arrays.toString(joinPoint.getArgs()));
        System.out.println("Around before is running!");
        Object object = joinPoint.proceed();
        System.out.println("Around after is running!");
        System.out.println("****LoggingAspect.logAroundAllMethods() : " + joinPoint.getSignature().getName() + ": After Method Execution");
        System.out.println("Return param is " + object.getClass().getSimpleName());
        return object;
    }

}

