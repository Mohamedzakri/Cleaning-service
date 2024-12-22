package com.medzak.utils.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Aspect
@Component
@Slf4j
public class ReactiveTimeAspect {

    @Around("@annotation(TimeReactive)")
    public Object timeMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        long startTime = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        if (result instanceof Mono) {
            return ((Mono<?>) result).doOnSuccess(value -> {
                long duration = System.currentTimeMillis() - startTime;
                logCompletedMessage(methodName, duration);
                log.info("logCompletedMessage");
            }).doOnError(throwable -> {
                long duration = System.currentTimeMillis() - startTime;
                logCompletedMessage(methodName, duration);
            });
        } else if (result instanceof Flux) {
            return ((Flux<?>) result).doOnComplete(() -> {
                long duration = System.currentTimeMillis() - startTime;
                logCompletedMessage(methodName, duration);
            }).doOnError(throwable -> {
                long duration = System.currentTimeMillis() - startTime;
                logCompletedMessage(methodName, duration);
            });
        }

        return result;
    }

    private void logCompletedMessage(String methodName, long startTime) {
        log.error("Method {} completed in {}ms", methodName, startTime);
    }
}