package t1.ismailov.timetracking.example.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import t1.ismailov.timetracking.annotation.TrackAsyncTime;
import t1.ismailov.timetracking.annotation.TrackTime;

import java.util.concurrent.ThreadLocalRandom;

@Service
@Slf4j
public class TestServiceImpl implements TestService {

    @Override
    @TrackTime
    public void testTime() throws InterruptedException {
        randomTime();
    }

    @TrackAsyncTime
    @Override
    public void testAsyncTime() throws InterruptedException {
        randomTime();
    }

    private static void randomTime() throws InterruptedException {
        long sleepTime = ThreadLocalRandom.current().nextLong(10, 1000);
        System.out.printf("Метод уснул на %d ms\n", sleepTime);
        Thread.sleep(sleepTime);
    }
}