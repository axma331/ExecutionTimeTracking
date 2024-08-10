package t1.ismailov.timetracking.example.service;

import org.springframework.stereotype.Service;
import t1.ismailov.timetracking.annotation.TrackAsyncTime;
import t1.ismailov.timetracking.annotation.TrackTime;

@Service
public class TestServiceImpl implements TestService {

    @Override
    @TrackTime
    public void testTime() {
        int random = (int) (Math.random() * 10);
        for (int i = 0; i < random; i++) {
            System.out.println(i);
        }
    }

    @TrackAsyncTime
    @Override
    public void testAsyncTime() {
        int random = (int) (Math.random() * 10);
        for (int i = 0; i < random; i++) {
            System.out.println(i);
        }

    }
}