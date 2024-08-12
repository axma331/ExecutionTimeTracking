package t1.ismailov.timetracking.aspect;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import t1.ismailov.timetracking.dto.ExecutionTimeDto;
import t1.ismailov.timetracking.dto.MethodDto;
import t1.ismailov.timetracking.mapper.ExecutionTimeMapper;
import t1.ismailov.timetracking.mapper.MethodMapper;
import t1.ismailov.timetracking.service.ExecutionTimeService;

import java.util.concurrent.CompletableFuture;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class ExecutionTimeTrackingAspect {

    private final ExecutionTimeService executionTimeService;
    private final ExecutionTimeMapper executionTimeMapper;
    private final MethodMapper methodMapper;

    @Pointcut("@annotation(t1.ismailov.timetracking.annotation.TrackTime)")
    public void trackTimePointcut() {
    }

    @Pointcut("@annotation(t1.ismailov.timetracking.annotation.TrackAsyncTime)")
    public void trackAsyncTimePointcut() {
    }


    @Order(1)
    @Around("trackTimePointcut()")
    public Object trackTime(ProceedingJoinPoint joinPoint) {
        MethodDto methodDto = methodMapper.getMethodDtoFromSignature(joinPoint.getSignature());
        long startTime = System.nanoTime();
        log.info("Method {} is executed sync", methodDto.getName());
        try {
            Object result = joinPoint.proceed();
            long endTime = System.nanoTime();

            loggingAndSaveExecutionTime(methodDto, startTime, endTime, false);
            return result;
        } catch (Throwable ex) {
            executionTimeService.save(
                    executionTimeMapper.createWithExceptionDto(methodDto)
            );
            throw new RuntimeException(ex);
        }
    }

    @Order(5)
    @Around("trackAsyncTimePointcut()")
    public Object trackAsyncTime(ProceedingJoinPoint joinPoint) {
        MethodDto methodDto = methodMapper.getMethodDtoFromSignature(joinPoint.getSignature());
        long startTime = System.nanoTime();
        log.info("Method {} is executed async", methodDto.getName());

        return CompletableFuture.supplyAsync(() -> {
            try {
                return joinPoint.proceed();
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }).thenApply(result -> {
            long endTime = System.nanoTime();

            loggingAndSaveExecutionTime(methodDto, startTime, endTime, true);
            return result;
        }).exceptionally(ex -> {
            executionTimeService.save(
                    executionTimeMapper.createWithExceptionAsyncDto(methodDto)
            );
            throw new RuntimeException(ex);
        });
    }

    private void loggingAndSaveExecutionTime(MethodDto methodDto, long startTime, long endTime, boolean isAsync) {
        long executionTime = (endTime - startTime) / 1_000_000;

        ExecutionTimeDto dto = isAsync
                ? executionTimeMapper.createAsyncDto(methodDto, executionTime)
                : executionTimeMapper.createDto(methodDto, executionTime);
        executionTimeService.save(dto);

        log.info("Method {} executed {} in {} ms", methodDto.getName(), isAsync ? "async" : "sync", executionTime);
    }
}
