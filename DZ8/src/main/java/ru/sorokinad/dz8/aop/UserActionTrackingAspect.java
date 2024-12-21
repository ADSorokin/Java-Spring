package ru.sorokinad.dz8.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class UserActionTrackingAspect {

    private static final Logger logger = LoggerFactory.getLogger(UserActionTrackingAspect.class);

    @Before("@annotation(ru.sorokinad.dz8.aop.TrackUserAction)")
    public void trackUserAction(JoinPoint joinPoint) {
        // На практике текущего пользователя можно получить через SecurityContext.
        String currentUser = "GuestUser"; // Для примера - фиктивный пользователь.

        String methodName = joinPoint.getSignature().toString();
        Object[] args = joinPoint.getArgs();

        // Логируем информацию о вызове
        logger.info("User '{}' invoked method '{}', with arguments: {}", currentUser, methodName, Arrays.toString(args));
    }
}
