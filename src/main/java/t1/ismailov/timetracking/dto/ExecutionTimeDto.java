package t1.ismailov.timetracking.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Data Transfer Object (DTO) representing the execution time and metadata of a method execution.
 *
 * <p>This class encapsulates information about the execution of a method, including the method itself,
 * the time it took to execute, whether the execution was asynchronous, and whether it completed successfully.</p>
 *
 * <p>Instances of this class are typically used to transfer data between different layers of the application,
 * such as between the service layer and the database layer, or to communicate data to the client through REST APIs.</p>
 **/
@Getter
@Setter
@Accessors(chain = true)
public class ExecutionTimeDto {

    /**
     * The DTO representation of the method that was executed.
     */
    private MethodDto methodDto;

    /**
     * The time it took to execute the method, in milliseconds.
     */
    private Long executionTime;

    /**
     * Indicates whether the method execution was asynchronous.
     *
     * <p><code>true</code> if the method was executed asynchronously; <code>false</code> if it was executed synchronously.</p>
     */
    private boolean async;

    /**
     * Indicates whether the method execution completed successfully.
     *
     * <p><code>true</code> if the execution was successful; <code>false</code> if an error occurred during execution.</p>
     */
    private boolean successfully;
}
