package t1.ismailov.timetracking.service;

import t1.ismailov.timetracking.dto.MethodDto;
import t1.ismailov.timetracking.dto.Statistics;
import t1.ismailov.timetracking.entity.Method;

import java.util.List;
import java.util.Map;

public interface MethodService {

    Map<Long, Map<String, String>>  findAllMethodsAsMap();

    Method findByNameOrSave(MethodDto dto);

    Statistics getStatisticsByMethodId(long methodId);

    Statistics getStatisticsAllOrByStatus(Boolean async);

    Statistics getStatisticsByGroup(String group, boolean async);
}
