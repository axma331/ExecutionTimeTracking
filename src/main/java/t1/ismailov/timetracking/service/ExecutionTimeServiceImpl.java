package t1.ismailov.timetracking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import t1.ismailov.timetracking.dto.ExecutionTimeDto;
import t1.ismailov.timetracking.entity.ExecutionTime;
import t1.ismailov.timetracking.entity.Method;
import t1.ismailov.timetracking.mapper.ExecutionTimeMapper;
import t1.ismailov.timetracking.repository.ExecutionTimeRepository;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ExecutionTimeServiceImpl implements ExecutionTimeService {

    private final MethodService methodService;
    private final ExecutionTimeMapper executionTimeMapper;
    private final ExecutionTimeRepository repository;

    @Override
    @Async("jobExecutor")
    @Transactional
    public void save(ExecutionTimeDto dto) {
        Method method = methodService.findByNameOrSave(dto.getMethodDto());
        ExecutionTime executionTime = executionTimeMapper.dtoToEntity(dto).setMethodId(method);
        repository.save(executionTime);
    }
}
