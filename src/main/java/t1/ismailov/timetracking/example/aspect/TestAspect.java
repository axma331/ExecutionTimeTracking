package t1.ismailov.timetracking.example.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Random;


@Aspect
@Component
@Slf4j
public class TestAspect {

    public final static Random random = new Random();

    @Order(2)
    @Before("within(t1.ismailov.timetracking.example.service..*))")
    public void intercept(JoinPoint joinPoint) throws Throwable {
        if (random.nextBoolean()) {
            throw new InterruptedException("Force abort of a method to test exception handling");
        }
    }
}
