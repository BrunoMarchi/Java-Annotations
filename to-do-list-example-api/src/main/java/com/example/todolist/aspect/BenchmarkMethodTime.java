package com.example.todolist.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class BenchmarkMethodTime {

    @Around("@annotation(com.example.todolist.annotation.BenchmarkTime)")
    public Object benchmarkTime(ProceedingJoinPoint point) throws Throwable {
        long start = System.currentTimeMillis();

        Object returnObject = point.proceed();

        long totalTime = (System.currentTimeMillis() - start);
        log.info("Method {} took {} milliseconds to run", point.getSignature().getName(), totalTime);

        return returnObject;
    }
}
