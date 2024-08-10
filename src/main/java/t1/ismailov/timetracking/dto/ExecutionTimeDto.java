package t1.ismailov.timetracking.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class ExecutionTimeDto {

    private MethodDto methodDto;        // DTO метода
    private Long executionTime;         // Время выполнения работы
    private boolean async;              // Асинхронное или синхронное выполнение
    private boolean successfully;       // Успешное завершение
}
