package t1.ismailov.timetracking.example.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import t1.ismailov.timetracking.annotation.TrackAsyncTime;
import t1.ismailov.timetracking.annotation.TrackTime;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Service
@Slf4j
public class TestServiceImpl implements TestService {
    public final static Random random = new Random();

    @Override
    @TrackTime
    public void testTime() throws InterruptedException {
        randomTime();
    }

    @Override
    @TrackAsyncTime
    public void testAsyncTime() throws InterruptedException {
        randomTime();
    }

    private static void randomTime() throws InterruptedException {
        long sleepTime = random.nextLong(50) + 1;
        System.out.printf("Метод уснул на %d ms\n", sleepTime);
        Thread.sleep(sleepTime);
        if (random.nextBoolean()) {
            throw new InterruptedException("Force abort of a method to test exception handling");
        }
    }
}