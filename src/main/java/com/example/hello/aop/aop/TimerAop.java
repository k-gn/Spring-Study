package com.example.hello.aop.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class TimerAop {

    @Pointcut("execution(* com.example.hello.aop.controller..*.*(..))")
    public void cut(){}

    @Pointcut("@annotation(com.example.hello.aop.annotation.Timer)")
    private void enableTimer() {}

    @Around("cut() && enableTimer()")
    public void arround(ProceedingJoinPoint joinPoint) throws Throwable {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = joinPoint.proceed();
        stopWatch.stop();
        System.out.println("total time : " + stopWatch.getTotalTimeSeconds());
    }
}
