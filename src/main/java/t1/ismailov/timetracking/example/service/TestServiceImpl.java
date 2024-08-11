package t1.ismailov.timetracking.example.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import t1.ismailov.timetracking.annotation.TrackAsyncTime;
import t1.ismailov.timetracking.annotation.TrackTime;

@Service
@Slf4j
public class TestServiceImpl implements TestService {

    @Override
    @TrackTime
    public void testTime() {
        randomTime();
    }

    @TrackAsyncTime
    @Override
    public void testAsyncTime() {
        randomTime();
    }

    private static void randomTime() {
        int random = (int) (Math.random() * 10);
        for (int i = 0; i < random; ++i) {
            System.out.println(i);
        }
    }
}