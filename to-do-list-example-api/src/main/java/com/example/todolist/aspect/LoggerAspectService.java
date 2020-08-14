package com.example.todolist.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LoggerAspectService {

    @Before("@annotation(com.example.todolist.annotation.LogParameters)")
    public Object logMethodParameters(JoinPoint joinPoint) {
        Object[] arguments = joinPoint.getArgs();

        StringBuilder argumentsStringBuilder = new StringBuilder();

        for(Object obj : arguments) {
            argumentsStringBuilder.append(obj);
        }

        log.info("Method {} has the following arguments {}",
                joinPoint.getSignature().getName(),
                argumentsStringBuilder.toString());

        return joinPoint;
    }
}
