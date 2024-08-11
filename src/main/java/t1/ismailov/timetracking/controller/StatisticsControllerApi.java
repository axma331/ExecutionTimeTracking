package t1.ismailov.timetracking.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import t1.ismailov.timetracking.dto.Statistics;

@RestController
@RequestMapping("/v1/statistics")
@Tag(name = "Statistics", description = "Data on the execution of methods")
public interface StatisticsControllerApi {

    @Operation(summary = "Getting statistics on an annotation",
            description = "Getting statistics of synchronous/asynchronous execution of methods " +
                    "marked with the @TrackTime/@TrackAsyncTime annotation")

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Statistics> getStatisticsByAsyncStatus(@Parameter(description = "Parameter for getting statistics " +
            "on methods executed synchronously = false / asynchronously = true")
                                                          @RequestParam(required = false, defaultValue = "false")
                                                          boolean async);

    @Operation(summary = "Getting statistics by ID", description = "Getting statistics for a method by ID")

    @GetMapping(value = "/method/{methodId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Statistics> getStatisticsByMethodId(
            @Parameter(description = "ID of the method to obtain statistics on it")
            @Schema(example = "1")
            @PathVariable long methodId
    );

    @Operation(summary = "Getting statistics for a group of methods",
            description = "Obtaining statistics of synchronous/asynchronous execution of a method by Id," +
                    "marked with the @TrackTime/@TrackAsyncTime annotation")

    //Получить все группы

    @GetMapping(value = "/group/{group}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Statistics> getStatisticsByGroup(
            @Parameter(description = "Parameter for getting statistics of executed methods " +
                    "synchronously = false / asynchronously = true in a group")
            @RequestParam(required = false, defaultValue = "false")
            boolean async,
            @Parameter(description = "Parameter for specifying a group of methods for which statistics are needed")
            @Schema(example = "service")
            @PathVariable String group);
}