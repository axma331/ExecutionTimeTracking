package t1.ismailov.timetracking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import t1.ismailov.timetracking.dto.MethodDto;
import t1.ismailov.timetracking.dto.Statistics;
import t1.ismailov.timetracking.entity.ExecutionTime;
import t1.ismailov.timetracking.entity.Method;
import t1.ismailov.timetracking.mapper.MethodMapper;
import t1.ismailov.timetracking.repository.MethodRepository;
import t1.ismailov.timetracking.util.CalculateStatistic;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MethodServiceImpl implements MethodService {

    private final MethodMapper mapper;
    private final MethodRepository repository;
    private final CalculateStatistic calculateStatistic;

    @Override
    public Map<Long, Map<String, String>> findAllMethodsAsMap() {
        return repository.findAll().stream()
                .collect(Collectors.toMap(
                        Method::getId,
                        method -> Map.of(
                                "name", method.getName(),
                                "group", method.getGroup()
                        )
                ));
    }

    @Override
    public Method findByNameOrSave(MethodDto dto) {
        return repository.findByNameAndDeclaredType(dto.getName(), dto.getDeclaredType())
                .orElseGet(() -> save(dto));
    }

    private Method save(MethodDto dto) {
        Method method = mapper.dtoToEntity(dto);
        return repository.save(method);
    }

    @Override
    public Statistics getStatisticsByMethodId(long id) {
        Method method = repository.findById(id).orElseGet(Method::new);
        List<ExecutionTime> executionTimes = method.getExecutionTimes();

        return calculateStatistic.calculate(executionTimes);
    }

    @Override
    public Statistics getStatisticsAllOrByStatus(Boolean isAsync) {
        return calculateStatistic.calculate(
                Objects.nonNull(isAsync)
                        ? repository.findExecutionTimeByAsync(isAsync)
                        : repository.findAllExecutionTimes()
        );
    }

    @Override
    public Statistics getStatisticsByGroup(String group, boolean isAsync) {
        List<Method> list = repository.findAllByGroup(group);
        List<ExecutionTime> executionTimes = list.stream()
                .flatMap(method -> method.getExecutionTimes().stream())
                .filter(executionTime -> executionTime.isAsync() == isAsync)
                .toList();
        return calculateStatistic.calculate(executionTimes);
    }
}
