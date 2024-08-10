package t1.ismailov.timetracking.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@Schema(description = "Модель для отображения статистики работы метода(-ов)")
public class Statistics {

    @Schema(description = "Среднее время выполнения метода(-ов)")
    private double averageExecutionTime;

    @Schema(description = "Минимальное время выполнения метода(-ов)")
    private long minExecutionTime;

    @Schema(description = "Максимальное время выполнения метода(-ов)")
    private long maxExecutionTime;

    @Schema(description = "Процент успешного завершения метода(-ов)")
    private double successfullyPercent;

    @Schema(description = "Количество выполненных методов")
    private long countWorks;
}
