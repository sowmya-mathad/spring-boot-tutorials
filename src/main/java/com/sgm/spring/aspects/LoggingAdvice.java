package com.sgm.spring.aspects;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.SimpleTimeZone;


@Aspect
@Component
public class LoggingAdvice {

    Logger logger = LoggerFactory.getLogger(LoggingAdvice.class);

    @Pointcut(value="execution(* com.sgm.spring.*.*.*(..) )")
    public void myPointCut() {

    }
    @Around("myPointCut()")
    public Object applicationLogger(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        ObjectMapper objectMapper = new ObjectMapper();
        String methodName = proceedingJoinPoint.getSignature().getName();
        String className = proceedingJoinPoint.getSignature().getDeclaringTypeName();
        String s = objectMapper.writeValueAsString(proceedingJoinPoint.getArgs());
        logger.debug("Method Invoked: "+ className + " " + methodName + Arrays.toString(proceedingJoinPoint.getArgs()));
        Instant start = Instant.now();
        Object object = proceedingJoinPoint.proceed();
        logger.info("Class Name: " + className + " Response : " +
                objectMapper.writeValueAsString(proceedingJoinPoint.getArgs()));
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start,finish).toMillis();
        logger.info("Method Exit: "+ className + " " + methodName + object);
    logger.info(String.format("Time Taken To Complete: " + new SimpleDateFormat("HH:MM:SS").format(timeElapsed) ));
        return object;
    }


}
