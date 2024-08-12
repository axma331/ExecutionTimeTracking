package t1.ismailov.timetracking.aspect;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
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
            log.error("An exception was thrown! Error information is saved to the database using " +
                    "the createWithExceptionDto method");
            throw new RuntimeException(ex);
        }
    }

    @Order(10)
    @Async("executor")
    @Around("trackAsyncTimePointcut()")
    public CompletableFuture<Void> trackAsyncTime(ProceedingJoinPoint joinPoint) {
        MethodDto methodDto = methodMapper.getMethodDtoFromSignature(joinPoint.getSignature());
        long startTime = System.nanoTime();
        log.info("Method {} is executed async", methodDto.getName());

        return CompletableFuture.runAsync(() -> {
                    try {
                        joinPoint.proceed();
                        long endTime = System.nanoTime();

                        loggingAndSaveExecutionTime(methodDto, startTime, endTime, true);
                    } catch (Throwable e) {
                        executionTimeService.save(
                                executionTimeMapper.createWithExceptionAsyncDto(methodDto)
                        );
                        log.error("An exception was thrown! Error information is saved to the database using " +
                                "the createWithExceptionAsyncDto method", e);
                        throw new RuntimeException(e);
                    }
                }
        );
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
