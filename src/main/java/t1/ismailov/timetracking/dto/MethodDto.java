package t1.ismailov.timetracking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class MethodDto {

    private String name;            // Наименование метода
    private String declaredType;    // Класс, к которому относится метод
    private String group;           // Группа, к которой относится метод (например, имя пакета)
}
