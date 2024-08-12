package t1.ismailov.timetracking.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import t1.ismailov.timetracking.dto.Statistics;
import t1.ismailov.timetracking.service.MethodService;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class StatisticsController implements StatisticsControllerApi {

    private final MethodService methodService;

    @Override
    public ResponseEntity<Statistics> getStatisticsByMethodId(long methodId) {
        return ResponseEntity.ok(methodService.getStatisticsByMethodId(methodId));
    }

    @Override
    public ResponseEntity<Statistics> getAllStatisticsByStatus(Boolean async) {
        return ResponseEntity.ok(methodService.getStatisticsAllOrByStatus(async));
    }

    @Override
    public ResponseEntity<Statistics> getStatisticsByGroup(boolean async, String group) {
        return ResponseEntity.ok(methodService.getStatisticsByGroup(group, async));
    }

    @Override
    public Map<Long, Map<String, String>>  getMethodNamesWithId() {
        return methodService.findAllMethodsAsMap();
    }
}
