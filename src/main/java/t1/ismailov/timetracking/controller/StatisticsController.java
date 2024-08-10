package t1.ismailov.timetracking.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import t1.ismailov.timetracking.dto.Statistics;
import t1.ismailov.timetracking.service.MethodService;

@RestController
@RequiredArgsConstructor
public class StatisticsController implements StatisticsControllerApi {

    private final MethodService methodService;

    @Override
    public ResponseEntity<Statistics> getStatisticsByMethodId(long methodId) {
        return ResponseEntity.ok(methodService.getStatisticsByMethodId(methodId));
    }

    @Override
    public ResponseEntity<Statistics> getStatisticsByAsyncStatus(boolean async) {
        return ResponseEntity.ok(methodService.getStatisticsByAsyncStatus(async));
    }

    @Override
    public ResponseEntity<Statistics> getStatisticsByGroup(boolean async, String group) {
        return ResponseEntity.ok(methodService.getStatisticsByGroup(group, async));
    }
}
