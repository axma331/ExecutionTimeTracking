package t1.ismailov.timetracking.example.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import t1.ismailov.timetracking.annotation.TrackAsyncTime;
import t1.ismailov.timetracking.annotation.TrackTime;

import java.util.Random;

@Service
@Slf4j
public class TestServiceImpl implements TestService {
    public final static Random random = new Random();

    @Override
    @TrackTime
    public void testTime() throws InterruptedException {
        long sleepTime = random.nextLong(50) + 1;
        System.out.printf("Метод testTime уснул на %d ms\n", sleepTime);
        Thread.sleep(sleepTime);
        if (random.nextBoolean()) {
            throw new InterruptedException("Force abort of a method to test exception handling");
        }
    }

    @Override
    @TrackAsyncTime
    public void testAsyncTime() throws InterruptedException {
        long sleepTime = random.nextLong(50) + 1;
        System.out.printf("Метод testAsyncTime уснул на %d ms\n", sleepTime);
        Thread.sleep(sleepTime);
        if (random.nextBoolean()) {
            throw new InterruptedException("Force abort of a method to test exception handling");
        }
    }
}