package t1.ismailov.timetracking.service;

import t1.ismailov.timetracking.dto.MethodDto;
import t1.ismailov.timetracking.dto.Statistics;
import t1.ismailov.timetracking.entity.Method;

public interface MethodService {

    Method findByNameOrSave(MethodDto dto);

    Statistics getStatisticsByMethodId(long methodId);

    Statistics getStatisticsByAsyncStatus(boolean async);

    Statistics getStatisticsByGroup(String group, boolean async);
}
