package edu.du.sb1010_2.aop2;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Aspect
@Component
public class SampleAspect2 {
    @Around("execution(* *..*Greet.*(..))")
    public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("===== Around Advice =====");

        System.out.println(">>> Before proceed <<<");

        Object result = pjp.proceed();

        System.out.println(">>> After proceed <<<");

        return result;
    }
}
