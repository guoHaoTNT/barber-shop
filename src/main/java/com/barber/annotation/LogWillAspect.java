package com.barber.annotation;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author will
 */
@Slf4j
@Aspect
@Component
public class LogWillAspect {

    /**
     * 环绕通知
     * @param point 切入点
     * @param logWill 注解
     */
    @Around(value = "@annotation(logWill)")
    public void logInfo(ProceedingJoinPoint point, LogWill logWill){


    }
}
