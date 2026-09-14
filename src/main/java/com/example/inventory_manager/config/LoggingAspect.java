package com.example.inventory_manager.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.example.inventory_manager.service..*(..))")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Entering {}", joinPoint.getSignature().toShortString());
    }

    @After("execution(* com.example.inventory_manager.service..*(..))")
    public void logAfter(JoinPoint joinPoint) {
        logger.info("Exiting {}", joinPoint.getSignature().toShortString());
    }
}
