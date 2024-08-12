package t1.ismailov.timetracking.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Data Transfer Object (DTO) representing statistics about the performance of methods.
 *
 * <p>This class is used to transfer data related to the execution time and success rate of methods
 * within the application. It includes details such as the average, minimum, and maximum execution times
 * in milliseconds, the percentage of successful executions, and the total number of executions.</p>
 *
 * <p>Instances of this class are typically used in response bodies in RESTful APIs to convey
 * performance statistics of methods to the client.</p>
 * */
@Getter
@Setter
@Accessors(chain = true)
@Schema(description = "Model for displaying method performance statistics")
public class Statistics {

    @Schema(description = "Average execution time of methods")
    private double averageExecutionTime;

    @Schema(description = "Minimum execution time of methods")
    private long minExecutionTime;

    @Schema(description = "Maximum execution time of methods")
    private long maxExecutionTime;

    @Schema(description = "Successful execution of methods as a percentage")
    private double successfullyPercent;

    @Schema(description = "Number of methods executed")
    private long countWorks;
}
