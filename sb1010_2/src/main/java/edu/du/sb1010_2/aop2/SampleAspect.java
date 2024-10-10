package edu.du.sb1010_2.aop2;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

//@Aspect
//@Component
public class SampleAspect {
    @Before("execution(* *..*Greet.*(..))")
    public void beforeAdvice(JoinPoint joinPoint) {
        System.out.println("===== Before Advice =====");

        System.out.println(new SimpleDateFormat("yyyy/MM/dd").format(new java.util.Date()));

        System.out.printf("method: %s", joinPoint.getSignature().getName());
    }
}
