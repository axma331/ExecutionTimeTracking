package t1.ismailov.timetracking.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import t1.ismailov.timetracking.dto.Statistics;

@RestController
@RequestMapping("/statistics/v1")
@Tag(name = "Статистика", description = "API для получения статистики")
public interface StatisticsControllerApi {

    @GetMapping(value = "/method/{methodId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Получение статистики по ID", description = "Получение статистики для метода по ID")
    ResponseEntity<Statistics> getStatisticsByMethodId(@Parameter(description = "ID метода, для которого требуется статистика")
                                                       @PathVariable long methodId);

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Получение статистики по аннотации",
            description = "Получение статистики для методов, помеченных аннотацией:" +
                    " @TrackTime (синхронно) или @TrackAsyncTime (асинхронно)")
    ResponseEntity<Statistics> getStatisticsByAsyncStatus(@Parameter(description = "Параметр для указания типа статистики")
                                                          @RequestParam(required = false, defaultValue = "false") boolean async);

    @GetMapping(value = "/group/{group}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Получение статистики по группе методов",
            description = "Получение статистики для методов определенной группы, помеченных аннотацией: " +
                    "@TrackTime (синхронно) или @TrackAsyncTime (асинхронно)")
    ResponseEntity<Statistics> getStatisticsByGroup(@Parameter(description = "Параметр для указания типа статистики")
                                                    @RequestParam(required = false, defaultValue = "false") boolean async,
                                                    @Parameter(description = "Параметр для указания группы методов")
                                                    @PathVariable String group);
}