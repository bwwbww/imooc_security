package com.bww.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;

import java.util.Date;

//@Aspect
//@Component
public class TimeAspect {

    @Around("execution(* com.bww.controller.UserController.*(..))")
    public Object handleController(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("time aspect start");
        Object[] args = pjp.getArgs();
        for (int i = 0; i < args.length; i++) {
            Object arg = args[i];
            System.out.println("arg is "+ arg);
        }
        long time = new Date().getTime();
        Object proceed = pjp.proceed();
        System.out.println("time aspect 耗时:"+(new Date().getTime()-time));
        return proceed;
    }
}
